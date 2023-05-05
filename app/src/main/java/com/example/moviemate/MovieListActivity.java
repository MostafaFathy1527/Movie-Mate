package com.example.moviemate;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class MovieListActivity extends AppCompatActivity {


    private List<Movie> movies;
    private RecyclerView movieListRecyclerView;

    private MovieAdapter movieAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_list_activity);

        // Get the selected category from the intent that started this activity
        Intent intent = getIntent();
        String category = intent.getStringExtra("category");

        // Get the list of movies for the selected category
        movies = getMoviesForCategory(category);

        // Initialize the RecyclerView and its adapter
        movieListRecyclerView = findViewById(R.id.movieListRecyclerView);
        movieAdapter = new MovieAdapter(movies);
        movieListRecyclerView.setAdapter(movieAdapter);
        movieListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private List<Movie> getMoviesForCategory(String category) {
        List<Movie> movies = new ArrayList<>();

        // TODO: Replace this with your own implementation to retrieve the list of movies for the selected category
        switch (category) {
            case "Action":
                movies.add(new Movie("The Matrix", "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.", R.drawable.the_matrix));
                movies.add(new Movie("Die Hard", "An NYPD officer tries to save his wife and several others taken hostage by German terrorists during a Christmas party at the Nakatomi Plaza in Los Angeles.", R.drawable.die_hard));
                movies.add(new Movie("Terminator 2: Judgment Day", "A cyborg, identical to the one who failed to kill Sarah Connor, must now protect her teenage son, John Connor, from a more advanced and powerful cyborg.", R.drawable.terminator_2));
                break;
            case "Comedy":
                movies.add(new Movie("The Hangover", "Three buddies wake up from a bachelor party in Las Vegas, with no memory of the previous night and the bachelor missing. They make their way around the city in order to find their friend before his wedding.", R.drawable.the_hangover));
                movies.add(new Movie("Bridesmaids", "Competition between the maid of honor and a bridesmaid, over who is the bride's best friend, threatens to upend the life of an out-of-work pastry chef.", R.drawable.bridesmaids));
                movies.add(new Movie("Superbad", "Two co-dependent high school seniors are forced to deal with separation anxiety after their plan to stage a booze-soaked party goes awry.", R.drawable.superbad));
                break;
            case "Drama":
                movies.add(new Movie("The Shawshank Redemption", "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.", R.drawable.the_shawshank_redemption));
                movies.add(new Movie("Forrest Gump", "The presidencies of Kennedy and Johnson, the events of Vietnam, Watergate, and other historical events unfold through the perspective of an Alabama man with an IQ of 75.", R.drawable.forrest_gump));
                movies.add(new Movie("The Godfather", "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.", R.drawable.the_godfather));
                break;
            default:
                // If the selected category is not recognized, return an empty list
                break;
        }

        return movies;
    }
}