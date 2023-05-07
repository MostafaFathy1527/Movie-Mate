package com.example.moviemate;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MovieListActivity extends AppCompatActivity implements MovieListAdapter.MovieClickListener {

    private RecyclerView movieListRecyclerView;
    private List<Movie> movies = new ArrayList<>();
    private MovieListAdapter movieAdapter;
    private SharedPreferences sharedPreferences;

    private String sortOrder;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_list_activity);
        Intent intent = getIntent();
        String category = intent.getStringExtra("category");

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        sortOrder = sharedPreferences.getString("sort_order", "rating");
        Log.i("SORT ORDER", sortOrder);

        // Get the list of movies for the selected category
        movies = getMoviesForCategory(category);

        // Initialize the RecyclerView and its adapter
        movieListRecyclerView = findViewById(R.id.movieListRecyclerView);
        movieAdapter = new MovieListAdapter(this, movies, this);
        movieListRecyclerView.setAdapter(movieAdapter);
        movieListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    @Override
    public void onBackPressed() {
        sortOrder = "rating";
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("sort_order", sortOrder);  // Save the actual sortOrder
        editor.apply();
    }
    final Handler handler = new Handler();
    final Runnable saveRunnable = new Runnable() {
        @Override
        public void run() {
            onPause();
        }
    };
    @Override
    protected void onResume() {
        super.onResume();

        // Read the saved sort order
        sortOrder = sharedPreferences.getString("sort_order", sortOrder);

        // Sort based on the saved sort order
        if (sortOrder.equals("title")) {
            // Sort by title
        } else if (sortOrder.equals("rating")) {
            // Sort by rating
        } else if (sortOrder.equals("date")) {
            // Sort by date
        }

        movieAdapter.notifyDataSetChanged();
    }
    private List<Movie> getMoviesForCategory(String category) {
        List<Movie> movies = new ArrayList<>();

        switch (category) {
            case "Action":
                movies.add(new Movie("The Matrix", "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.", "1999", R.drawable.the_matrix, 5f));
                movies.add(new Movie("Die Hard", "An NYPD officer tries to save his wife and several others taken hostage by German terrorists during a Christmas party at the Nakatomi Plaza in Los Angeles.", "1988", R.drawable.die_hard, 4.5f));
                movies.add(new Movie("Terminator 2: Judgment Day", "A cyborg, identical to the one who failed to kill Sarah Connor, must now protect her teenage son, John Connor, from a more advanced and powerful cyborg.", "1991", R.drawable.terminator_2, 4f));
                movies.add(new Movie("Lethal Weapon", "A veteran cop, Murtaugh, is partnered with a young suicidal cop, Riggs. Both having one thing in common; hating working in pairs. Now they must learn to work with one another to stop a gang of drug smugglers.", "1987", R.drawable.lethal_weapon, 4f));
                movies.add(new Movie("Mad Max: Fury Road", "In a post-apocalyptic wasteland, a woman rebels against a tyrannical ruler in search for her homeland with the aid of a group of female prisoners, a psychotic worshiper, and a drifter named Max.", "2015", R.drawable.mad_max_fury_road, 4f));
                movies.add(new Movie("Gladiator", "A former Roman General sets out to exact vengeance against the corrupt emperor who murdered his family and sent him into slavery.", "2000", R.drawable.gladiator, 4.5f));
                break;

            case "Drama":
                movies.add(new Movie("The Shawshank Redemption", "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.", "1994", R.drawable.the_shawshank_redemption, 5f));
                movies.add(new Movie("Forrest Gump", "The presidencies of Kennedy and Johnson, the events of Vietnam, Watergate, and other historical events unfold through the perspective of an Alabama man with an IQ of 75.", "1994", R.drawable.forrest_gump, 4.5f));
                movies.add(new Movie("The Godfather", "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.", "1972", R.drawable.the_godfather, 4f));
                movies.add(new Movie("The Green Mile", "The lives of guards on Death Row are affected by one of their charges: a black man accused of child murder and rape, yet who has a mysterious gift.", "1999", R.drawable.the_green_mile, 4f));
                movies.add(new Movie("The Social Network", "Harvard student Mark Zuckerberg creates the social networking site that would become known as Facebook, but is later sued by two brothers who claimed he stole their idea, and the co-founder who was later squeezed out of the business.", "2010", R.drawable.the_social_network, 4f));
                movies.add(new Movie("Schindler's List", "In German-occupied Poland during World War II, industrialist Oskar Schindler gradually becomes concerned for his Jewish workforce after witnessing their persecution by the Nazis.", "1993", R.drawable.schindlers_list_poster, 4.5f));
                break;

            case "Comedy":
                movies.add(new Movie("The Hangover", "Three buddies wake up from a bachelor party in Las Vegas, with no memory of the previous night and the bachelor missing. They make their way around the city in order to find their friend before his wedding.", "2009", R.drawable.the_hangover, 4.5f));
                movies.add(new Movie("Bridesmaids", "Competition between the maid of honor and a bridesmaid, over who is the bride's best friend, threatens to upend the life of an out-of-work pastry chef.", "2011", R.drawable.bridesmaids, 4f));
                movies.add(new Movie("Superbad", "Two co-dependent high school seniors are forced to deal with separation anxiety after their plan to stage a booze-soaked party goes awry.", "2007", R.drawable.superbad, 3.5f));
                movies.add(new Movie("This Is the End", "While attending a party at James Franco's house, Seth Rogen, Jay Baruchel and many other celebrities are faced with the apocalypse.", "2013", R.drawable.this_is_the_end, 4f));
                movies.add(new Movie("Step Brothers", "Two aimless middle-aged losers still living at home are forced against their will to become roommates when their parents marry.", "2008", R.drawable.step_brothers, 4f));
                movies.add(new Movie("The 40-Year-Old Virgin", "Goaded by his buddies, a nerdy guy who's never 'done the deed' only finds the pressure mounting when he meets a single mother.", "2005", R.drawable.the_40_year_old_virgin, 4f));
                break;

            case "Horror":
                movies.add(new Movie("The Shining", "A family heads to an isolated hotel for the winter where a sinister presence influences the father into violence, while his psychic son sees horrific forebodings from both past and future.", "1980", R.drawable.the_shining, 4f));
                movies.add(new Movie("Psycho", "A Phoenix secretary embezzles forty thousand dollars from her employer's client, goes on the run, and checks into a remote motel run by a young man under the domination of his mother.", "1960", R.drawable.psycho, 4.5f));
                movies.add(new Movie("The Exorcist", "When a teenage girl is possessed by a mysterious entity, her mother seeks the help of two priests to save her daughter.", "1973", R.drawable.the_exorcist, 4f));
                movies.add(new Movie("The Conjuring", "Paranormal investigators Ed and Lorraine Warren work to help a family terrorized by a dark presence in their farmhouse.", "2013", R.drawable.the_conjuring, 4f));
                movies.add(new Movie("Hereditary", "A grieving family is haunted by tragic and disturbing occurrences.", "2018", R.drawable.hereditary, 4.5f));
                movies.add(new Movie("The Babadook", "A single mother, plagued by the violent death of her husband, battles with her son's fear of a monster lurking in the house, but soon discovers a sinister presence all around her.", "2014", R.drawable.the_babadook, 4f));
                break;

            case "Animation":
                movies.add(new Movie("Toy Story", "A cowboy doll is profoundly threatened and jealous when a new spaceman figure supplants him as top toy in a boy's room.", "1995", R.drawable.toy_story, 4.5f));
                movies.add(new Movie("The Lion King", "A lion cub prince is tricked by a treacherous uncle into thinking he caused his father's death and flees into exile in despair, only to learn in adulthood his identity and his responsibilities.", "1994", R.drawable.the_lion_king, 4.5f));
                movies.add(new Movie("Finding Nemo", "After his son is captured in the Great Barrier Reef and taken to Sydney, a timid clownfish sets out on a journey to bring him home.", "2003", R.drawable.finding_nemo, 4f));
                movies.add(new Movie("Up", "78-year-old Carl Fredricksen travels to Paradise Falls in his house equipped with balloons, inadvertently taking a young stowaway.", "2009", R.drawable.up, 4f));
                movies.add(new Movie("Inside Out", "After young Riley is uprooted from her Midwest life and moved to San Francisco, her emotions - Joy, Fear, Anger, Disgust, and Sadness - conflict on how best to navigate a new city, house, and school.", "2015", R.drawable.inside_out, 4.5f));
                movies.add(new Movie("Zootopia", "In a city of anthropomorphic animals, a rookie bunny cop and a cynical con artist fox must work together to uncover a conspiracy.", "2016", R.drawable.zootopia, 4f));
                break;

            default:
                break;
        }

        return movies;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.movie_list_activity, menu);
        return true;
    }
    // Implement the onMovieClick method of the MovieClickListener interface
    @Override

    public void onMovieClick(int position) {
        // Get the selected movie from the movieList
        Movie selectedMovie = movies.get(position);

        // Create an intent to start the MovieDetailActivity and pass the selected movie as an extra
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra("movie", selectedMovie);

        startActivity(intent);
    }


    @Override


    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.sort_by_title:
                // Sort movies by title
                Collections.sort(movies, new Comparator<Movie>() {
                    @Override
                    public int compare(Movie m1, Movie m2) {
                        return m1.getTitle().compareTo(m2.getTitle());
                    }
                });

                // Save sort order to SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("sort_order", "title");
                editor.apply();

                // Notify the adapter that the data has changed
                movieAdapter.notifyDataSetChanged();
                return true;

            case R.id.sort_by_rating:
                // Sort movies by rating
                Collections.sort(movies, new Comparator<Movie>() {
                    @Override
                    public int compare(Movie m1, Movie m2) {
                        return Double.compare(m2.getRating(), m1.getRating());
                    }
                });

                // Save sort order to SharedPreferences
                editor = sharedPreferences.edit();
                editor.putString("sort_order", "rating");
                editor.apply();

                // Notify the adapter that the data has changed
                movieAdapter.notifyDataSetChanged();
                return true;

            case R.id.sort_by_date:
                // Sort movies by date
                Collections.sort(movies, new Comparator<Movie>() {
                    @Override
                    public int compare(Movie m1, Movie m2) {
                        return m1.getReleaseDate().compareTo(m2.getReleaseDate());
                    }
                });

                // Save sort order to SharedPreferences
                editor = sharedPreferences.edit();
                editor.putString("sort_order", "date");
                editor.apply();

                // Notify the adapter that the data has changed
                movieAdapter.notifyDataSetChanged();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }}