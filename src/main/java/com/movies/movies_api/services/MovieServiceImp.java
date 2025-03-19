package com.movies.movies_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movies.movies_api.interfaces.MovieRepository;
import com.movies.movies_api.interfaces.MovieService;
import com.movies.movies_api.models.Movie;

@Service
public class MovieServiceImp implements MovieService {
    private final MovieRepository movieRepository;

    @Autowired
    public MovieServiceImp(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie findById(Long id) {
        return movieRepository.findById(id);
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie update(Long id, Movie movie) {
        return movieRepository.update(id, movie);
    }

    @Override
    public void delete(Long id) {
        movieRepository.delete(id);
    }
}
