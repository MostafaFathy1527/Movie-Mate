package com.example.moviemate;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {
    private List<Movie> mMovies;

    // Define a MovieClickListener interface
    public interface MovieClickListener {
        void onMovieClick(int position);
    }

    // Add a MovieClickListener field to the adapter
    private final MovieClickListener mMovieClickListener;

    // Add a constructor that accepts a List of movies and a MovieClickListener
    public MovieListAdapter(List<Movie> movies, MovieClickListener movieClickListener) {
        mMovies = movies;
        mMovieClickListener = movieClickListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        // Get the movie at the given position
        Movie movie = mMovies.get(position);

        // Set the movie poster, title, release date, and synopsis in the ViewHolder views
        holder.posterImageView.setImageResource(movie.getPoster());
        holder.titleTextView.setText(movie.getTitle());
        holder.releaseDateTextView.setText(movie.getReleaseDate());
        holder.synopsisTextView.setText(movie.getSynopsis());

        // Set the click listener for the item view
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the onMovieClick method of the MovieClickListener interface
                mMovieClickListener.onMovieClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView posterImageView;
        TextView titleTextView;
        TextView releaseDateTextView;
        TextView synopsisTextView;

        public MovieViewHolder(View itemView) {
            super(itemView);
            posterImageView = itemView.findViewById(R.id.posterImageView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            releaseDateTextView = itemView.findViewById(R.id.releaseDateTextView);
            synopsisTextView = itemView.findViewById(R.id.synopsisTextView);
        }
    }
}