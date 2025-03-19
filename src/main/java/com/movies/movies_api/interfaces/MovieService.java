package com.movies.movies_api.interfaces;

import java.util.List;

import com.movies.movies_api.models.Movie;

public interface MovieService {
    public Movie save(Movie movie);
    public Movie update(Long id, Movie movie);
    public void delete(Long id);
    public Movie findById(Long id);
    public List<Movie> findAll();
}
