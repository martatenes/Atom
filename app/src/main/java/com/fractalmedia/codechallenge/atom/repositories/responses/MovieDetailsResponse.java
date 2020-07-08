package com.fractalmedia.codechallenge.atom.repositories.responses;

import com.fractalmedia.codechallenge.atom.models.Genre;
import com.fractalmedia.codechallenge.atom.models.Movie;
import com.fractalmedia.codechallenge.atom.models.ProductionCompany;
import com.fractalmedia.codechallenge.atom.models.ProductionCountry;
import com.fractalmedia.codechallenge.atom.models.SpokenLanguage;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDetailsResponse {

    private Movie movie;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}