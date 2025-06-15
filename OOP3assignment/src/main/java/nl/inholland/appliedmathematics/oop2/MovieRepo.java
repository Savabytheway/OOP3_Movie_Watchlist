package nl.inholland.appliedmathematics.oop2;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepo extends JpaRepository<Movie, UUID>{


    List<Movie> findByTitle(String title);
    List<Movie> findByReleaseYear(int releaseYear);
    List<Movie> findByDirector(String director);
    List<Movie> findByGenre(String genre);
    List<Movie> findBySimilarMovies(String similarMovies);
    List<Movie> findByFilePaths(String filePaths);
    List<Movie> findByWatched(boolean watched);
    List<Movie> findByRating(int rating);

}
