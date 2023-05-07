package com.example.moviemate;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private SearchView searchView;
    private RecyclerView categoryRecyclerView, featuredRecyclerView, topRatedRecyclerView;
    private MovieAdapter featuredAdapter, topRatedAdapter;

    private List<Movie> movies;

    private RecyclerView categoriesRecyclerView;
    private CategoryAdapter categoryAdapter;
    private List<Category> categories;
    private List<Category> originalCategories;
    private BroadcastReceiver MyReceiver = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        loadLocale();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));

        setContentView(R.layout.activity_main);
        Button lang=(Button) findViewById(R.id.langbtn);
        lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("lang btn","clicker");
                showChangeLangDialog();
            }
        });


        MyReceiver = new MyReceiver();

        // Initialize views
        searchView = findViewById(R.id.searchView);
        categoryRecyclerView = findViewById(R.id.categoriesRecyclerView);
        featuredRecyclerView = findViewById(R.id.featuredRecyclerView);
        topRatedRecyclerView = findViewById(R.id.topRatedRecyclerView);

        // Initialize movie data
        movies = new ArrayList<>();
        movies.add(new Movie("The Shawshank Redemption", "Drama", "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.", R.drawable.shawshank_redemption_poster));
        movies.add(new Movie("The Godfather", "Crime", "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.", R.drawable.the_godfather));
        movies.add(new Movie("The Dark Knight", "Action", "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.", R.drawable.dark_knight_poster));
        movies.add(new Movie("Schindler's List", "Drama", "In German-occupied Poland during World War II, industrialist Oskar Schindler gradually becomes concerned for his Jewish workforce after witnessing their persecution by the Nazis.", R.drawable.schindlers_list_poster));
        movies.add(new Movie("Forrest Gump", "Drama", "The presidencies of Kennedy and Johnson, the events of Vietnam, Watergate, and other history unfold through the perspective of an Alabama man with an IQ of 75.", R.drawable.forrest_gump_poster));
        movies.add(new Movie("The Matrix", "Action", "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.", R.drawable.the_matrix));

        // Set up category RecyclerView
        categoriesRecyclerView = findViewById(R.id.categoriesRecyclerView);
        categories = getCategoryData();
        originalCategories = new ArrayList<>(categories);
        categoryAdapter = new CategoryAdapter(this, categories);
        categoriesRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        categoriesRecyclerView.setAdapter(categoryAdapter);

        // Set the spacing between items in the RecyclerView
        int spanCount = 2; // number of columns
        int spacing = 16; // spacing between columns in pixels
        boolean includeEdge = true; // include edge spacing
        categoriesRecyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));

        // Set an OnItemClickListener for the RecyclerView
        categoryAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.d("lan btn","clicked");
                // Get the selected category from the clicked item
                Category selectedCategory = categories.get(position);

                // Launch the MovieListActivity for the selected category
                Intent intent = new Intent(MainActivity.this, MovieListActivity.class);
                intent.putExtra("category", selectedCategory.getName());
                startActivity(intent);
            }

        });

        // Set up featured RecyclerView
        featuredAdapter = new MovieAdapter(this, getFeaturedMovies());
        featuredRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        featuredRecyclerView.setAdapter(featuredAdapter);

        // Set up top rated RecyclerView
        topRatedAdapter = new MovieAdapter(this, getTopRatedMovies());
        topRatedRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        topRatedRecyclerView.setAdapter(topRatedAdapter);

        // Set up searchView listener
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Filter the categories by the search query

                Intent intent = new Intent(getApplicationContext(), SearchResultsActivity.class);
                String SearchedMovie = searchView.getQuery().toString().toLowerCase();
                intent.putExtra("name", SearchedMovie);
                startActivity(intent);
                return true;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filter the categories by the search query
                filterCategories(newText);
                return true;
            }
        });
        registerReceiver(MyReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(MyReceiver);
    }
    private void showChangeLangDialog(){
        final String [] listItems= {"English","arabic"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        mBuilder.setTitle("Choose a Language. . .");
        mBuilder.setSingleChoiceItems( listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(i==0){
                    setLocale("en");
                    recreate();
                }
               else if(i==1){
                    setLocale("ar");
                    recreate();
                }
                dialogInterface.dismiss();

            }
        });
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale= locale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor= getSharedPreferences("settings",MODE_PRIVATE).edit();
        editor.putString("My_lang",lang);
        editor.apply();
    }
    public void loadLocale(){
        SharedPreferences prefs = getSharedPreferences("settings", Activity.MODE_PRIVATE);
        String lang=prefs.getString("My_lang","");
        setLocale(lang);
    }

    // Get the category data
    private List<Category> getCategoryData() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Action", R.drawable.dark_knight_poster));
        categories.add(new Category("Drama", R.drawable.dark_knight_poster));
        categories.add(new Category("Comedy", R.drawable.dark_knight_poster));
        categories.add(new Category("Horror", R.drawable.dark_knight_poster));
        categories.add(new Category("Science Fiction", R.drawable.dark_knight_poster));
        categories.add(new Category("Thriller", R.drawable.dark_knight_poster));
        categories.add(new Category("Romance", R.drawable.dark_knight_poster));
        categories.add(new Category("Animation", R.drawable.dark_knight_poster));
        return categories;
    }

    // Get the featured movies
    private List<Movie> getFeaturedMovies() {
        List<Movie> featuredMovies = new ArrayList<>();
        featuredMovies.add(new Movie("The Godfather", "Crime", "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.", R.drawable.the_godfather));
        featuredMovies.add(new Movie("The Shawshank Redemption", "Drama", "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.", R.drawable.shawshank_redemption_poster));
        featuredMovies.add(new Movie("The Dark Knight", "Action", "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.", R.drawable.dark_knight_poster));
        featuredMovies.add(new Movie("Forrest Gump", "Drama", "The presidencies of Kennedy and Johnson, the events of Vietnam, Watergate, and other history unfold through the perspective of an Alabama man with an IQ of 75.", R.drawable.forrest_gump_poster));
        return featuredMovies;
    }

    // Get the top rated movies
    private List<Movie> getTopRatedMovies() {
        List<Movie> topRatedMovies = new ArrayList<>();
        topRatedMovies.add(new Movie("The Shawshank Redemption", "Drama", "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.", R.drawable.shawshank_redemption_poster));
        topRatedMovies.add(new Movie("The Godfather", "Crime", "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.", R.drawable.the_godfather));
        topRatedMovies.add(new Movie("The Dark Knight", "Action", "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.", R.drawable.dark_knight_poster));
        topRatedMovies.add(new Movie("Schindler's List", "Drama", "In German-occupied Poland during World War II, industrialist Oskar Schindler gradually becomes concerned for his Jewish workforce after witnessing their persecution by the Nazis.", R.drawable.schindlers_list_poster));
        topRatedMovies.add(new Movie("The Matrix", "Action", "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.", R.drawable.the_matrix));
        // Sort the movies by rating in descending order
        Collections.sort(topRatedMovies, new Comparator<Movie>() {
            @Override
            public int compare(Movie movie1, Movie movie2) {
                return Double.compare(movie2.getRating(), movie1.getRating());
            }
        });
        return topRatedMovies;
    }

    // Filter the categories by the search query
    private void filterCategories(String query) {
        categories.clear();
        if (query.isEmpty()) {
            categories.addAll(originalCategories);
        } else {
            query = query.toLowerCase();
            for (Category category : originalCategories) {
                if (category.getName().toLowerCase().contains(query)) {
                    categories.add(category);
                }
            }
        }
        categoryAdapter.notifyDataSetChanged();
    }
}