package com.movies.movies_api.models;

public class Movie {
    private Long id;
    private String title;
    private int year;
    private String[] genres;
    private String director;
    private String synopsis;

    public Movie(Long id, String title, int year, String[] genres, String director, String synopsis) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.genres = genres;
        this.director = director;
        this.synopsis = synopsis;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public String[] getGenres() {
        return genres;
    }
    public void setGenres(String[] genres) {
        this.genres = genres;
    }
    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    public String getSynopsis() {
        return synopsis;
    }
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
}
