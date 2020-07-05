package com.fractalmedia.codechallenge.atom.movies;

import com.fractalmedia.codechallenge.atom.models.Movie;

import java.util.List;
// Describe la comunicación entre la vista y el presenter
public class PopularMoviesContract {

    public interface Model{
        interface OnFinishedListener{
            void onSuccess (List<Movie> movieList);
            void onFailure(Throwable t);
        }

        void getMoviesList(OnFinishedListener onFinishedListener, int numPage);
    }

    public interface View{
        // Spinner
        void showProgress();
        void hideProgress();

        // Asignación de datos a RecyclerView
        void setData(List<Movie> movieList);
        void onResponseFailure(Throwable throwable);

        // Búsqueda
        void clearResults();
        void searchResults(String param);
    }

    public interface Presenter{
        void onDestroy();
        void getMoreMovies(int numPage); // Paginación
        void requestMovies(); // Pedimos más datos al api rest
    }
}
