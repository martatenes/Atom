package com.fractalmedia.codechallenge.atom.movie_details;

import com.fractalmedia.codechallenge.atom.models.Credit;
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
    public void requestCredits(long movieId) {
        if (movieDetailsView != null) {
            movieDetailsView.showProgress();
        }
        movieDetailsModel.getMovieCredits(this, movieId);
    }


    @Override
    public void onSuccessMovie(Movie movie) {
        if (movieDetailsView != null) {
            movieDetailsView.hideProgress();
            movieDetailsView.setData(movie);
        }
    }



    @Override
    public void onFailureMovie(Throwable t) {

        movieDetailsView.onResponseFailure(t);
        if (movieDetailsView != null) {
            movieDetailsView.hideProgress();
        }
    }

    @Override
    public void onSuccessCredits(List<Credit> credits) {
        if (movieDetailsView != null) {
            movieDetailsView.hideProgress();
            movieDetailsView.setCredits(credits);
        }
    }

    @Override
    public void onFailureCredits(Throwable t) {
        movieDetailsView.onResponseFailure(t);
        if (movieDetailsView != null) {
            movieDetailsView.hideProgress();
        }
    }

}
