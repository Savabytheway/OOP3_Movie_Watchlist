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

import nl.inholland.appliedmathematics.oop2.IMovieHandler;
import nl.inholland.appliedmathematics.oop2.model.Movie;
import nl.inholland.appliedmathematics.oop2.model.MovieEntityDTO;

@RestController
@RequestMapping("/api/movie")
public class MovieManager {

    private final IMovieHandler movieService;

    public MovieManager(IMovieHandler movieService) {
        this.movieService = movieService;
    }


    @PostMapping
    public ResponseEntity<Void> createPerson(@RequestBody MovieEntityDTO movieDTO) {
        movieService.createMovie(movieDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
        

    @GetMapping
    public ResponseEntity<List<Movie>> findAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }


    @GetMapping("/title/{title}")
    public ResponseEntity<List<Movie>> findMovieByTitle(@PathVariable String title) {
        return ResponseEntity.ok(movieService.getMovieByTitle(title));
    }


    @GetMapping("/releaseYear/{releaseYear}")
    public ResponseEntity<List<Movie>> findMovieByReleaseYear(@PathVariable int releaseYear) {
        return ResponseEntity.ok(movieService.getMovieByReleaseYear(releaseYear));
    }


    @GetMapping("/director/{director}")
    public ResponseEntity<List<Movie>> findMovieByDirector(@PathVariable String director) {
        return ResponseEntity.ok(movieService.getMovieByDirector(director));
    }


    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<Movie>> findMovieByGenre(@PathVariable String genre) {
        return ResponseEntity.ok(movieService.getMovieByGenre(genre));
    }


    @GetMapping("/similarMovies/{similarMovies}")
    public ResponseEntity<List<Movie>> findMovieBySimilarMovies(@PathVariable String similarMovies) {
        return ResponseEntity.ok(movieService.getMovieBySimilarMovies(similarMovies));
    }


    @GetMapping("/filePaths/{filePaths}")
    public ResponseEntity<List<Movie>> findMovieByFilePaths(@PathVariable String filePaths) {
        return ResponseEntity.ok(movieService.getMovieByFilePaths(filePaths));
    }


    @GetMapping("/watched/{watched}")
    public ResponseEntity<List<Movie>> findMovieByFileWatched(@PathVariable boolean watched) {
        return ResponseEntity.ok(movieService.getMovieByWatched(watched));
    }


    @GetMapping("/rating/{rating}")
    public ResponseEntity<List<Movie>> findMovieByRating(@PathVariable int rating) {
        return ResponseEntity.ok(movieService.getMovieByRating(rating));
    }


    @GetMapping("/rating/stream/{bound}/{higher}")
    public ResponseEntity<List<String>> findMovieByRatingBound(@PathVariable int bound, @PathVariable boolean higher) {
        return ResponseEntity.ok(movieService.getMovieNamesByRatingBound(bound, higher));
    }


    @GetMapping("/rating/stream/{lower}/{upper}")
    public ResponseEntity<Boolean> findMovieByYearBounds(@PathVariable int lower, @PathVariable int upper) {
        return ResponseEntity.ok(movieService.isBetweenYearBounds(lower, upper));
    }


    @GetMapping("/rating/stream/{director}")
    public ResponseEntity<Float> findAverageDirectorRating(@PathVariable String director) {
        return ResponseEntity.ok(movieService.getDirectorRating(director));
    }


    @PutMapping("/{id}/title")
    public ResponseEntity<Movie> updateMovieTitleById(@PathVariable UUID id, @RequestBody String title) {
        Movie updatedPerson = movieService.updateMovieTitleById(title, id);
        return ResponseEntity.ok(updatedPerson);
    }


    @PutMapping("/{id}/releaseYear")
    public ResponseEntity<Movie> updateMovieReleaseYearById(@PathVariable UUID id, @RequestBody int releaseYear) {
        Movie updatedPerson = movieService.updateMovieReleaseYearById(releaseYear, id);
        return ResponseEntity.ok(updatedPerson);
    }


    @PutMapping("/{id}/director")
    public ResponseEntity<Movie> updateMovieDirectorById(@PathVariable UUID id, @RequestBody String director) {
        Movie updatedPerson = movieService.updateMovieDirectorById(director, id);
        return ResponseEntity.ok(updatedPerson);
    }


    @PutMapping("/{id}/genre")
    public ResponseEntity<Movie> updateMovieGenreById(@PathVariable UUID id, @RequestBody String genre) {
        Movie updatedPerson = movieService.updateMovieGenreById(genre, id);
        return ResponseEntity.ok(updatedPerson);
    }


    @PutMapping("/{id}/similarMovies")
    public ResponseEntity<Movie> updateMovieSimilarMoviesById(@PathVariable UUID id, @RequestBody String similarMovies) {
        Movie updatedPerson = movieService.updateMovieSimilarMoviesById(similarMovies, id);
        return ResponseEntity.ok(updatedPerson);
    }


    @PutMapping("/{id}/filePaths")
    public ResponseEntity<Movie> updateMovieFilePathsById(@PathVariable UUID id, @RequestBody String filePaths) {
        Movie updatedPerson = movieService.updateMovieFilePathsById(filePaths, id);
        return ResponseEntity.ok(updatedPerson);
    }


    @PutMapping("/{id}/watched")
    public ResponseEntity<Movie> updateMovieWatchedById(@PathVariable UUID id, @RequestBody boolean watched) {
        Movie updatedPerson = movieService.updateMovieWatchedById(watched, id);
        return ResponseEntity.ok(updatedPerson);
    }


    @PutMapping("/{id}/rating")
    public ResponseEntity<Movie> updateMovieRatingById(@PathVariable UUID id, @RequestBody int rating) {
        Movie updatedPerson = movieService.updateMovieRatingById(rating, id);
        return ResponseEntity.ok(updatedPerson);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable UUID id) {
        movieService.deleteMovieById(id);
        return ResponseEntity.noContent().build();
    }

}
