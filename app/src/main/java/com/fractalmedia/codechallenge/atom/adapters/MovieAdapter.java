package com.fractalmedia.codechallenge.atom.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fractalmedia.codechallenge.atom.R;
import com.fractalmedia.codechallenge.atom.constants.Constants;
import com.fractalmedia.codechallenge.atom.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder>  {

    public List<Movie> mMovieList;
    private Context context;

    public MovieAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvTitle.setText(mMovieList.get(position).getTitle());
        holder.tvDesc.setText(mMovieList.get(position).getDescription());
        //TODO: Cargar imagen por url utilizando Picasso
        SharedPreferences sharedPreferences = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        String baseUrl = sharedPreferences.getString(Constants.CONFIGURATION_URL, null);
        String imagePath = mMovieList.get(position).getBackdropPath();
        if (baseUrl != null &&  imagePath != null){
            String url = baseUrl+"w780"+imagePath;
            Picasso.get().load(url).placeholder( R.drawable.progress_animation).error(R.drawable.ic_movie_placeholder).into(holder.ivMovie);
        }
    }

    @Override
    public int getItemCount() {
        return mMovieList == null ? 0 : mMovieList.size();
    }

    public void setData(List<Movie> movieList) {
        this.mMovieList = movieList;
    }

    public static class MyViewHolder  extends RecyclerView.ViewHolder{

        public ImageView ivMovie;
        public TextView tvTitle;
        public TextView tvDesc;


        public MyViewHolder(final View itemView){
            super(itemView);
            this.ivMovie = itemView.findViewById(R.id.ivMovie);
            this.tvTitle = itemView.findViewById(R.id.tvTitle);
            this.tvDesc = itemView.findViewById(R.id.tvDesc);
        }
    }
}
