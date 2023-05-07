package com.example.moviemate;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsActivity extends AppCompatActivity {

    TextView name;
    ImageView imageView;
    ImageView nfound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);



        Intent intent = getIntent();
        String movieName = intent.getStringExtra("name");

        name = findViewById(R.id.moviename);
        imageView = findViewById(R.id.poster);
        nfound = findViewById(R.id.nfound);

        name.setText(movieName);


        int imageResource = getResources().getIdentifier(movieName, "drawable", getPackageName());

        if (imageResource == 0) {
            Toast.makeText(this,  movieName + " not found " , Toast.LENGTH_SHORT).show();
            nfound.setImageResource(R.drawable.notfound);
            name.setVisibility(View.INVISIBLE);
        } else {
            imageView.setImageResource(imageResource);
        }

      //thisd is a tmkl;serjyhgjklndfjklgh;dfkl;jnhjkndf

    }


}