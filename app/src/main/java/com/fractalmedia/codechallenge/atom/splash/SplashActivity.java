package com.fractalmedia.codechallenge.atom.splash;
import com.fractalmedia.codechallenge.atom.R;
import com.fractalmedia.codechallenge.atom.constants.Constants;
import com.fractalmedia.codechallenge.atom.movies.PopularMoviesActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity implements SplashContract.View{

    SplashPresenter mPresenter;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initUI();
        mPresenter = new SplashPresenter(this);

        sharedPref = getSharedPreferences("pref", Context.MODE_PRIVATE);
        // Decidimos si mostramos el activity de movies o si pedimos la configuración
        if (sharedPref.getString(Constants.CONFIGURATION_URL, null) != null){
            showPopularMovies();
        }else{
            mPresenter.requestConfiguration();
        }
    }

    private void initUI(){
        progressBar = findViewById(R.id.progressBar);
    }
    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void setConfiguration(String baseImagesUrl) {
        editor = sharedPref.edit();
        editor.putString(Constants.CONFIGURATION_URL, baseImagesUrl);
        editor.apply();
        showPopularMovies();
    }


    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(this, R.string.TR_OCURRIO_ERROR_CONFIGURACION, Toast.LENGTH_LONG).show();
    }

    public void showPopularMovies() {
        Intent intent = new Intent(this, PopularMoviesActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}