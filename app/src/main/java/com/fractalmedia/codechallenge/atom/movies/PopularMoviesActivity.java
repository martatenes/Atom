package com.fractalmedia.codechallenge.atom.movies;



import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fractalmedia.codechallenge.atom.R;
import com.fractalmedia.codechallenge.atom.adapters.MovieAdapter;
import com.fractalmedia.codechallenge.atom.models.Movie;

import java.util.List;

public class PopularMoviesActivity extends AppCompatActivity implements PopularMoviesContract.View{
    private PopularMoviesPresenter mPresenter;


    private RecyclerView recyclerView;
    private MovieAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Movie> moviesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popular_movies_activity);
        initUI();
        // Inicializamos presenter
        mPresenter = new PopularMoviesPresenter(this);
        mPresenter.requestMovies();
    }

    private void initUI(){
        setToolBar();
        // Inicializamos recyclerView + adapter
        recyclerView = findViewById(R.id.rvMovies);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MovieAdapter(this);
        recyclerView.setAdapter(mAdapter);
    }

    private void setToolBar() {

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.TR_PELICULAS_POPULARES);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setData(List<Movie> movieList) {
        mAdapter.setData(movieList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResponseFailure(Throwable throwable) {

    }

    @Override
    public void clearResults() {

    }

    @Override
    public void searchResults(String param) {

    }
}