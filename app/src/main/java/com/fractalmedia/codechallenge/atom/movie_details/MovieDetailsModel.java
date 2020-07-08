package com.fractalmedia.codechallenge.atom.movie_details;

import android.util.Log;

import com.fractalmedia.codechallenge.atom.models.Movie;
import com.fractalmedia.codechallenge.atom.repositories.ApiClient;
import com.fractalmedia.codechallenge.atom.repositories.ApiInterface;
import com.fractalmedia.codechallenge.atom.repositories.responses.MovieDetailsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.fractalmedia.codechallenge.atom.constants.Constants.API_KEY;

public class MovieDetailsModel  implements MovieDetailsContract.Model {
    private final String TAG = "MovieDetailsModel";

    @Override
    public void getMovieDetails(OnFinishedListener onFinishedListener, int id) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MovieDetailsResponse> call = apiService.getMovieDetails(API_KEY, id);
        call.enqueue(new Callback<MovieDetailsResponse>() {
            @Override
            public void onResponse(Call<MovieDetailsResponse> call, Response<MovieDetailsResponse> response) {
                if (response.body() != null) {
                    Movie movie = response.body().getMovie();
                    onFinishedListener.onSuccess(movie);
                }else{
                    Log.e(TAG, response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<MovieDetailsResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                onFinishedListener.onFailure(t);
            }
        });
    }
}
