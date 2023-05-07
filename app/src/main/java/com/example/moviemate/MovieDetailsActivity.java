package com.example.moviemate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableCompat;

public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        // Get the selected movie from the intent extras
        Movie movie = getIntent().getParcelableExtra("movie");

        // Log the retrieved movie
        Log.d("MovieDetailsActivity", "Retrieved movie: " + movie.getTitle());

        // Set the movie details in the UI
        AppCompatImageView posterImageView = findViewById(R.id.posterImageView);
        TextView titleTextView = findViewById(R.id.titleTextView);
        TextView releaseDateTextView = findViewById(R.id.releaseDateTextView);
        TextView synopsisTextView = findViewById(R.id.synopsisTextView);

        Log.d("MovieDetailsActivity", "Setting poster image");
        Drawable posterDrawable = AppCompatResources.getDrawable(this, movie.getPoster());
        posterImageView.setImageDrawable(posterDrawable);

        Log.d("MovieDetailsActivity", "Setting title");
        titleTextView.setText(movie.getTitle());

        Log.d("MovieDetailsActivity", "Setting release date");
        releaseDateTextView.setText(movie.getReleaseDate());

        Log.d("MovieDetailsActivity", "Setting synopsis");
        synopsisTextView.setText(movie.getSynopsis());
    }
}