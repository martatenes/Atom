package com.fractalmedia.codechallenge.atom.movies;

import com.fractalmedia.codechallenge.atom.models.Movie;

import java.util.List;

public class PopularMoviesPresenter implements PopularMoviesContract.Presenter, PopularMoviesContract.Model.OnFinishedListener {

    private PopularMoviesContract.View movieListView;

    private PopularMoviesContract.Model movieListModel;

    public PopularMoviesPresenter(PopularMoviesContract.View movieListView) {
        this.movieListView = movieListView;
        movieListModel = new PopularMoviesModel();
    }

    @Override
    public void onDestroy() {
        this.movieListView = null;
    }


   


    @Override
    public void requestMovies(int numPage) {

        if (movieListView != null) {
            movieListView.showProgress();
        }
        movieListModel.getMoviesList(this, numPage);
    }

    @Override
    public void onSuccess(List<Movie> movieArrayList, int currentPage, int totalPages) {
        if (movieListView != null) {
            movieListView.hideProgress();
            movieListView.setPaginationData(currentPage, totalPages);
            movieListView.setData(movieArrayList);
        }
    }



    @Override
    public void onFailure(Throwable t) {

        movieListView.onResponseFailure(t);
        if (movieListView != null) {
            movieListView.hideProgress();
        }
    }
}