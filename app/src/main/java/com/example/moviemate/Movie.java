package com.example.moviemate;

import java.io.Serializable;
import java.util.List;

public class Movie implements Serializable {
    private String title;
    private String synopsis;
    private String releaseDate;
    private int poster;
    private List<String> cast;

    public Movie(String title, String synopsis, String releaseDate, int poster, List<String> cast) {
        this.title = title;
        this.synopsis = synopsis;
        this.releaseDate = releaseDate;
        this.poster = poster;
        this.cast = cast;
    }

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
        return poster;
    }

    public List<String> getCast() {
        return cast;
    }
}