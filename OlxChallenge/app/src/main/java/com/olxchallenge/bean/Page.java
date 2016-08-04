package com.olxchallenge.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Page implements Serializable {

    @SerializedName("page")
    private int page;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("ads_on_page")
    private int adsOnPage;

    @SerializedName("view")
    private String viewMode;

    @SerializedName("next_page_url")
    private String nextPageUrl;

    @SerializedName("ads")
    private List<Ads> adsList;

    public Page() {
        adsList = new ArrayList<>();
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getAdsOnPage() {
        return adsOnPage;
    }

    public void setAdsOnPage(int adsOnPage) {
        this.adsOnPage = adsOnPage;
    }

    public String getViewMode() {
        return viewMode;
    }

    public void setViewMode(String viewMode) {
        this.viewMode = viewMode;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public List<Ads> getAdsList() {
        return adsList;
    }

    public void setAdsList(List<Ads> adsList) {
        this.adsList = adsList;
    }
}
