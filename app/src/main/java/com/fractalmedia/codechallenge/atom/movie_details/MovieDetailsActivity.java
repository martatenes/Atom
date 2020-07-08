package com.fractalmedia.codechallenge.atom.movie_details;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.fractalmedia.codechallenge.atom.R;
import com.fractalmedia.codechallenge.atom.constants.Constants;
import com.fractalmedia.codechallenge.atom.models.Movie;

public class MovieDetailsActivity extends AppCompatActivity implements MovieDetailsContract.View{

    private Movie movie;
    private MovieDetailsPresenter mPresenter;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        if (getIntent().hasExtra(Constants.EXTRA_MOVIE)) {
            movie = (Movie) getIntent().getExtras().getSerializable(Constants.EXTRA_MOVIE);
        }
        initUI();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter = new MovieDetailsPresenter(this);
        if (movie != null)
            mPresenter.requestMovie(movie.getId());
    }

    private void initUI(){
        setToolBar();
        progressBar = findViewById(R.id.progressBar);
    }

    private void setToolBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(movie.getTitle());
            actionBar.setDisplayShowHomeEnabled(true);
        }
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
    public void setData(Movie movie) {

    }

    @Override
    public void onResponseFailure(Throwable throwable) {

    }
}