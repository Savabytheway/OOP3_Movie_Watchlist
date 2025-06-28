package nl.inholland.appliedmathematics.oop2.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.inholland.appliedmathematics.oop2.model.Movie;
import nl.inholland.appliedmathematics.oop2.model.MovieEntityDTO;
import nl.inholland.appliedmathematics.oop2.service.IMovieHandler;

/**
 * REST controller for managing movies.
 * Provides endpoints for CRUD operations and various queries on movies.
 */
@RestController
@RequestMapping("/api/movie")
public class MovieManager {

    // Service for handling movie-related operations.
    private final IMovieHandler movieService;

    // Constructor for dependency injection of the movie service.
    public MovieManager(IMovieHandler movieService) {
        this.movieService = movieService;
    }

    /**
     * Create a new movie.
     * @param movieDTO Data transfer object containing movie details.
     * @return HTTP 201 Created if successful.
     */
    @PostMapping
    public ResponseEntity<Void> createPerson(@RequestBody MovieEntityDTO movieDTO) {
        movieService.createMovie(movieDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Get all movies.
     * @return List of all movies.
     */
    @GetMapping
    public ResponseEntity<List<Movie>> findAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    /**
     * Find movies by title.
     * @param title Movie title.
     * @return List of movies matching the title.
     */
    @GetMapping("/title/{title}")
    public ResponseEntity<List<Movie>> findMovieByTitle(@PathVariable String title) {
        return ResponseEntity.ok(movieService.getMovieByTitle(title));
    }

    /**
     * Find movies by release year.
     * @param releaseYear Release year.
     * @return List of movies released in the given year.
     */
    @GetMapping("/releaseYear/{releaseYear}")
    public ResponseEntity<List<Movie>> findMovieByReleaseYear(@PathVariable int releaseYear) {
        return ResponseEntity.ok(movieService.getMovieByReleaseYear(releaseYear));
    }

    /**
     * Find movies by director.
     * @param director Director's name.
     * @return List of movies by the given director.
     */
    @GetMapping("/director/{director}")
    public ResponseEntity<List<Movie>> findMovieByDirector(@PathVariable String director) {
        return ResponseEntity.ok(movieService.getMovieByDirector(director));
    }

    /**
     * Find movies by genre.
     * @param genre Genre name.
     * @return List of movies in the given genre.
     */
    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<Movie>> findMovieByGenre(@PathVariable String genre) {
        return ResponseEntity.ok(movieService.getMovieByGenre(genre));
    }

    /**
     * Find movies by similar movies.
     * @param similarMovies Similar movies string.
     * @return List of movies with similar movies.
     */
    @GetMapping("/similarMovies/{similarMovies}")
    public ResponseEntity<List<Movie>> findMovieBySimilarMovies(@PathVariable String similarMovies) {
        return ResponseEntity.ok(movieService.getMovieBySimilarMovies(similarMovies));
    }

    /**
     * Find movies by file paths.
     * @param filePaths File paths string.
     * @return List of movies with the given file paths.
     */
    @GetMapping("/filePaths/{filePaths}")
    public ResponseEntity<List<Movie>> findMovieByFilePaths(@PathVariable String filePaths) {
        return ResponseEntity.ok(movieService.getMovieByFilePaths(filePaths));
    }

    /**
     * Find movies by watched status.
     * @param watched Watched status.
     * @return List of movies with the given watched status.
     */
    @GetMapping("/watched/{watched}")
    public ResponseEntity<List<Movie>> findMovieByFileWatched(@PathVariable boolean watched) {
        return ResponseEntity.ok(movieService.getMovieByWatched(watched));
    }

    /**
     * Find movies by rating.
     * @param rating Movie rating.
     * @return List of movies with the given rating.
     */
    @GetMapping("/rating/{rating}")
    public ResponseEntity<List<Movie>> findMovieByRating(@PathVariable int rating) {
        return ResponseEntity.ok(movieService.getMovieByRating(rating));
    }

    /**
     * Find movie names by rating bound.
     * @param bound Rating bound.
     * @param higher True for movies with rating higher than bound, false for lower.
     * @return List of movie names matching the criteria.
     */
    @GetMapping("/rating/stream/{bound}/{higher}")
    public ResponseEntity<List<String>> findMovieByRatingBound(@PathVariable int bound, @PathVariable boolean higher) {
        return ResponseEntity.ok(movieService.getMovieNamesByRatingBound(bound, higher));
    }

    /**
     * Check if movies exist between year bounds.
     * @param lower Lower year bound.
     * @param upper Upper year bound.
     * @return True if movies exist between the bounds, false otherwise.
     */
    @GetMapping("/rating/stream/{lower}/{upper}")
    public ResponseEntity<Boolean> findMovieByYearBounds(@PathVariable int lower, @PathVariable int upper) {
        return ResponseEntity.ok(movieService.isBetweenYearBounds(lower, upper));
    }

    /**
     * Get average rating for a director.
     * @param director Director's name.
     * @return Average rating of the director's movies.
     */
    @GetMapping("/rating/stream/{director}")
    public ResponseEntity<Float> findAverageDirectorRating(@PathVariable String director) {
        return ResponseEntity.ok(movieService.getDirectorRating(director));
    }

    /**
     * Update movie title by ID.
     * @param id Movie ID.
     * @param title New title.
     * @return Updated movie.
     */
    @PutMapping("/{id}/title")
    public ResponseEntity<Movie> updateMovieTitleById(@PathVariable UUID id, @RequestBody String title) {
        Movie updatedPerson = movieService.updateMovieTitleById(title, id);
        return ResponseEntity.ok(updatedPerson);
    }

    /**
     * Update movie release year by ID.
     * @param id Movie ID.
     * @param releaseYear New release year.
     * @return Updated movie.
     */
    @PutMapping("/{id}/releaseYear")
    public ResponseEntity<Movie> updateMovieReleaseYearById(@PathVariable UUID id, @RequestBody int releaseYear) {
        Movie updatedPerson = movieService.updateMovieReleaseYearById(releaseYear, id);
        return ResponseEntity.ok(updatedPerson);
    }

    /**
     * Update movie director by ID.
     * @param id Movie ID.
     * @param director New director.
     * @return Updated movie.
     */
    @PutMapping("/{id}/director")
    public ResponseEntity<Movie> updateMovieDirectorById(@PathVariable UUID id, @RequestBody String director) {
        Movie updatedPerson = movieService.updateMovieDirectorById(director, id);
        return ResponseEntity.ok(updatedPerson);
    }

    /**
     * Update movie genre by ID.
     * @param id Movie ID.
     * @param genre New genre.
     * @return Updated movie.
     */
    @PutMapping("/{id}/genre")
    public ResponseEntity<Movie> updateMovieGenreById(@PathVariable UUID id, @RequestBody String genre) {
        Movie updatedPerson = movieService.updateMovieGenreById(genre, id);
        return ResponseEntity.ok(updatedPerson);
    }

    /**
     * Update similar movies by ID.
     * @param id Movie ID.
     * @param similarMovies New similar movies string.
     * @return Updated movie.
     */
    @PutMapping("/{id}/similarMovies")
    public ResponseEntity<Movie> updateMovieSimilarMoviesById(@PathVariable UUID id, @RequestBody String similarMovies) {
        Movie updatedPerson = movieService.updateMovieSimilarMoviesById(similarMovies, id);
        return ResponseEntity.ok(updatedPerson);
    }

    /**
     * Update file paths by ID.
     * @param id Movie ID.
     * @param filePaths New file paths string.
     * @return Updated movie.
     */
    @PutMapping("/{id}/filePaths")
    public ResponseEntity<Movie> updateMovieFilePathsById(@PathVariable UUID id, @RequestBody String filePaths) {
        Movie updatedPerson = movieService.updateMovieFilePathsById(filePaths, id);
        return ResponseEntity.ok(updatedPerson);
    }

    /**
     * Update watched status by ID.
     * @param id Movie ID.
     * @param watched New watched status.
     * @return Updated movie.
     */
    @PutMapping("/{id}/watched")
    public ResponseEntity<Movie> updateMovieWatchedById(@PathVariable UUID id, @RequestBody boolean watched) {
        Movie updatedPerson = movieService.updateMovieWatchedById(watched, id);
        return ResponseEntity.ok(updatedPerson);
    }

    /**
     * Update movie rating by ID.
     * @param id Movie ID.
     * @param rating New rating.
     * @return Updated movie.
     */
    @PutMapping("/{id}/rating")
    public ResponseEntity<Movie> updateMovieRatingById(@PathVariable UUID id, @RequestBody int rating) {
        Movie updatedPerson = movieService.updateMovieRatingById(rating, id);
        return ResponseEntity.ok(updatedPerson);
    }

    /**
     * Delete a movie by ID.
     * @param id Movie ID.
     * @return HTTP 204 No Content if successful.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable UUID id) {
        movieService.deleteMovieById(id);
        return ResponseEntity.noContent().build();
    }

}
