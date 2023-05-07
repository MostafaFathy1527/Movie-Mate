package com.example.moviemate;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {

    private Context context;
    private List<Movie> movies;
    private MovieClickListener clickListener;

    public MovieListAdapter(Context context, List<Movie> movies, MovieClickListener clickListener) {
        this.context = context;
        this.movies = movies;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_list_item, parent, false);
        return new MovieViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.bindData(movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView titleTextView;
        private TextView releaseDateTextView;
        private TextView synopsisTextView;
        private ImageView posterImageView;
        private MovieClickListener clickListener;

        public MovieViewHolder(@NonNull View itemView, MovieClickListener clickListener) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            releaseDateTextView = itemView.findViewById(R.id.releaseDateTextView);
            synopsisTextView = itemView.findViewById(R.id.synopsisTextView);
            posterImageView = itemView.findViewById(R.id.posterImageView);
            this.clickListener = clickListener;

            // Set the click listener for the item view
            itemView.setOnClickListener(this);
        }

        // Bind the data to the ViewHolder
        public void bindData(Movie movie) {
            titleTextView.setText(movie.getTitle());
            releaseDateTextView.setText(movie.getReleaseDate());
            synopsisTextView.setText(movie.getSynopsis());
            posterImageView.setImageResource(movie.getPoster());
        }

        // Handle clicks on the item view
        @Override
        public void onClick(View v) {
            // Call the onMovieClick method of the click listener with the position of the clicked item
            clickListener.onMovieClick(getAdapterPosition());
        }
    }

    public interface MovieClickListener {
        void onMovieClick(int position);
    }
}