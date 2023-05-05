package com.example.moviemate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TopRatedMoviesAdapter extends RecyclerView.Adapter<TopRatedMoviesAdapter.TopRatedMovieViewHolder> {
    private Context context;
    private List<Movie> topRatedMovies;

    public TopRatedMoviesAdapter(Context context, List<Movie> topRatedMovies) {
        this.context = context;
        this.topRatedMovies = topRatedMovies;
    }

    @NonNull
    @Override
    public TopRatedMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_top_rated_movie, parent, false);
        return new TopRatedMovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopRatedMovieViewHolder holder, int position) {
        Movie movie = topRatedMovies.get(position);

        holder.movieImageView.setImageResource(movie.getPoster());
        holder.movieRatingBar.setRating(movie.getRating());
    }

    @Override
    public int getItemCount() {
        return topRatedMovies.size();
    }

    public static class TopRatedMovieViewHolder extends RecyclerView.ViewHolder {

        private ImageView movieImageView;
        private RatingBar movieRatingBar;

        public TopRatedMovieViewHolder(@NonNull View itemView) {
            super(itemView);
            movieImageView = itemView.findViewById(R.id.movieImageView);
            movieRatingBar = itemView.findViewById(R.id.movieRatingBar);
        }
    }
}