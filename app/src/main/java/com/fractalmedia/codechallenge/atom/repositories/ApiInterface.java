package com.fractalmedia.codechallenge.atom.repositories;

import com.fractalmedia.codechallenge.atom.repositories.responses.ConfigurationResponse;
import com.fractalmedia.codechallenge.atom.repositories.responses.MovieCreditsResponse;
import com.fractalmedia.codechallenge.atom.repositories.responses.MovieListResponse;
import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("configuration")
    Call<ConfigurationResponse> getConfiguration(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Call<MovieListResponse> getPopularMovies(@Query("api_key") String apiKey, @Query("page") Integer numPage);

    @GET("movie/{movie_id}")
    Call<JsonElement> getMovieDetails (@Path("movie_id") long movieId, @Query("api_key") String apiKey);

    @GET("search/movie")
    Call<MovieListResponse> getMoviesBySearch(@Query("api_key") String apiKey,  @Query("page") Integer PageNo,  @Query("query") String query, @Query("include_adult")  Boolean adult);

    @GET("movie/{movie_id}/credits")
    Call<MovieCreditsResponse> getMovieCredits (@Path("movie_id") long movieId, @Query("api_key") String apiKey);

}
