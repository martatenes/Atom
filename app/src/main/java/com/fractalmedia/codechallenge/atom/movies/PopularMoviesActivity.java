package com.fractalmedia.codechallenge.atom.movies;



import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.fractalmedia.codechallenge.atom.R;
import com.fractalmedia.codechallenge.atom.adapters.MovieAdapter;
import com.fractalmedia.codechallenge.atom.constants.Constants;
import com.fractalmedia.codechallenge.atom.models.Movie;
import com.fractalmedia.codechallenge.atom.movie_details.MovieDetailsActivity;
import com.fractalmedia.codechallenge.atom.utils.Utils;

import java.util.List;

public class PopularMoviesActivity extends AppCompatActivity implements PopularMoviesContract.View {
    private PopularMoviesPresenter mPresenter;

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private MovieAdapter mAdapter;
    private GridLayoutManager layoutManager;
    private SearchView searchView;
    private ProgressBar progressBar;
    private TextView tvError;

    // Paginación
    private int currentPage = 1;
    private boolean isLastPage = false;
    private int totalPages = 1;
    private Boolean isLoading = false;
    private Boolean isSearchActive = false;
    private String searchQuery = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popular_movies_activity);
        initUI();
        setUpListeners();

    }

    @Override
    protected void onStart() {
        super.onStart();
        // Inicializamos presenter
        mPresenter = new PopularMoviesPresenter(this);
        mPresenter.requestMovies(1);
    }

    @Override
    public void onBackPressed() {
        if (!searchView.isIconified()){ // Cerramos el searchView si pulsamos el botón de atrás
            searchView.onActionViewCollapsed();
        }else{
            super.onBackPressed();
        }
        isSearchActive = false;
    }

    private void initUI() {
        setToolBar();
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        progressBar = findViewById(R.id.progressBar);
        tvError = findViewById(R.id.tvError);
        tvError.setVisibility(View.GONE);
        recyclerView = findViewById(R.id.rvMovies);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MovieAdapter(this, this::OnClickMovie);
        recyclerView.setAdapter(mAdapter);
    }

    public void OnClickMovie(Movie movie){
        Log.d("Movie", movie.getTitle());
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra(Constants.EXTRA_MOVIE, movie);
        startActivity(intent);
    }

    private void setToolBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.TR_PELICULAS_POPULARES);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    private void setUpListeners() {
        swipeRefreshLayout.setOnRefreshListener(() -> {
            mAdapter.clearAll(); // Nos cargamos todos los datos por si habíamos paginado
            tvError.setVisibility(View.GONE);
            if (isSearchActive) {
                mPresenter.requestMoviesByQuery(1, searchQuery);
            } else {
                mPresenter.requestMovies(1);
            }
        });


        // Listener para la paginación
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();


                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if (!isLoading)
                {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0) // Hemos llegado al final de la lista
                    {
                        if (currentPage +1 <= totalPages) { // Si cambiamos de página y nos pasamos del total
                            currentPage+=1;
                            isLoading = true;
                            tvError.setVisibility(View.GONE);
                            if (isSearchActive) {
                                mPresenter.requestMoviesByQuery(currentPage, searchQuery);
                            } else {
                                mPresenter.requestMovies(currentPage);
                            }
                        }
                    }
                }
            }
        });
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.popular_movies_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnCloseListener(() -> { // Al cerrar la búsqueda volvemos a obtener las películas
            if (!searchView.isIconified()) {
                searchView.onActionViewCollapsed();
                isSearchActive = false;
                mAdapter.clearAll();
                mPresenter.requestMovies(1);
            }
            return true;
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchQuery = query; // Nos guardamos la query para tenerla disponible en las funciones de paginación
                isSearchActive = query != null && !query.isEmpty();
                if (isSearchActive) {
                    // Borramos los datos que hubiesen antes
                    mAdapter.clearAll();
                    mPresenter.requestMoviesByQuery(1, query);
                }
                else{
                    mPresenter.requestMovies(currentPage);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("Busqueda", newText);
                searchQuery = newText;
                return false;
            }
        });
        return true;
    }

    @Override
    public void setPaginationData(int currentPage, int totalPages) {
        this.currentPage = currentPage;
        this.totalPages = totalPages;
    }

    @Override
    public void setData(List<Movie> movieList) {
        mAdapter.addMovies(movieList); // Las añadimos
        swipeRefreshLayout.setRefreshing(false);
        isLoading = false;

        // Si teníamos la búsqueda activa y no hemos obtenido resultados mostramos un mensaje
        if(isSearchActive && movieList.size() == 0){
            tvError.setText(R.string.TR_NO_SE_HAN_ENCONTRADO_PELICULAS);
            tvError.setVisibility(View.VISIBLE);        }

    }

    // TODO: Aquí sería mejor gestionar la respuesta en base a los errorCode devueltos por el API. Por simplicidad mostraremos un mensaje genérico
    @Override
    public void onResponseFailure(Throwable throwable) {
        swipeRefreshLayout.setRefreshing(false);
        isLoading = false;
        Utils.ShowSimpleAlert(this, getString(R.string.TR_ERROR),  getString(R.string.TR_OCURRIO_ERROR_CONFIGURACION), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("HOLA", "HOLA");
            }
        });
        Utils.ShowSimpleAlert(this, getString(R.string.TR_ERROR),  getString(R.string.TR_OCURRIO_ERROR_CONFIGURACION));
    }


}