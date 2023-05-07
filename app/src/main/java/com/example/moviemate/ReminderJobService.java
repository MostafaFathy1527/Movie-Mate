package com.example.moviemate;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class ReminderJobService extends JobService {

    public void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("notify", "My Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

    }

    @Override


    public boolean onStartJob(JobParameters params) {
        createNotificationChannel();
        PendingIntent contentPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, MovieDetailsActivity.class), PendingIntent.FLAG_IMMUTABLE);        String movieTitle = params.getExtras().getString("MovieTitle");
        int resID = params.getExtras().getInt("res_id");
        NotificationCompat.Builder notify = new NotificationCompat.Builder(this, "notify");
        notify.setContentTitle("Don't Worry!");
        notify.setContentText("You'll be reminded when " + movieTitle + " is showing");
        notify.setSmallIcon(R.drawable.logo);
        notify.setAutoCancel(true);
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
        }

        managerCompat.notify(0, notify.build());
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return true;
    }


}