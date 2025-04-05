package com.movies.movies_api.services.interfaces;

import java.util.List;
import java.util.Optional;

import com.movies.movies_api.models.Movie;

public interface IMovieService {
    public Optional<Movie> save(Movie movie);
    public Optional<Movie> update(Long id, Movie movie);
    public void delete(Long id);
    public Optional<Movie> findById(Long id);
    public List<Movie> findAll();
}
