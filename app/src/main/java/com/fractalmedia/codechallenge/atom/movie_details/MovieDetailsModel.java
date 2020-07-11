package com.fractalmedia.codechallenge.atom.movie_details;

import android.util.Log;

import com.fractalmedia.codechallenge.atom.models.Credit;
import com.fractalmedia.codechallenge.atom.models.Movie;
import com.fractalmedia.codechallenge.atom.repositories.ApiClient;
import com.fractalmedia.codechallenge.atom.repositories.ApiInterface;
import com.fractalmedia.codechallenge.atom.repositories.responses.MovieCreditsResponse;
import com.fractalmedia.codechallenge.atom.repositories.responses.MovieListResponse;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.fractalmedia.codechallenge.atom.constants.Constants.API_KEY;

public class MovieDetailsModel  implements MovieDetailsContract.Model {
    private final String TAG = "MovieDetailsModel";

    @Override
    public void getMovieDetails(OnFinishedListener onFinishedListener, long id) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<JsonElement> call = apiService.getMovieDetails(id, API_KEY);
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if (response.body() != null) {
                    try {
                        Movie movie = new Gson().fromJson(response.body(), Movie.class);
                        onFinishedListener.onSuccessMovie(movie);
                    }
                    catch(Exception ex){
                        Log.e(TAG, ex.toString());
                    }

                }else{
                    Log.e(TAG, response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Log.e(TAG, t.toString());
                onFinishedListener.onFailureMovie(t);
            }
        });
    }

    @Override
    public void getMovieCredits(OnFinishedListener onFinishedListener, long id) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MovieCreditsResponse> call = apiService.getMovieCredits(id, API_KEY);
        call.enqueue(new Callback<MovieCreditsResponse>() {
            @Override
            public void onResponse(Call<MovieCreditsResponse> call, Response<MovieCreditsResponse> response) {
                if (response.body() != null) {
                    try {
                        List<Credit> creditList = response.body().getCast();
                        onFinishedListener.onSuccessCredits(creditList);
                    }
                    catch(Exception ex){
                        Log.e(TAG, ex.toString());
                    }

                }else{
                    Log.e(TAG, response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<MovieCreditsResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
                onFinishedListener.onFailureCredits(t);
            }
        });
    }
}
