package com.example.moviemate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MovieDetailsActivity extends AppCompatActivity {

    private TextView titleTextView;
    private TextView releaseDateTextView;
    private TextView synopsisTextView;
    private TextView castTextView;
    private ImageView posterImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        // Get the selected movie from the intent that started this activity
        Intent intent = getIntent();
        Movie selectedMovie = (Movie) intent.getSerializableExtra("movie");

        // Initialize the views in the activity layout
        titleTextView = findViewById(R.id.titleTextView);
        releaseDateTextView = findViewById(R.id.releaseDateTextView);
        synopsisTextView = findViewById(R.id.synopsisTextView);
        castTextView = findViewById(R.id.castTextView);
        posterImageView = findViewById(R.id.posterImageView);

        // Set the text content and image for each view
        titleTextView.setText(selectedMovie.getTitle());
        releaseDateTextView.setText(selectedMovie.getReleaseDate());
        synopsisTextView.setText(selectedMovie.getSynopsis());
        castTextView.setText(TextUtils.join(", ", selectedMovie.getCast()));
        posterImageView.setImageResource(selectedMovie.getPoster());
    }
}