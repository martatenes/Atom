package com.fractalmedia.codechallenge.atom.movie_details;

import com.fractalmedia.codechallenge.atom.models.Movie;


public class MovieDetailsContract {

    public interface Model{

        interface OnFinishedListener{
            void onSuccess (Movie movie);
            void onFailure(Throwable t);
        }

        void getMovieDetails(MovieDetailsContract.Model.OnFinishedListener onFinishedListener, long id);
    }

    public interface View{
        // Spinner
        void showProgress();
        void hideProgress();

        void setData(Movie movie);
        void onResponseFailure(Throwable throwable);


    }

    public interface Presenter{
        void onDestroy();
        void requestMovie(long id);

    }
}
