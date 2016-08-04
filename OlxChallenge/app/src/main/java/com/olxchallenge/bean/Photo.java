package com.olxchallenge.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Photo implements Serializable {

    @SerializedName("riak_ring")
    private int riakRing;

    @SerializedName("riak_key")
    private int riakKey;

    @SerializedName("riak_rev")
    private int riakRev;

    @SerializedName("data")
    private List<Data> data;

    public Photo() {
        data = new ArrayList<>();
    }

    public int getRiakRing() {
        return riakRing;
    }

    public void setRiakRing(int riakRing) {
        this.riakRing = riakRing;
    }

    public int getRiakKey() {
        return riakKey;
    }

    public void setRiakKey(int riakKey) {
        this.riakKey = riakKey;
    }

    public int getRiakRev() {
        return riakRev;
    }

    public void setRiakRev(int riakRev) {
        this.riakRev = riakRev;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public class Data implements Serializable {

        @SerializedName("slot_id")
        private int slotiD;

        @SerializedName("w")
        private int w;

        @SerializedName("h")
        private int h;

        public int getSlotiD() {
            return slotiD;
        }

        public void setSlotiD(int slotiD) {
            this.slotiD = slotiD;
        }

        public int getW() {
            return w;
        }

        public void setW(int w) {
            this.w = w;
        }

        public int getH() {
            return h;
        }

        public void setH(int h) {
            this.h = h;
        }
    }
}
