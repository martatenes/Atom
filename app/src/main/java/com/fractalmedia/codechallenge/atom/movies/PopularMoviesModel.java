package com.fractalmedia.codechallenge.atom.movies;

import android.util.Log;

import com.fractalmedia.codechallenge.atom.models.Movie;
import com.fractalmedia.codechallenge.atom.repositories.ApiClient;
import com.fractalmedia.codechallenge.atom.repositories.ApiInterface;
import com.fractalmedia.codechallenge.atom.repositories.responses.MovieListResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.fractalmedia.codechallenge.atom.constants.Constants.API_KEY;

public class PopularMoviesModel implements PopularMoviesContract.Model {
    private final String TAG = "MoviesListModel";

    @Override
    public void getMoviesList(OnFinishedListener onFinishedListener, int numPage) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MovieListResponse> call = apiService.getPopularMovies(API_KEY, numPage);
        call.enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
                List<Movie> movies = response.body().getMovies() != null ? response.body().getMovies() : new ArrayList<>();
                int currentPage = response.body().getPage();
                int totalPages = response.body().getTotalPages();
                onFinishedListener.onSuccess(movies, currentPage, totalPages);
            }

            @Override
            public void onFailure(Call<MovieListResponse> call, Throwable t) {
                // TODO: Aquí sería mejor gestionar la respuesta en base a los errorCode devueltos por el API. Por simplicidad devolveremos el thrown
                Log.e(TAG, t.toString());
                onFinishedListener.onFailure(t);
            }
        });
    }

    @Override
    public void getMoviesListByQuery(OnFinishedListener onFinishedListener, int numPage, String query) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MovieListResponse> call = apiService.getMoviesBySearch(API_KEY, numPage, query);
        call.enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
                if (response.body() != null) {
                    List<Movie> movies = response.body().getMovies() != null ? response.body().getMovies() : new ArrayList<>();
                    int currentPage = response.body().getPage();
                    int totalPages = response.body().getTotalPages();
                    Log.d(TAG, "Number of movies received: " + movies.size());
                    onFinishedListener.onSuccess(movies, currentPage, totalPages);
                }else{
                    Log.e(TAG, response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<MovieListResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                onFinishedListener.onFailure(t);
            }
        });
    }
}
