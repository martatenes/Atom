package com.fractalmedia.codechallenge.atom.movies;



import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SearchView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.fractalmedia.codechallenge.atom.R;
import com.fractalmedia.codechallenge.atom.adapters.MovieAdapter;
import com.fractalmedia.codechallenge.atom.models.Movie;

import java.util.List;

public class PopularMoviesActivity extends AppCompatActivity implements PopularMoviesContract.View, SwipeRefreshLayout.OnRefreshListener {
    private PopularMoviesPresenter mPresenter;

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private MovieAdapter mAdapter;
    private GridLayoutManager layoutManager;

    // Paginación
    private int currentPage = 1;
    private boolean isLastPage = false;
    private int totalPages = 1;
    private Boolean isLoading = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popular_movies_activity);
        initUI();
        // Inicializamos presenter
        mPresenter = new PopularMoviesPresenter(this);
        mPresenter.requestMovies(1);
    }

    private void initUI(){
        setToolBar();
        // Inicializamos recyclerView + adapter
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        recyclerView = findViewById(R.id.rvMovies);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MovieAdapter(this);
        recyclerView.setAdapter(mAdapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mAdapter.clearAll(); // Nos cargamos todos los datos por si habíamos paginado
                mAdapter.notifyDataSetChanged();
                currentPage = 1;
                mPresenter.requestMovies(currentPage);
            }
        });
        recyclerView.addOnScrollListener(new PaginationScrollListener(layoutManager) {

            @Override
            protected void loadMoreItems() {

                if (!isLastPage) {
                    isLoading = true;
                    currentPage += 1;
                    mPresenter.requestMovies(currentPage);
                }
            }

            @Override
            protected boolean isLastPage() {
                return currentPage == totalPages;
            }

            @Override
            protected boolean isLoading() {
                return isLoading;
            }
        });
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



    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.popular_movies_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("A", "A buscar");
                mAdapter.filterItems(query);
                //TODO: Llamar al api rest Search Movies
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("Busqueda", newText);
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
        mAdapter.setData(movieList);
        mAdapter.notifyDataSetChanged();
        isLoading = false;
        swipeRefreshLayout.setRefreshing(false);

    }


    @Override
    public void onResponseFailure(Throwable throwable) {
        isLoading = false;
        swipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void clearResults() {

    }

    @Override
    public void searchResults(String param) {

    }

    @Override
    public void onRefresh() {

    }

    public abstract static class PaginationScrollListener extends RecyclerView.OnScrollListener {
        private GridLayoutManager layoutManager;
        public PaginationScrollListener(GridLayoutManager layoutManager) {
            this.layoutManager = layoutManager;
        }
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
            if (!isLoading() && !isLastPage()) {
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0) {
                    loadMoreItems();
                }
            }
        }

        protected abstract void loadMoreItems();

        protected abstract boolean isLastPage();

        protected abstract boolean isLoading();
    }
}