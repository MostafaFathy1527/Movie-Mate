package com.example.moviemate;

import android.annotation.SuppressLint;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MovieDetailsActivity extends AppCompatActivity {

    private TextView titleTextView;
    private TextView releaseDateTextView;
    private TextView synopsisTextView;
    private TextView castTextView;
    private ImageView posterImageView;

    private TextView ratingTextView;
    private  TextView catTextView;

    private JobScheduler nScheduler;
    private static final int JOB_ID=0;
    @SuppressLint("SetTextI18n")
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
        posterImageView = findViewById(R.id.posterImageView);
        ratingTextView = findViewById(R.id.Rating);
        catTextView = findViewById(R.id.Category);
        Button backbtn = (Button) findViewById(R.id.BackBtn);
        Button remindbtn = (Button) findViewById(R.id.button);


        // Set the text content and image for each view
        titleTextView.setText(selectedMovie.getTitle());
        releaseDateTextView.setText(selectedMovie.getReleaseDate());
        synopsisTextView.setText(selectedMovie.getSynopsis());
        posterImageView.setImageResource(selectedMovie.getPoster());
        ratingTextView.setText((int) selectedMovie.getRating()+"/5 Stars");
        catTextView.setText("Category: "+selectedMovie.getCategory());

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getBaseContext();
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });




        remindbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "You'll be reminded when movie start", Toast.LENGTH_SHORT);
                toast.show();

                JobScheduler nScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
                ComponentName serviceName=new ComponentName(getPackageName(),ReminderJobService.class.getName());
                JobInfo.Builder builder=new JobInfo.Builder(JOB_ID,serviceName);
                PersistableBundle bundle = new PersistableBundle();
                bundle.putString("MovieTitle", titleTextView.getText().toString());
                JobInfo myJobInfo = new JobInfo.Builder(1, serviceName)
                        .setExtras(bundle)
                        .build();
                nScheduler.schedule(myJobInfo);

            } });
    }
}