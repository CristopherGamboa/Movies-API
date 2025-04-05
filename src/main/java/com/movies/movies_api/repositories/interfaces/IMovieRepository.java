package com.movies.movies_api.repositories.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movies.movies_api.models.Movie;

public interface IMovieRepository extends JpaRepository<Movie, Long> {
}