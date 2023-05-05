package com.example.moviemate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private GridView categoriesGrid;
    private CategoryAdapter categoryAdapter;
    private List<Category> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the GridView and its adapter
        categoriesGrid = findViewById(R.id.categoriesGrid);
        categories = getCategoryData();
        categoryAdapter = new CategoryAdapter(this, categories);
        categoriesGrid.setAdapter(categoryAdapter);

        // Set an OnItemClickListener for the GridView
        categoriesGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Get the selected category from the clicked item
                Category selectedCategory = categories.get(position);

                // Launch the MovieListActivity for the selected category
                Intent intent = new Intent(MainActivity.this, MovieListActivity.class);
                intent.putExtra("category", selectedCategory.getName());
                startActivity(intent);
            }
        });
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
}