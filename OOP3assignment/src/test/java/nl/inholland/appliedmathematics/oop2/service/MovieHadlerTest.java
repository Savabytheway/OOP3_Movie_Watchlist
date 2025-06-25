package nl.inholland.appliedmathematics.oop2.service;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import nl.inholland.appliedmathematics.oop2.model.Movie;
import nl.inholland.appliedmathematics.oop2.repository.MovieRepo;


@ExtendWith(MockitoExtension.class)
class MovieHandlerTest {

    @Mock
    private MovieRepo movieRepo;

    @Mock
    private IImageHandler imageHandler;

    @InjectMocks
    private MovieHandler movieHandler;

    @BeforeEach
    void setup() {
       
    }

    @Test
    void testGetMovieNamesByRatingBound_HigherTrue() {
        List<Movie> mockMovies = List.of(
            Movie.builder().title("Interstellar").rating(5).build(),
            Movie.builder().title("The Room").rating(2).build()
        );

        Mockito.when(movieRepo.findAll()).thenReturn(mockMovies);

        List<String> result = movieHandler.getMovieNamesByRatingBound(3, true);

        Assertions.assertEquals(List.of("Interstellar"), result);
    }

    @Test
    void testIsBetweenYearBounds_true() {
        List<Movie> mockMovies = List.of(
            Movie.builder().releaseYear(2010).build(),
            Movie.builder().releaseYear(2020).build()
        );

        Mockito.when(movieRepo.findAll()).thenReturn(mockMovies);

        boolean result = movieHandler.isBetweenYearBounds(2005, 2015);

        Assertions.assertTrue(result);
    }

    @Test
    void testGetDirectorRating() {
        List<Movie> mockMovies = List.of(
            Movie.builder().director("Nolan").rating(5).build(),
            Movie.builder().director("Nolan").rating(3).build()
        );

        Mockito.when(movieRepo.findAll()).thenReturn(mockMovies);

        float rating = movieHandler.getDirectorRating("Nolan");

        Assertions.assertEquals(4.0, rating);
    }

}
