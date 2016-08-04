package com.olxchallenge.comm;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.olxchallenge.BuildConfig;
import com.olxchallenge.bean.Ads;
import com.olxchallenge.bean.Page;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import io.realm.Realm;

public class CommonRequest {

    public static Page fetchPage(String url) {

        Page page = null;
        String jsonResult;
        if (url != null) {
            jsonResult = sendRequest(url);
        } else {
            jsonResult = sendRequest(BuildConfig.BASE_SERVER_URL);
        }

        try {
            JSONObject jsonObj = new JSONObject(jsonResult);
            page = new Page();
            page.setPage(jsonObj.optInt("page"));
            page.setTotalPages(jsonObj.optInt("total_pages"));
            page.setNextPageUrl(jsonObj.optString("next_page_url"));

            JSONArray adsArray = jsonObj.getJSONArray("ads");
            if (adsArray != null) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                Type listType = new TypeToken<List<Ads>>() {
                }.getType();

                List<Ads> ads = gson.fromJson(adsArray.toString(), listType);
                page.setAdsList(ads);
            }

            //TODO save on database
//            Realm realm = Realm.getDefaultInstance();
//            realm.beginTransaction();
//            realm.copyToRealm(page);
//            realm.commitTransaction();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return page;
    }

    private static String sendRequest(String requestString) {
        HttpURLConnection conn = null;
        StringBuilder jsonResults = new StringBuilder();
        try {
            URL url = new URL(requestString);
            conn = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(conn.getInputStream());

            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1)
                jsonResults.append(buff, 0, read);
        } catch (MalformedURLException e) {
            Log.w("MalformedURLException", "Error processing URL", e);
        } catch (IOException e) {
            Log.w("IOException", "Error", e);
        } finally {
            if (conn != null)
                conn.disconnect();
        }
        return jsonResults.toString();
    }
}
