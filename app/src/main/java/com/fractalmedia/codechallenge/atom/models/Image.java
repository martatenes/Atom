package com.fractalmedia.codechallenge.atom.models;

import com.google.gson.annotations.SerializedName;

public class Image{
    @SerializedName("base_url")
    private String baseUrl;
    @SerializedName("secure_base_url")
    private String secureBaseUrl;

    //TODO: Añadir el resto de atributos cuando los necesitemos, de momento no nos interesan

    public Image(String baseUrl, String secureBaseUrl) {
        this.baseUrl = baseUrl;
        this.secureBaseUrl = secureBaseUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getSecureBaseUrl() {
        return secureBaseUrl;
    }

    public void setSecureBaseUrl(String secureBaseUrl) {
        this.secureBaseUrl = secureBaseUrl;
    }
}
