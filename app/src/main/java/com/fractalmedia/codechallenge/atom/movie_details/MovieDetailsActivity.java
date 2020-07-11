package com.fractalmedia.codechallenge.atom.movie_details;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fractalmedia.codechallenge.atom.R;
import com.fractalmedia.codechallenge.atom.adapters.CreditAdapter;
import com.fractalmedia.codechallenge.atom.constants.Constants;
import com.fractalmedia.codechallenge.atom.models.Credit;
import com.fractalmedia.codechallenge.atom.models.Genre;
import com.fractalmedia.codechallenge.atom.models.Movie;
import com.fractalmedia.codechallenge.atom.utils.Utils;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import co.lujun.androidtagview.TagContainerLayout;

public class MovieDetailsActivity extends AppCompatActivity implements MovieDetailsContract.View{

    private Movie movie;
    private MovieDetailsPresenter mPresenter;
    private ProgressBar progressBar;
    private TextView tvTitle, tvOverview, tvDuration, tvRelease;
    private TagContainerLayout tagGenres;
    private ImageView ivMovie, ivAdults;
    private RelativeLayout rlMovie;
    private String imageBaseURL;
    private CreditAdapter mAdapter;
    RecyclerView rvCredits;
    private final String TAG = "MovieDetailsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        imageBaseURL = Utils.getBaseUrlImages(this) + "w780";
        if (getIntent().hasExtra(Constants.EXTRA_MOVIE)) {
            movie = (Movie) getIntent().getExtras().getSerializable(Constants.EXTRA_MOVIE);
        }
        initUI();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter = new MovieDetailsPresenter(this);
        if (movie != null) {
            mPresenter.requestMovie(movie.getId());
            mPresenter.requestCredits(movie.getId());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    private void initUI(){
        setToolBar();
        tvTitle = findViewById(R.id.tvTitle);
        tvDuration = findViewById(R.id.tvDuration);
        tvOverview = findViewById(R.id.tvOverview);
        tvRelease = findViewById(R.id.tvRelease);
        progressBar = findViewById(R.id.progressBar);
        ivAdults = findViewById(R.id.ivAdults);
        ivMovie = findViewById(R.id.ivMovie);
        tagGenres = findViewById(R.id.tagGenres);
        rlMovie = findViewById(R.id.rlMovie);
        rlMovie.setVisibility(View.GONE);
        rvCredits = findViewById(R.id.rvCredits);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false); // Recyclerview horizontal
        rvCredits.setLayoutManager(layoutManager);
        mAdapter = new CreditAdapter(this);
        rvCredits.setAdapter(mAdapter);
    }

    private void setToolBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(movie.getTitle());
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
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
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        String duration = String.format(Locale.getDefault(), "%d min", movie.getRuntime());
        tvDuration.setText(duration);
        tvRelease.setText(movie.getReleaseDate());

        for(Genre g : movie.getGenreList()){
            tagGenres.addTag(g.getName());
        }

        ivAdults.setVisibility(movie.isAdult() ? View.VISIBLE: View.INVISIBLE);
        Picasso.get().load(imageBaseURL + movie.getBackdropPath()).placeholder(R.drawable.progress_animation).error(R.drawable.ic_movie_placeholder).into(ivMovie);

        rlMovie.setVisibility(View.VISIBLE);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {

        rlMovie.setVisibility(View.GONE);
        Utils.ShowSimpleAlert(this, getString(R.string.TR_ERROR),  getString(R.string.TR_OCURRIO_ERROR_OBTENER_INFO));

    }

    @Override
    public void setCredits(List<Credit> credits) {
        mAdapter.setCreditList(credits);
    }
}