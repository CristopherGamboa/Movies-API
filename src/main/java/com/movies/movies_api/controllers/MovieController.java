package com.movies.movies_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movies.movies_api.interfaces.MovieService;
import com.movies.movies_api.models.Movie;

@RestController
@RequestMapping("api/movies")
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.findAll();
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable Long id) {
        return movieService.findById(id);
    }

    @PostMapping
    public Movie createMovie(@RequestBody Movie movie) {
        return movieService.save(movie);
    }

    @PutMapping("/{id}")
    public Movie updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
        return movieService.update(id, movie);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id) {
        movieService.delete(id);
    }
}
