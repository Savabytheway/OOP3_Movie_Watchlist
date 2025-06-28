package nl.inholland.appliedmathematics.oop2.service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import nl.inholland.appliedmathematics.oop2.model.Movie;
import nl.inholland.appliedmathematics.oop2.model.MovieEntityDTO;

/**
 * Interface for handling movie operations.
 * Defines methods for crud,
 * as well as various custom queries and asynchronous operations.
 */
public interface IMovieHandler {
     
    /**
     * Creates a new movie from the provided DTO.
     * @param movieDTO Data transfer object containing movie details.
     */
    void createMovie(MovieEntityDTO movieDTO);

    /**
     * Saves a movie to the database asynchronously.
     * @param movie Movie entity to save.
     * @return CompletableFuture representing the asynchronous operation.
     */
    CompletableFuture<Void> saveMovieToDB(Movie movie);

    // The R from CRUD (Read operations)

    /**
     * Retrieves all movies.
     * @return List of all movies.
     */
    List<Movie> getAllMovies();

    /**
     * Retrieves movies by title.
     * @param title Movie title.
     * @return List of movies matching the title.
     */
    List<Movie> getMovieByTitle(String title);

    /**
     * Retrieves movies by release year.
     * @param releaseYear Release year.
     * @return List of movies released in the given year.
     */
    List<Movie> getMovieByReleaseYear(int releaseYear);

    /**
     * Retrieves movies by director.
     * @param director Director's name.
     * @return List of movies by the given director.
     */
    List<Movie> getMovieByDirector(String director);

    /**
     * Retrieves movies by genre.
     * @param genre Genre name.
     * @return List of movies in the given genre.
     */
    List<Movie> getMovieByGenre(String genre);

    /**
     * Retrieves movies by similar movies string.
     * @param similarMovies Similar movies string.
     * @return List of movies with similar movies.
     */
    List<Movie> getMovieBySimilarMovies(String similarMovies);

    /**
     * Retrieves movies by file paths.
     * @param filePaths File paths string.
     * @return List of movies with the given file paths.
     */
    List<Movie> getMovieByFilePaths(String filePaths);

    /**
     * Retrieves movies by watched status.
     * @param watched Watched status.
     * @return List of movies with the given watched status.
     */
    List<Movie> getMovieByWatched(boolean watched);

    /**
     * Retrieves movies by rating.
     * @param rating Movie rating.
     * @return List of movies with the given rating.
     */
    List<Movie> getMovieByRating(int rating);

    /**
     * Retrieves movie names by rating bound.
     * @param bound Rating bound.
     * @param higher True for movies with rating higher than bound, false for lower.
     * @return List of movie names matching the criteria.
     */
    List<String> getMovieNamesByRatingBound(int bound, boolean higher);

    /**
     * Checks if movies exist between year bounds.
     * @param lower Lower year bound.
     * @param upper Upper year bound.
     * @return True if movies exist between the bounds, false otherwise.
     */
    boolean isBetweenYearBounds(int lower, int upper);

    /**
     * Gets the average rating for a director.
     * @param director Director's name.
     * @return Average rating of the director's movies.
     */
    float getDirectorRating(String director);

    // The U from CRUD (Update operations)

    /**
     * Updates the movie title by ID.
     * @param title New title.
     * @param id Movie ID.
     * @return Updated movie.
     */
    Movie updateMovieTitleById(String title, UUID id);

    /**
     * Updates the movie release year by ID.
     * @param releaseYear New release year.
     * @param id Movie ID.
     * @return Updated movie.
     */
    Movie updateMovieReleaseYearById(int releaseYear, UUID id);

    /**
     * Updates the movie director by ID.
     * @param director New director.
     * @param id Movie ID.
     * @return Updated movie.
     */
    Movie updateMovieDirectorById(String director, UUID id);

    /**
     * Updates the movie genre by ID.
     * @param genre New genre.
     * @param id Movie ID.
     * @return Updated movie.
     */
    Movie updateMovieGenreById(String genre, UUID id);

    /**
     * Updates similar movies by ID.
     * @param similarMovies New similar movies string.
     * @param id Movie ID.
     * @return Updated movie.
     */
    Movie updateMovieSimilarMoviesById(String similarMovies, UUID id);

    /**
     * Updates file paths by ID.
     * @param filePaths New file paths string.
     * @param id Movie ID.
     * @return Updated movie.
     */
    Movie updateMovieFilePathsById(String filePaths, UUID id);

    /**
     * Updates watched status by ID.
     * @param watched New watched status.
     * @param id Movie ID.
     * @return Updated movie.
     */
    Movie updateMovieWatchedById(boolean watched, UUID id);

    /**
     * Updates movie rating by ID.
     * @param rating New rating.
     * @param id Movie ID.
     * @return Updated movie.
     */
    Movie updateMovieRatingById(int rating, UUID id);

    // The D from CRUD (Delete operation)

    /**
     * Deletes a movie by ID.
     * @param id Movie ID.
     */
    void deleteMovieById(UUID id);


}
