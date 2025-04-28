package com.movies.movies_api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.movies.movies_api.models.Movie;
import com.movies.movies_api.repositories.MovieRepositoryTest;
import com.movies.movies_api.repositories.interfaces.IMovieRepository;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(MovieRepositoryTest.class);

    @InjectMocks
    private MovieService movieService;

    @Mock
    private IMovieRepository movieRepository;

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        logger.info("==== Initializing [{}] ====", testInfo.getDisplayName());
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
        logger.info("==== Finished [{}] ====", testInfo.getDisplayName());
    }

    @Test
    @DisplayName("Service save movie test")
    public void saveMovieTest() {
        Movie movie = Movie.builder()
            .title("Titanic")
            .year(1997)
            .genre("Drama")
            .director("James Cameron")
            .synopsis("A seventeen-year-old aristocrat falls in love with a kind but poor artist aboard the luxurious, ill-fated R.M.S. Titanic.")
            .build();

        
        when(movieRepository.save(movie)).thenReturn(movie);

        Movie savedMovie = movieService.save(movie).get();

        assertNotNull(savedMovie);
        assertEquals(movie.getTitle(), savedMovie.getTitle());
        assertEquals(movie.getYear(), savedMovie.getYear());
        assertEquals(movie.getGenre(), savedMovie.getGenre());
        assertEquals(movie.getDirector(), savedMovie.getDirector());
        assertEquals(movie.getSynopsis(), savedMovie.getSynopsis());
    }
}
