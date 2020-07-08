package com.fractalmedia.codechallenge.atom.movie_details;

import com.fractalmedia.codechallenge.atom.models.Movie;
import com.fractalmedia.codechallenge.atom.movies.PopularMoviesContract;
import com.fractalmedia.codechallenge.atom.movies.PopularMoviesModel;

import java.util.List;

public class MovieDetailsPresenter implements MovieDetailsContract.Presenter, MovieDetailsContract.Model.OnFinishedListener {

    private MovieDetailsContract.View movieDetailsView;

    private MovieDetailsContract.Model movieDetailsModel;

    public MovieDetailsPresenter(MovieDetailsContract.View view) {
        this.movieDetailsView = view;
        movieDetailsModel = new MovieDetailsModel();
    }

    @Override
    public void onDestroy() {
        this.movieDetailsView = null;
    }




    @Override
    public void requestMovie(long id) {

        if (movieDetailsView != null) {
            movieDetailsView.showProgress();
        }
        movieDetailsModel.getMovieDetails(this, id);
    }


    @Override
    public void onSuccess(Movie movie) {
        if (movieDetailsView != null) {
            movieDetailsView.hideProgress();
            movieDetailsView.setData(movie);
        }
    }



    @Override
    public void onFailure(Throwable t) {

        movieDetailsView.onResponseFailure(t);
        if (movieDetailsView != null) {
            movieDetailsView.hideProgress();
        }
    }

}
