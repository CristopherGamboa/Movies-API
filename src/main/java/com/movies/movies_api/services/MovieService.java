package com.movies.movies_api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movies.movies_api.models.Movie;
import com.movies.movies_api.repositories.interfaces.IMovieRepository;
import com.movies.movies_api.services.interfaces.IMovieService;

@Service
public class MovieService implements IMovieService {
    private final IMovieRepository movieRepository;

    @Autowired
    public MovieService(IMovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return movieRepository.findById(id);
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Optional<Movie> save(Movie movie) {
        return Optional.of(movieRepository.save(movie));
    }

    @Override
    public Optional<Movie> update(Long id, Movie movie) {
        if (!movieRepository.existsById(id)) {
            return Optional.empty();
        }

        movie.setId(id);

        return Optional.of(movieRepository.save(movie));
    }

    @Override
    public void delete(Long id) {
        movieRepository.deleteById(id);
    }
}
