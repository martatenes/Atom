package com.fractalmedia.codechallenge.atom.movie_details;

import com.fractalmedia.codechallenge.atom.models.Credit;
import com.fractalmedia.codechallenge.atom.models.Movie;

import java.util.List;


public class MovieDetailsContract {

    public interface Model{

        interface OnFinishedListener{
            void onSuccessMovie (Movie movie);
            void onFailureMovie(Throwable t);
            void onSuccessCredits (List<Credit> credits);
            void onFailureCredits(Throwable t);
        }

        void getMovieDetails(MovieDetailsContract.Model.OnFinishedListener onFinishedListener, long id);
        void getMovieCredits(MovieDetailsContract.Model.OnFinishedListener onFinishedListener, long id);
    }

    public interface View{
        // Spinner
        void showProgress();
        void hideProgress();

        void setData(Movie movie);
        void onResponseFailure(Throwable throwable);


        void setCredits(List<Credit> credits);
    }

    public interface Presenter{
        void onDestroy();
        void requestMovie(long id);
        void requestCredits(long movieId);

    }
}
