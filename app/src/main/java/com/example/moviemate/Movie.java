package com.example.moviemate;

import java.io.Serializable;
import java.util.List;
import java.io.Serializable;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.List;

public class Movie implements Serializable {
    private String title;
    private String synopsis;
    private String releaseDate;
    private int poster;
    private List<String> cast;
    private String category;


    private float rating;

    public Movie(String title,String category, String synopsis,  String releaseDate, int poster) {
        this.title = title;
        this.category = category;
        this.synopsis = synopsis;
        this.releaseDate = releaseDate;
        this.poster = poster;
    }

    public Movie(String title, String synopsis, String category, int poster) {
        this.title = title;
        this.category = category;
        this.synopsis = synopsis;
        this.poster = poster;
    }
    protected Movie(Parcel in) {
        title = in.readString();
        synopsis = in.readString();
        poster = in.readInt();
        category = in.readString();
    }

    public Movie(String title, String synopsis, String releaseDate, int poster, float rating) {
        this.title = title;
        this.synopsis = synopsis;
        this.releaseDate = releaseDate;
        this.poster = poster;
        this.rating = rating;
    }
    public Movie(String title, String synopsis, String releaseDate, int poster, float rating,String category) {
        this.title = title;
        this.synopsis = synopsis;
        this.releaseDate = releaseDate;
        this.poster = poster;
        this.rating = rating;
        this.category= category;
    }

    public Movie(String title, int poster, float rating) {
        this.title = title;
        this.poster = poster;
    }
    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int getPoster() {
        Log.d("Movie", "Getting poster for " + title + " with ID " + poster);
        return poster;
    }
    public List<String> getCast() {
        return cast;
    }

    public String getCategory() {
        return category;
    }

    public float getRating() {
        return rating;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }

    public void setCast(List<String> cast) {
        this.cast = cast;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }




}