package com.example.moviemate;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<Movie> movies;

    public MovieAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.movieImageView.setImageResource(movie.getImageResource());
        holder.movieTitleTextView.setText(movie.getTitle());
        holder.movieDescriptionTextView.setText(movie.getDescription());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        public ImageView movieImageView;
        public TextView movieTitleTextView;
        public TextView movieDescriptionTextView;

        public MovieViewHolder(View view) {
            super(view);
            movieImageView = view.findViewById(R.id.movieImageView);
            movieTitleTextView = view.findViewById(R.id.titleTextView);
            movieDescriptionTextView = view.findViewById(R.id.descriptionTextView);
        }
    }
}
