package com.fractalmedia.codechallenge.atom.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fractalmedia.codechallenge.atom.R;
import com.fractalmedia.codechallenge.atom.constants.Constants;
import com.fractalmedia.codechallenge.atom.models.Movie;
import com.fractalmedia.codechallenge.atom.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    public interface IMovieClickListener{
        void onClick(Movie movie);
    }
    public List<Movie> mMovieList;
    private Context mContext;
    private IMovieClickListener mListener;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private String baseUrl;

    public MovieAdapter(Context context, IMovieClickListener listener){
        this.mContext = context;
        this.mListener = listener;
        this.mMovieList = new ArrayList<>();
        this.baseUrl = Utils.getBaseUrlImages(context) + "w780"; //TODO: Sería mejor crear una clase para clasificar los tamaños de las imágenes
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
            return new MovieViewHolder(view);
        }
        else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
    }
    public int getItemViewType(int position) {
        return mMovieList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MovieViewHolder) {
            MovieViewHolder movieHolder = (MovieViewHolder) holder;
            movieHolder.tvTitle.setText(mMovieList.get(position).getTitle());
            movieHolder.tvDesc.setText(mMovieList.get(position).getOverview());

            String imagePath = mMovieList.get(position).getBackdropPath();
            if (baseUrl != null && imagePath != null) {
                Picasso.get().load(baseUrl + imagePath).placeholder(R.drawable.progress_animation).error(R.drawable.ic_movie_placeholder).into(movieHolder.ivMovie);
            }

            ((MovieViewHolder) holder).cardView.setOnClickListener(v -> mListener.onClick(mMovieList.get(position)));

        }else{
            showLoadingView((LoadingViewHolder) holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return mMovieList == null ? 0 : mMovieList.size();
    }

    public void addMovies(List<Movie> movieList) {
        this.mMovieList.addAll(movieList);
        notifyDataSetChanged();
    }

    public void clearAll() {
        mMovieList.clear();
        notifyDataSetChanged();
    }


    private static class MovieViewHolder  extends RecyclerView.ViewHolder {

        public CardView cardView;
        public ImageView ivMovie;
        public TextView tvTitle;
        public TextView tvDesc;

        public MovieViewHolder(final View itemView){
            super(itemView);
            this.cardView = itemView.findViewById(R.id.cardView);
            this.ivMovie = itemView.findViewById(R.id.ivMovie);
            this.tvTitle = itemView.findViewById(R.id.tvTitle);
            this.tvDesc = itemView.findViewById(R.id.tvDesc);
        }
    }

    private static class LoadingViewHolder extends RecyclerView.ViewHolder {

        ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }

    private void showLoadingView(LoadingViewHolder viewHolder, int position) {
        //ProgressBar would be displayed

    }
}
