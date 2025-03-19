package com.movies.movies_api.repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.movies.movies_api.interfaces.MovieRepository;
import com.movies.movies_api.models.Movie;

@Repository
public class InMemoryMovieRepository implements MovieRepository {
    private final List<Movie> movies;

    public InMemoryMovieRepository() {
        this.movies = new ArrayList<>(Arrays.asList(
            new Movie(1L, "The Shawshank Redemption", 1994, new String[]{"Drama"}, "Frank Darabont", 
                      "Two imprisoned men bond over a number of years, finding solace and eventual redemption."),
            new Movie(2L, "The Godfather", 1972, new String[]{"Crime", "Drama"}, "Francis Ford Coppola", 
                      "An organized crime dynasty's aging patriarch transfers control of his clandestine empire."),
            new Movie(3L, "Nightcrawler", 2014, new String[]{"Crime", "Drama"}, "Dan Gilroy", 
                      "A man with a camera can see everything... but he can't tell if it's real or not."),
            new Movie(4L, "Interstellar", 2014, new String[]{"Adventure", "Drama", "Sci-Fi"}, "Christopher Nolan", 
                      "A team of explorers travel through a wormhole in space to ensure humanity's survival."),
            new Movie(5L, "Fight Club", 1999, new String[]{"Drama"}, "David Fincher", 
                      "An insomniac office worker and a devil-may-care soapmaker form an underground fight club."),
            new Movie(6L, "Good Will Hunting", 1997, new String[]{"Drama"}, "Gus Van Sant", 
                      "A janitor at MIT solves a difficult math problem and gets discovered for his genius."),
            new Movie(7L, "A Beautiful Mind", 2001, new String[]{"Drama"}, "Ron Howard", 
                      "A brilliant but asocial mathematician struggles with schizophrenia and receives the Nobel Prize.")
        ));
    }

    @Override
    public Movie findById(Long id) {
        Movie result = movies.stream().filter(m -> m.getId().equals(id)).findFirst().orElse(null);

        return result;
    }
    
    @Override
    public List<Movie> findAll() {
        return movies;
    }

    @Override
    public Movie save(Movie movie) {
        movies.add(movie);

        return movie;
    }

    @Override
    public Movie update(Long id, Movie movie) {
        Movie existingMovie = movies.stream().filter(m -> m.getId().equals(id)).findFirst().orElse(null);

        if (existingMovie != null) {
            existingMovie.setTitle(movie.getTitle());
            existingMovie.setYear(movie.getYear());
            existingMovie.setGenres(movie.getGenres());
            existingMovie.setDirector(movie.getDirector());
            existingMovie.setSynopsis(movie.getSynopsis());
        }

        return existingMovie;
    }

    @Override
    public void delete(Long id) {
        movies.removeIf(m -> m.getId().equals(id));
    }
}
