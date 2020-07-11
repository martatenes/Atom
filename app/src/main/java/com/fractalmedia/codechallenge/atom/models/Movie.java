package com.fractalmedia.codechallenge.atom.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Movie implements Serializable {

    private long id;
    private String title;
    @SerializedName("overview")
    private String overview;
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
    private List<SpokenLanguage> spokenLanguagesList;
    private boolean adult;
  //  @SerializedName("belongs_to_collection")
  //  private String belongsToCollection;
    private String homepage;
    @SerializedName("imdb_id")
    private String imdbId;
    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("original_title")
    private String originalTitle;
    @SerializedName("production_companies")
    List<ProductionCompany> productionCompanies;
    @SerializedName("production_countries")
    List<ProductionCountry> productionCountries;
    @SerializedName("release_date")
    private String releaseDate;
    private float revenue;
    private int runtime;
    private String tagline;
    private boolean video;
    @SerializedName("vote_average")
    private float voteAverage;
    @SerializedName("vote_count")
    private float voteCount;

    public Movie(){
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
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

    public List<SpokenLanguage> getSpokenLanguagesList() {
        return spokenLanguagesList;
    }

    public void setSpokenLanguagesList(List<SpokenLanguage> spokenLanguagesList) {
        this.spokenLanguagesList = spokenLanguagesList;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

  /*  public String getBelongsToCollection() {
        return belongsToCollection;
    }

    public void setBelongsToCollection(String belongsToCollection) {
        this.belongsToCollection = belongsToCollection;
    }*/

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }


    public List<ProductionCompany> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(List<ProductionCompany> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public List<ProductionCountry> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(List<ProductionCountry> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public float getRevenue() {
        return revenue;
    }

    public void setRevenue(float revenue) {
        this.revenue = revenue;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public float getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(float voteCount) {
        this.voteCount = voteCount;
    }
}
