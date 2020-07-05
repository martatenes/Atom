package com.fractalmedia.codechallenge.atom.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Movie {

    private String title;
    @SerializedName("overview")
    private String description;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("genres")
    private List<Genre> genreList;
    private float popularity;
    private long budget;
    private String status;
    @SerializedName("spoken_languages")
    private List<Languages> spokenLanguagesList;

    public Movie(String title, String description, String posterPath, String backdropPath, float popularity, long budget, String status) {
        this.title = title;
        this.description = description;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.popularity = popularity;
        this.budget = budget;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public List<Genre> getGenreList() {
        return genreList;
    }

    public void setGenreList(List<Genre> genreList) {
        this.genreList = genreList;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public long getBudget() {
        return budget;
    }

    public void setBudget(long budget) {
        this.budget = budget;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Languages> getSpokenLanguagesList() {
        return spokenLanguagesList;
    }

    public void setSpokenLanguagesList(List<Languages> spokenLanguagesList) {
        this.spokenLanguagesList = spokenLanguagesList;
    }
}
