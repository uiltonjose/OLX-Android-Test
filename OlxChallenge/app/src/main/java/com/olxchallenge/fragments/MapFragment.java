package com.olxchallenge.fragments;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.olxchallenge.R;
import com.olxchallenge.bean.Ads;
import com.olxchallenge.bean.MapMarker;
import com.olxchallenge.bean.Page;
import com.olxchallenge.comm.FetchServerTask;
import com.olxchallenge.util.AndroidUtil;

import java.util.ArrayList;
import java.util.List;

public class MapFragment extends Fragment implements OnMapReadyCallback, FetchServerTask.ServerCallback {

    private static final int REQUEST_CODE_ASK_PERMISSIONS = 100;

    private List<Marker> markers;
    private GoogleMap googleMap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.map_fragment, container, false);
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_view);
        supportMapFragment.getMapAsync(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        markers = new ArrayList<>();
        new FetchServerTask(this).execute();
    }

    private void plotManyMarkers(List<MapMarker> mapMarkers) {

        if (mapMarkers != null && !mapMarkers.isEmpty()) {

            for (MapMarker marker : mapMarkers) {
                double lat = marker.getLatitude();
                double lng = marker.getLongitude();
                drawMarker(new LatLng(lat, lng), marker);
            }

            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            for (Marker marker : markers) {
                builder.include(marker.getPosition());
            }
            LatLngBounds bounds = builder.build();
            CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 0);
            googleMap.animateCamera(cu);
        }
    }

    private void drawMarker(LatLng point, MapMarker marker) {
        MarkerOptions markerOptions = new MarkerOptions();

        markerOptions.position(point);
        markerOptions.title(marker.getTitle());
        markerOptions.snippet(marker.getSnippet());
//        markerOptions.icon(BitmapDescriptorFactory.fromBitmap((Bitmap) marker.getExtra()));

        markers.add(googleMap.addMarker(markerOptions));
    }

    @Override
    public void onMapReady(GoogleMap map) {
        this.googleMap = map;
        checkAndroidPermission();
        setupMap();
    }

    private void setupMap() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            googleMap.setMyLocationEnabled(true);
            googleMap.setTrafficEnabled(false);
            googleMap.setIndoorEnabled(false);
            googleMap.setBuildingsEnabled(true);
            googleMap.getUiSettings().setZoomControlsEnabled(true);
        }
    }

    private void checkAndroidPermission() {
        List<String> permissionsNeeded = new ArrayList<>();
        final List<String> permissionsList = new ArrayList<String>();

        if (!addPermission(permissionsList, Manifest.permission.ACCESS_FINE_LOCATION))
            permissionsNeeded.add("\n-GPS");
        if (!addPermission(permissionsList, Manifest.permission.ACCESS_COARSE_LOCATION))
            permissionsNeeded.add("-Localização");

        if (permissionsList.size() > 0) {
            if (permissionsNeeded.size() > 0) {
                String message = "Você precisa conceder acesso a: " + permissionsNeeded.get(0);
                for (int i = 1; i < permissionsNeeded.size(); i++)
                    message = message + "\n" + permissionsNeeded.get(i);
                AndroidUtil.showMessageOKCancel(getActivity(), message,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(getActivity(), permissionsList.toArray(new String[permissionsList.size()]), REQUEST_CODE_ASK_PERMISSIONS);
                            }
                        });
                return;
            }
            ActivityCompat.requestPermissions(getActivity(), permissionsList.toArray(new String[permissionsList.size()]), REQUEST_CODE_ASK_PERMISSIONS);
            return;
        }

        setupMap();
    }

    private boolean addPermission(List<String> permissionsList, String permission) {
        if (ContextCompat.checkSelfPermission(getActivity(), permission) != PackageManager.PERMISSION_GRANTED) {
            permissionsList.add(permission);
            if (!ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), permission))
                return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                setupMap();
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void resultServer(Page page) {

        List<MapMarker> mapMarkers = new ArrayList<>();
        List<Ads> adsList = page.getAdsList();
        for (Ads ad : adsList) {
            MapMarker marker = new MapMarker();
            marker.setLatitude(ad.getMapLat());
            marker.setLongitude(ad.getMapLon());
            marker.setTitle(ad.getTitle() + "\n" + ad.getCityLabel());
            marker.setSnippet(ad.getDescription());
            mapMarkers.add(marker);
        }

        plotManyMarkers(mapMarkers);
    }
}
