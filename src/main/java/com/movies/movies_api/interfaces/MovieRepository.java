package com.movies.movies_api.interfaces;
import java.util.List;
import com.movies.movies_api.models.Movie;

public interface MovieRepository {
    List<Movie> findAll();
    Movie findById(Long id);
    Movie save(Movie movie);
    Movie update(Long id, Movie movie);
    void delete(Long id);
}
