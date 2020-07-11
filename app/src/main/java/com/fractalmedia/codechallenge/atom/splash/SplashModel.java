package com.fractalmedia.codechallenge.atom.splash;

import android.util.Log;

import com.fractalmedia.codechallenge.atom.models.Image;
import com.fractalmedia.codechallenge.atom.repositories.ApiClient;
import com.fractalmedia.codechallenge.atom.repositories.ApiInterface;
import com.fractalmedia.codechallenge.atom.repositories.responses.ConfigurationResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.fractalmedia.codechallenge.atom.constants.Constants.API_KEY;

public class SplashModel implements SplashContract.Model {

    private final String TAG = "SplashModel";

    @Override
    public void getConfiguration(OnFinishedListener onFinishedListener) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ConfigurationResponse> call = apiService.getConfiguration(API_KEY);
        call.enqueue(new Callback<ConfigurationResponse>() {
            @Override
            public void onResponse(Call<ConfigurationResponse> call, Response<ConfigurationResponse> response) {
                Image images = response.body().getImages();
                Log.d(TAG, "Configuration received");
                onFinishedListener.onSuccess(images.getSecureBaseUrl());
            }

            @Override
            public void onFailure(Call<ConfigurationResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
                onFinishedListener.onFailure(t);
            }
        });
    }
}
