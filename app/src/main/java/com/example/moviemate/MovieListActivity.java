package com.example.moviemate;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class MovieListActivity extends AppCompatActivity implements MovieListAdapter.MovieClickListener {

    private RecyclerView movieRecyclerView;
    private List<Movie> movieList;
    private MovieListAdapter movieListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_list_activity);

        // Get the selected category from the intent that started this activity
        Intent intent = getIntent();
        String selectedCategory = intent.getStringExtra("category");

        // Initialize the views in the activity layout
        movieRecyclerView = findViewById(R.id.movieListRecyclerView);

        // Initialize the list of movies and the movieListAdapter
        movieList = new ArrayList<>();
        movieListAdapter = new MovieListAdapter(movieList, this); // Pass the activity as the MovieClickListener

        // Set the layout manager and adapter for the movieRecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        movieRecyclerView.setLayoutManager(layoutManager);
        movieRecyclerView.setAdapter(movieListAdapter);

        // Populate the movieList with the movies for the selected category
        if (selectedCategory.equals("Action")) {
            movieList.add(new Movie("The Dark Knight", "When the menace known as The Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham.", "2008", R.drawable.dark_knight_poster, Arrays.asList("Christian Bale", "Heath Ledger", "Aaron Eckhart")));
            movieList.add(new Movie("Die Hard", "An NYPD officer tries to save his wife and several others taken hostage by German terrorists during a Christmas party at the Nakatomi Plaza in Los Angeles.", "1988", R.drawable.die_hard_poster, Arrays.asList("Bruce Willis", "Alan Rickman", "Bonnie Bedelia")));
            movieList.add(new Movie("Kill Bill: Vol. 1", "After awakening from a four-year coma, a former assassin wreaks vengeance on the team of assassins who betrayed her.", "2003", R.drawable.kill_bill_poster, Arrays.asList("Uma Thurman", "Lucy Liu", "Vivica A. Fox")));
            movieList.add(new Movie("The Matrix", "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.", "1999", R.drawable.matrix_poster, Arrays.asList("Keanu Reeves", "Laurence Fishburne", "Carrie-Anne Moss")));
        } else if (selectedCategory.equals("Drama")) {
            movieList.add(new Movie("Forrest Gump", "The presidencies of Kennedy and Johnson, the events of Vietnam, Watergate, and other history unfold through the perspective of an Alabama man with an IQ of 75.", "1994", R.drawable.forrest_gump_poster, Arrays.asList("Tom Hanks", "Robin Wright", "Gary Sinise")));
            movieList.add(new Movie("The Shawshank Redemption", "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.", "1994", R.drawable.shawshank_redemption_poster, Arrays.asList("Tim Robbins", "Morgan Freeman", "Bob Gunton")));
            movieList.add(new Movie("The Godfather", "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.", "1972", R.drawable.godfather_poster, Arrays.asList("Marlon Brando", "Al Pacino", "James Caan")));
            movieList.add(new Movie("Schindler's List", "In German-occupied Poland during World War II, industrialist Oskar Schindler gradually becomes concerned for his Jewish workforce after witnessing their persecution by the Nazis.", "1993", R.drawable.schindlers_list_poster, Arrays.asList("Liam Neeson", "Ralph Fiennes", "Ben Kingsley")));
        } else if (selectedCategory.equals("Comedy")) {
            movieList.add(new Movie("The Hangover", "Three buddies wake up from a bachelor party in Las Vegas, with no memory of the previous night and the bachelor missing. They make their way around the city in order to find their friend before his wedding.", "2009", R.drawable.hangover_poster, Arrays.asList("Zach Galifianakis", "Bradley Cooper", "Ed Helms")));
            movieList.add(new Movie("Airplane!", "A man afraid to fly must ensure that a plane lands safely after the pilots become sick.", "1980", R.drawable.airplane_poster, Arrays.asList("Robert Hays", "Julie Hagerty", "Leslie Nielsen")));
            movieList.add(new Movie("Bridesmaids", "Competition between the maid of honor and a bridesmaid, over who is the bride's best friend, threatensthe wedding when their antics lead to a series of disasters.", "2011", R.drawable.bridesmaids_poster, Arrays.asList("Kristen Wiig", "Maya Rudolph", "Rose Byrne")));
            movieList.add(new Movie("The Grand Budapest Hotel", "The adventures of Gustave H, a legendary concierge at a famous hotel from the fictional Republic of Zubrowka between the first and second World Wars, and Zero Moustafa, the lobby boy who becomes his most trusted friend.", "2014", R.drawable.grand_budapest_hotel_poster, Arrays.asList("Ralph Fiennes", "Tony Revolori", "F. Murray Abraham")));
        }

        // Notify the movieListAdapter that the movieList has changed
        movieListAdapter.notifyDataSetChanged();
    }

    // Implement the onMovieClick method of the MovieClickListener interface
    @Override
    public void onMovieClick(int position) {
        // Get the selected movie from the movieList
        Movie selectedMovie = movieList.get(position);

        // Create an intent to start the MovieDetailActivity and pass the selected movie as an extra
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra("movie", selectedMovie);
        startActivity(intent);
    }
}