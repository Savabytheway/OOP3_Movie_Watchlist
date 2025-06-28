package nl.inholland.appliedmathematics.oop2.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nl.inholland.appliedmathematics.oop2.model.Movie;

/**
 * Repository interface for Movie entities.
 * Extends JpaRepository to provide CRUD operations and custom queries.
 */
@Repository
public interface MovieRepo extends JpaRepository<Movie, UUID>{

    // Find movies by title.
    List<Movie> findByTitle(String title);

    // Find movies by release year.
    List<Movie> findByReleaseYear(int releaseYear);

    // Find movies by director.
    List<Movie> findByDirector(String director);

    // Find movies by genre.
    List<Movie> findByGenre(String genre);

    // Find movies by similar movies string.
    List<Movie> findBySimilarMovies(String similarMovies);

    // Find movies by file paths.
    List<Movie> findByFilePaths(String filePaths);

    // Find movies by watched status.
    List<Movie> findByWatched(boolean watched);

    // Find movies by rating.
    List<Movie> findByRating(int rating);

}
