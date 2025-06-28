package nl.inholland.appliedmathematics.oop2.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Entity class representing a Movie.
 * Uses Lombok annotations.
 * Annotated for JPA persistence.
 */
@Entity
@Builder
@Table
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    // Unique identifier for the movie (primary key).
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    // Movie title.
    private String title;

    // Year the movie was released.
    private int releaseYear;

    // Director of the movie.
    private String director;

    // Genre of the movie.
    private String genre;

    // String representing similar movies.
    private String similarMovies;

    // File paths associated with the movie.
    private String filePaths;

    // Watched status of the movie.
    private boolean watched;

    // Rating of the movie (e.g., 0-5).
    private int rating;

}
