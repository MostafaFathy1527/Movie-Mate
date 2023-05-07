package com.example.moviemate;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView categoriesRecyclerView;
    private CategoryAdapter categoryAdapter;
    private List<Category> categories;

    private DarkModeReceiver receiver;

    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the RecyclerView and its adapter
        categoriesRecyclerView = findViewById(R.id.categoriesRecyclerView);
        categories = getCategoryData();
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
                // Get the selected category from the clicked item
                Category selectedCategory = categories.get(position);

                // Launch the MovieListActivity for the selected category
                Intent intent = new Intent(MainActivity.this, MovieListActivity.class);
                intent.putExtra("category", selectedCategory.getName());
                startActivity(intent);
            }
        });

        // Set up the featured movies section
        List<Movie> featuredMovies = new ArrayList<>();
        featuredMovies.add(new Movie("Movie 1", R.drawable.dark_knight_poster));
        featuredMovies.add(new Movie("Movie 2", R.drawable.forrest_gump));
        featuredMovies.add(new Movie("Movie 3", R.drawable.godfather_poster));

        RecyclerView featuredRecyclerView = findViewById(R.id.featuredRecyclerView);
        featuredRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        featuredRecyclerView.setAdapter(new FeaturedMoviesAdapter(featuredMovies));

// Set up the top rated movies section
        List<Movie> topRatedMovies = new ArrayList<>();
        topRatedMovies.add(new Movie("Movie 1", R.drawable.dark_knight_poster, 4.5f));
        topRatedMovies.add(new Movie("Movie 2", R.drawable.dark_knight_poster, 4.2f));
        topRatedMovies.add(new Movie("Movie 3", R.drawable.dark_knight_poster, 4.0f));
        topRatedMovies.add(new Movie("Movie 4", R.drawable.dark_knight_poster, 3.9f));
        topRatedMovies.add(new Movie("Movie 5", R.drawable.dark_knight_poster, 3.8f));

        RecyclerView topRatedRecyclerView = findViewById(R.id.topRatedRecyclerView);
        TopRatedMoviesAdapter adapter = new TopRatedMoviesAdapter(this, topRatedMovies); // pass the context as the first argument
        topRatedRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        topRatedRecyclerView.setAdapter(adapter); // use the same adapter object created above

        // Register the receiver in your activity or fragment
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_CONFIGURATION_CHANGED);
        receiver = new DarkModeReceiver();
        registerReceiver(receiver, filter);

    }

    private List<Category> getCategoryData() {
        List<Category> categories = new ArrayList<Category>();
        categories.add(new Category("Sci-Fi", R.drawable.scifi));
        categories.add(new Category("Action", R.drawable.action));
        categories.add(new Category("Comedy", R.drawable.comedy));
        categories.add(new Category("Drama", R.drawable.drama));
        categories.add(new Category("Horror", R.drawable.horror));
        categories.add(new Category("Romance", R.drawable.romance));
        return categories;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Unregister the receiver when the activity is destroyed
        unregisterReceiver(receiver);
    }

    public class DarkModeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_CONFIGURATION_CHANGED)) {
                int uiMode = context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
                switch (uiMode) {
                    case Configuration.UI_MODE_NIGHT_NO:
                        // Light mode
                        Log.d(TAG, "light");
                        break;
                    case Configuration.UI_MODE_NIGHT_YES:
                        // Dark mode
                        Log.d(TAG, "dark");
                        break;
                }
            }
        }
    }
}