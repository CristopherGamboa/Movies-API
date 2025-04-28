package com.movies.movies_api.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movies.movies_api.models.Movie;
import com.movies.movies_api.services.interfaces.IMovieService;

@RestController
@RequestMapping("api/movies")
public class MovieController {
    private final IMovieService movieService;

    @Autowired
    public MovieController(IMovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public CollectionModel<EntityModel<Movie>> getAllMovies() {
        List<Movie> movies = movieService.findAll(); 

        List<EntityModel<Movie>> movieModels = movies.stream()
            .map(movie -> EntityModel.of(movie,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                .getMovieById(movie.getId())).withSelfRel()
                ))
            .collect(Collectors.toList());

        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllMovies());
        CollectionModel<EntityModel<Movie>> movieCollectionModel = CollectionModel
            .of(movieModels, linkBuilder.withRel("movies"));

        return movieCollectionModel;
    }

    @GetMapping("/{id}")
    public EntityModel<Movie> getMovieById(@PathVariable Long id) {
        Optional<Movie> movie = movieService.findById(id);

        if (!movie.isPresent()) {
            throw new MovieNotFoundException("Movie not found with id: " + id);
        }

        return EntityModel.of(movie.get(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getMovieById(id)).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllMovies()).withRel("movies"));
    }

    @PostMapping
    public EntityModel<Movie> createMovie(@RequestBody Movie movie) {
        Optional<Movie> savedMovie = movieService.save(movie);

        if (!savedMovie.isPresent()) {
            throw new MovieNotFoundException("Movie not found with id: " + movie.getId());
        }

        return EntityModel.of(savedMovie.get(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getMovieById(savedMovie.get().getId())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllMovies()).withRel("movies"));
    }

    @PutMapping("/{id}")
    public EntityModel<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
        Optional<Movie> savedMovie = movieService.update(id, movie);

        if (!savedMovie.isPresent()) {
            throw new MovieNotFoundException("Movie not found with id: " + movie.getId());
        }

        return EntityModel.of(savedMovie.get(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getMovieById(savedMovie.get().getId())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllMovies()).withRel("movies"));
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id) {
        movieService.delete(id);
    }
}
