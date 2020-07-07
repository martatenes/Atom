package com.fractalmedia.codechallenge.atom.movies;

import com.fractalmedia.codechallenge.atom.models.Movie;

import java.util.List;
// Describe la comunicación entre la vista y el presenter
public class PopularMoviesContract {

    public interface Model{

        interface OnFinishedListener{
            void onSuccess (List<Movie> movieList, int currentPage, int totalPages);
            void onFailure(Throwable t);
        }

        void getMoviesList(OnFinishedListener onFinishedListener, int numPage);
        void getMoviesListByQuery(OnFinishedListener onFinishedListener, int numPage, String query);
    }

    public interface View{
        // Spinner
        void showProgress();
        void hideProgress();

        // Asignación de datos a RecyclerView
        void setPaginationData(int currentPage, int totalPages);
        void setData(List<Movie> movieList);
        void onResponseFailure(Throwable throwable);


    }

    public interface Presenter{
        void onDestroy();
        void requestMovies(int numPage); // Pedimos más datos al api rest

    }
}
