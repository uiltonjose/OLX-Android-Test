package com.olxchallenge.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.olxchallenge.bean.Ads;
import com.olxchallenge.bean.Photo;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AndroidUtil {

    public static boolean hasConnectivity(Context context) {
        if (context != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context
                    .getApplicationContext().getSystemService(
                            Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager
                    .getActiveNetworkInfo();

            return networkInfo != null && networkInfo.isAvailable()
                    && networkInfo.isConnected();
        } else {
            return false;
        }
    }

    public static void showMessageOKCancel(Context context, String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton("Sim", okListener)
                .setNegativeButton("Cancelar", null)
                .create()
                .show();
    }

    public static String getImageUrl(Ads ads) {
        //TEMPLATE: {riak_ring_url}{riak_bucket}\/{riak_key}_{slot_id}_{width}x{height}_rev{riak_rev}.jpg"}

        Photo photo = ads.getPhotos();
        String url = "https://img.olx.pt/images_olxpt/";
        if (photo != null && photo.getData() != null) {
            Photo.Data data = photo.getData().get(0);
            url += photo.getRiakKey() + "_" + data.getSlotiD() + "_" + data.getW() + "x" + data.getH()
                    + "_rev" + photo.getRiakRev() + ".jpg";

            return url;
        }
        return null;
    }

    public static Bitmap getBitmapFromURL(final Ads ads) {
        try {
            URL url = new URL(getImageUrl(ads));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
