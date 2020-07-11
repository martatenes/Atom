package com.fractalmedia.codechallenge.atom.models;

import com.google.gson.annotations.SerializedName;

public class ProductionCountry {

    @SerializedName("iso_3166_1")
    private String iso;
    private String name;

    public ProductionCountry(String iso, String name) {
        this.iso = iso;
        this.name = name;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
