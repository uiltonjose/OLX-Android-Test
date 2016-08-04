package com.olxchallenge.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Ads implements Serializable {

    @SerializedName("id")
    private String id;

    @SerializedName("url")
    private String url;

    @SerializedName("title")
    private String title;

    @SerializedName("created")
    private String created;

    @SerializedName("age")
    private int age;

    @SerializedName("description")
    private String description;

    @SerializedName("category_id")
    private int categoryId;

    @SerializedName("status")
    private String status;

    @SerializedName("campaignSource")
    private String campaignSource;

    @SerializedName("has_phone")
    private int hasPhone;

    @SerializedName("has_email")
    private int hasEmail;

    @SerializedName("is_price_negotiable")
    private int isPriceNegotiable;

    @SerializedName("price_type")
    private String priceType;

    @SerializedName("price_numeric")
    private String priceNumeric;

    @SerializedName("map_zoom")
    private String mapZoom;

    @SerializedName("map_lat")
    private String mapLat;

    @SerializedName("map_lon")
    private String mapLon;

    @SerializedName("map_location")
    private String mapLocation;

    @SerializedName("city_label")
    private String cityLabel;

    @SerializedName("person")
    private String person;

    @SerializedName("user_label")
    private String userLabel;

    @SerializedName("user_ads_id")
    private String userAdsId;

    @SerializedName("user_ads_url")
    private String userAdsUrl;

    @SerializedName("list_label")
    private String listLabel;

    @SerializedName("list_label_ad")
    private String listLabelAd;

    @SerializedName("list_label_small")
    private String listLabelSmall;

    @SerializedName("photos")
    private Photo photos;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCampaignSource() {
        return campaignSource;
    }

    public void setCampaignSource(String campaignSource) {
        this.campaignSource = campaignSource;
    }

    public int getHasPhone() {
        return hasPhone;
    }

    public void setHasPhone(int hasPhone) {
        this.hasPhone = hasPhone;
    }

    public int getHasEmail() {
        return hasEmail;
    }

    public void setHasEmail(int hasEmail) {
        this.hasEmail = hasEmail;
    }

    public int getIsPriceNegotiable() {
        return isPriceNegotiable;
    }

    public void setIsPriceNegotiable(int isPriceNegotiable) {
        this.isPriceNegotiable = isPriceNegotiable;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public String getPriceNumeric() {
        return priceNumeric;
    }

    public void setPriceNumeric(String priceNumeric) {
        this.priceNumeric = priceNumeric;
    }

    public String getMapZoom() {
        return mapZoom;
    }

    public void setMapZoom(String mapZoom) {
        this.mapZoom = mapZoom;
    }

    public String getMapLat() {
        return mapLat;
    }

    public void setMapLat(String mapLat) {
        this.mapLat = mapLat;
    }

    public String getMapLon() {
        return mapLon;
    }

    public void setMapLon(String mapLon) {
        this.mapLon = mapLon;
    }

    public String getMapLocation() {
        return mapLocation;
    }

    public void setMapLocation(String mapLocation) {
        this.mapLocation = mapLocation;
    }

    public String getCityLabel() {
        return cityLabel;
    }

    public void setCityLabel(String cityLabel) {
        this.cityLabel = cityLabel;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getUserLabel() {
        return userLabel;
    }

    public void setUserLabel(String userLabel) {
        this.userLabel = userLabel;
    }

    public String getUserAdsId() {
        return userAdsId;
    }

    public void setUserAdsId(String userAdsId) {
        this.userAdsId = userAdsId;
    }

    public String getUserAdsUrl() {
        return userAdsUrl;
    }

    public void setUserAdsUrl(String userAdsUrl) {
        this.userAdsUrl = userAdsUrl;
    }

    public String getListLabel() {
        return listLabel;
    }

    public void setListLabel(String listLabel) {
        this.listLabel = listLabel;
    }

    public String getListLabelAd() {
        return listLabelAd;
    }

    public void setListLabelAd(String listLabelAd) {
        this.listLabelAd = listLabelAd;
    }

    public String getListLabelSmall() {
        return listLabelSmall;
    }

    public void setListLabelSmall(String listLabelSmall) {
        this.listLabelSmall = listLabelSmall;
    }

    public Photo getPhotos() {
        return photos;
    }

    public void setPhotos(Photo photos) {
        this.photos = photos;
    }
}
