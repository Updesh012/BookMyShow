package org.example;

public class Movie {
    private String movieId;
    private String name;
    private String genre;
    private int durationInMinutes;

    public Movie(String movieId, String name, String genre, int durationInMinutes) {
        this.movieId = movieId;
        this.name = name;
        this.genre = genre;
        this.durationInMinutes = durationInMinutes;
    }

    public String getName() { return name;}

    public String getGenre() { return genre; }

    public int getDurationInMinutes() { return durationInMinutes; }
}

