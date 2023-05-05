package com.example.moviemate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

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

        categoriesGrid = (GridView) findViewById(R.id.categoriesGrid);
        categories = getCategoryData();
        categoryAdapter = new CategoryAdapter(this, categories);
        categoriesGrid.setAdapter(categoryAdapter);
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