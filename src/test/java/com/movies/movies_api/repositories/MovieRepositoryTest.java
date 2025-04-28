package com.movies.movies_api.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.movies.movies_api.models.Movie;
import com.movies.movies_api.repositories.interfaces.IMovieRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MovieRepositoryTest {
    private static final Logger logger = LoggerFactory.getLogger(MovieRepositoryTest.class);
    private final IMovieRepository movieRepository;
    
    @Autowired
    public MovieRepositoryTest(IMovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        logger.info("==== Initializing [{}] ====", testInfo.getDisplayName());
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
        logger.info("==== Finished [{}] ====", testInfo.getDisplayName());
    }
    
    @Test
    @DisplayName("Repository save movie test")
    public void saveMovieTest() {
        Movie movie = Movie.builder()
            .title("Titanic")
            .year(1997)
            .genre("Drama")
            .director("James Cameron")
            .synopsis("A seventeen-year-old aristocrat falls in love with a kind but poor artist aboard the luxurious, ill-fated R.M.S. Titanic.")
            .build();

        Movie savedMovie = movieRepository.save(movie);

        assertNotNull(savedMovie.getId());
        assertEquals(movie.getTitle(), savedMovie.getTitle());
        assertEquals(movie.getYear(), savedMovie.getYear());
        assertEquals(movie.getGenre(), savedMovie.getGenre());
        assertEquals(movie.getDirector(), savedMovie.getDirector());
        assertEquals(movie.getSynopsis(), savedMovie.getSynopsis());
    }

    @Test
    @DisplayName("Repository find movie by ID test")
    public void findMovieByIdTest() {
        Movie movie = Movie.builder()
            .title("Inception")
            .year(2010)
            .genre("Sci-Fi")
            .director("Christopher Nolan")
            .synopsis("A thief who steals corporate secrets through dream-sharing technology is given the inverse task of planting an idea.")
            .build();

        Movie savedMovie = movieRepository.save(movie);

        Optional<Movie> foundMovie = movieRepository.findById(savedMovie.getId());

        assertNotNull(foundMovie);
        assertEquals(savedMovie.getId(), foundMovie.get().getId());
        assertEquals(savedMovie.getTitle(), foundMovie.get().getTitle());
    }
}
