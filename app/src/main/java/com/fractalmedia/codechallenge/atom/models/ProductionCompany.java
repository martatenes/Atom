package com.fractalmedia.codechallenge.atom.models;

import com.google.gson.annotations.SerializedName;

public class ProductionCompany {

    private long id;
    @SerializedName("logo_path")
    private String logoPath;
    private String name;
    @SerializedName("origin_country")
    private String originCountry;

    public ProductionCompany(long id, String logoPath, String name, String originCountry) {
        this.id = id;
        this.logoPath = logoPath;
        this.name = name;
        this.originCountry = originCountry;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }
}
