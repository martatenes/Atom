package com.fractalmedia.codechallenge.atom.repositories;

import com.fractalmedia.codechallenge.atom.models.Movie;
import com.fractalmedia.codechallenge.atom.repositories.responses.ConfigurationResponse;
import com.fractalmedia.codechallenge.atom.repositories.responses.MovieDetailsResponse;
import com.fractalmedia.codechallenge.atom.repositories.responses.MovieListResponse;

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
    Call<MovieDetailsResponse> getMovieDetails(@Query("api_key") String apiKey, @Path("movie_id") int movieId);

    @GET("search/movie")
    Call<MovieListResponse> getMoviesBySearch(@Query("api_key") String apiKey,  @Query("page") Integer PageNo,  @Query("query") String query);
}
