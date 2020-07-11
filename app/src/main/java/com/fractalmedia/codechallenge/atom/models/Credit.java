package com.fractalmedia.codechallenge.atom.models;

import com.google.gson.annotations.SerializedName;

public class Credit {

    private long id;
    private String name;
    @SerializedName("profile_path")
    private String profilePath;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }
}
