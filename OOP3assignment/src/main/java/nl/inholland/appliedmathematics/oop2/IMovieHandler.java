package nl.inholland.appliedmathematics.oop2;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface IMovieHandler {
     
    
    void createMovie(MovieEntityDTO movieDTO);
    CompletableFuture<Void> saveMovieToDB(Movie movie);

    // The R from CRUD
    List<Movie> getAllMovies();
    List<Movie> getMovieByTitle(String title);
    List<Movie> getMovieByReleaseYear(int releaseYear);
    List<Movie> getMovieByDirector(String director);
    List<Movie> getMovieByGenre(String genre);
    List<Movie> getMovieBySimilarMovies(String similarMovies);
    List<Movie> getMovieByFilePaths(String filePaths);
    List<Movie> getMovieByWatched(boolean watched);
    List<Movie> getMovieByRating(int rating);
    List<String> getMovieNamesByRatingBound(int bound, boolean higher);
    boolean isBetweenYearBounds(int lower, int upper);
    float getDirectorRating(String director);

    // Thge U from CRUD
    Movie updateMovieTitleById(String title, UUID id);
    Movie updateMovieReleaseYearById(int releaseYear, UUID id);
    Movie updateMovieDirectorById(String director, UUID id);
    Movie updateMovieGenreById(String genre, UUID id);
    Movie updateMovieSimilarMoviesById(String similarMovies, UUID id);
    Movie updateMovieFilePathsById(String filePaths, UUID id);
    Movie updateMovieWatchedById(boolean watched, UUID id);
    Movie updateMovieRatingById(int rating, UUID id);

    // The D from CRUD
    void deleteMovieById(UUID id);


}
