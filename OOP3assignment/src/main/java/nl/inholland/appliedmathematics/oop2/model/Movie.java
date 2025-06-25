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

@Entity
@Builder
@Table
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

// Then of course my Movie class with only the attributes and little to no extra behaviour.

public class Movie{

    @Id
    @Column(nullable = false)

    // Very important unique identifier!
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String title;
    private int releaseYear;
    private String director;
    private String genre;
    private String similarMovies;
    private String filePaths;
    private boolean watched;
    private int rating;

}
