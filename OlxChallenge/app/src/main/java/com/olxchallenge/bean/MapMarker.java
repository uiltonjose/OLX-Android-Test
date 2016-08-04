package com.olxchallenge.bean;

public class MapMarker {

    private double latitude;
    private double longitude;
    private String title;
    private String snippet;
    private Object extra;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = Double.parseDouble(latitude);
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = Double.parseDouble(longitude);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public Object getExtra() {
        return extra;
    }

    public void setExtra(Object extra) {
        this.extra = extra;
    }
}
