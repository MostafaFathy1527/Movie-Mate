package com.example.moviemate;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsActivity extends AppCompatActivity {

    private List<Movie> allMovies;
    private List<Movie> searchResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        // Initialize movie data
        allMovies = getMovieData();

        // Get search query from intent extra
        String query = getIntent().getStringExtra("query");

        // Filter movie data based on search query
        searchResults = filterMovies(query);


        // Set up RecyclerView and adapter
        RecyclerView searchResultsRecyclerView = findViewById(R.id.search_results_list);
        MovieAdapter searchResultsAdapter = new MovieAdapter(SearchResultsActivity.this, searchResults);
        searchResultsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchResultsRecyclerView.setAdapter(searchResultsAdapter);

        // Set click listener for movie items
        searchResultsAdapter.setOnItemClickListener(new MovieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                launchMovieDetailsActivity(searchResults.get(position));
            }
        });
    }

    private List<Movie> getMovieData() {
        // Initialize movie data as a List<Movie> object
        // This can be loaded from a database or API
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("The Shawshank Redemption", "Drama", "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.", R.drawable.shawshank_redemption_poster));
        movies.add(new Movie("The Godfather", "Crime", "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.", R.drawable.godfather_poster));
        movies.add(new Movie("The Dark Knight", "Action", "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.", R.drawable.dark_knight_poster));

        movies.add(new Movie("Schindler's List", "Drama", "In German-occupied Poland during World War II, industrialist Oskar Schindler gradually becomes concerned for his Jewish workforce after witnessing their persecution by the Nazis.", R.drawable.schindlers_list_poster));

        movies.add(new Movie("Forrest Gump", "Drama", "The presidencies of Kennedy and Johnson, the events of Vietnam, Watergate, and other history unfold through the perspective of an Alabama man with an IQ of 75.", R.drawable.forrest_gump_poster));
        movies.add(new Movie("The Matrix", "Action", "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.", R.drawable.matrix_poster));
        return movies;
    }

    private List<Movie> filterMovies(String query) {
        // Filter movie data based on search query
        List<Movie> filteredMovies = new ArrayList<>();
        for (Movie movie : allMovies) {
            if (movie.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                    movie.getCategory().toLowerCase().contains(query.toLowerCase())) {
                filteredMovies.add(movie);
            }
        }
        return filteredMovies;
    }

    private void launchMovieDetailsActivity(Movie movie) {
        // Launch MovieDetailsActivity and pass movie as intent extra
        Intent intent = new Intent(SearchResultsActivity.this, MovieDetailsActivity.class);
        intent.putExtra("movie", movie);
        startActivity(intent);
    }
}