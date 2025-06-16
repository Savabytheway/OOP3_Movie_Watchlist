package nl.inholland.appliedmathematics.oop2;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MovieHandler implements IMovieHandler{

    private final MovieRepo movieRepository;
    private final IImageHandler imageService;

    @Override
    public void createMovie(MovieEntityDTO movieDTO) {

        System.out.println("Handling POST - adding a movie and downloading images. Time: " + System.currentTimeMillis());

        
        List<String> myBasic = DataBaseBase.getMovieInfo(movieDTO.movieName()); 
        List<String> mySimilar = DataBaseSimilar.getSimilarMovies(movieDTO.movieName()); 
        List<String> myImagePaths = DataBaseImageRouting.getImageFilePaths(movieDTO.movieName());

        List<String> myFilePaths = new ArrayList<>();

        for (String path : myImagePaths){
            myFilePaths.add(movieDTO.destinationFolder().concat("/" + path));
        }

        Movie myMovie = Movie.builder()
        .title(myBasic.get(0))
        .releaseYear(Integer.valueOf(myBasic.get(1)))
        .director(myBasic.get(2))
        .genre(myBasic.get(3))
        .similarMovies(mySimilar.toString())
        .filePaths(myFilePaths.toString())
        .watched(movieDTO.watched())
        .rating(movieDTO.rating())
        .build();

        if(myMovie.getRating() < 0 || myMovie.getRating() > 5){
            throw new ExceptionRatingOutOfBound();
        }

        CompletableFuture<Void> task1 = saveMovieToDB(myMovie);
        CompletableFuture<Void> task2 = imageService.downloadImage(myImagePaths.get(0), movieDTO.destinationFolder());
        CompletableFuture<Void> task3 = imageService.downloadImage(myImagePaths.get(1), movieDTO.destinationFolder());
        CompletableFuture<Void> task4 = imageService.downloadImage(myImagePaths.get(2), movieDTO.destinationFolder());

        CompletableFuture.allOf(task1, task2, task3, task4).join();

        System.out.println("Finished. Time: " + System.currentTimeMillis());
    }


    @Override
    public CompletableFuture<Void> saveMovieToDB(Movie movie) {

        return CompletableFuture.runAsync(() -> {
            System.out.println("Starting the interaction with the DB: " + System.currentTimeMillis());
            movieRepository.save(movie);
            System.out.println("Finished the interaction with the DB: " + System.currentTimeMillis());
        });
    }


    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }


    @Override
    public List<Movie> getMovieByTitle(String title) {
        return movieRepository.findByTitle(title);
    }


    @Override
    public List<Movie> getMovieByReleaseYear(int releaseYear) {
        return movieRepository.findByReleaseYear(releaseYear);
    }

    
    @Override
    public List<Movie> getMovieByDirector(String director) {
        return movieRepository.findByDirector(director);
    }


    @Override
    public List<Movie> getMovieByGenre(String genre) {
        return movieRepository.findByGenre(genre);
    }


    @Override
    public List<Movie> getMovieBySimilarMovies(String similarMovies) {
        return movieRepository.findBySimilarMovies(similarMovies);
    }


    @Override
    public List<Movie> getMovieByFilePaths(String filePaths) {
        return movieRepository.findByFilePaths(filePaths);
    }


    @Override
    public List<Movie> getMovieByWatched(boolean watched) {
        return movieRepository.findByWatched(watched);
    }


    @Override
    public List<Movie> getMovieByRating(int rating) {
        return movieRepository.findByRating(rating);
    }


    @Override
    public List<String> getMovieNamesByRatingBound(int bound, boolean higher){

        List<Movie> movies = movieRepository.findAll();

        List<String> filtered = new ArrayList<>();

        if(higher){
            filtered = movies.stream()
            .filter(m -> m.getRating() >= bound)
            .map(Movie::getTitle)
            .sorted()
            .collect(Collectors.toList());
        } else {
            filtered = movies.stream()
            .filter(m -> m.getRating() < bound)
            .map(Movie::getTitle)
            .sorted()
            .collect(Collectors.toList());
        }

        return filtered;
    }

    @Override
    public boolean isBetweenYearBounds(int lower, int upper){

        List<Movie> movies = movieRepository.findAll();

        boolean isBetween = movies.stream()
        .anyMatch(m -> m.getReleaseYear() >= lower && m.getReleaseYear() <= upper);

        return isBetween;
    }


    // Get the average director rating if it exits in the database.
    public float getDirectorRating(String director){

        List<Movie> movies = movieRepository.findAll();

        List<Integer> ratings = movies.stream()
        .filter(m -> m.getDirector().equals(director))
        .map(Movie::getRating)
        .collect(Collectors.toList());

        int ratingSum = 0;

        for(int i = 0; i < ratings.size(); i++){
            ratingSum += ratings.get(i);
        }

        return ratingSum / ratings.size();
    }


    @Override
    public Movie updateMovieTitleById(String title, UUID id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new ExceptionMovieNotFound("No movie found with id: " + id));
        movie.setTitle(title);
        return movieRepository.save(movie);
    }


    @Override
    public Movie updateMovieReleaseYearById(int releaseYear, UUID id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new ExceptionMovieNotFound("No movie found with id: " + id));
        movie.setReleaseYear(releaseYear);
        return movieRepository.save(movie);
    }


    @Override
    public Movie updateMovieDirectorById(String director, UUID id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new ExceptionMovieNotFound("No movie found with id: " + id));
        movie.setDirector(director);
        return movieRepository.save(movie);
    }


    @Override
    public Movie updateMovieGenreById(String genre, UUID id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new ExceptionMovieNotFound("No movie found with id: " + id));
        movie.setGenre(genre);
        return movieRepository.save(movie);
    }


    @Override
    public Movie updateMovieSimilarMoviesById(String similarMovies, UUID id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new ExceptionMovieNotFound("No movie found with id: " + id));
        movie.setSimilarMovies(similarMovies);
        return movieRepository.save(movie);
    }


    @Override
    public Movie updateMovieFilePathsById(String filePaths, UUID id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new ExceptionMovieNotFound("No movie found with id: " + id));
        movie.setFilePaths(filePaths);
        return movieRepository.save(movie);
    }


    @Override
    public Movie updateMovieWatchedById(boolean watched, UUID id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new ExceptionMovieNotFound("No movie found with id: " + id));
        movie.setWatched(watched);
        return movieRepository.save(movie);
    }


    @Override
    public Movie updateMovieRatingById(int rating, UUID id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new ExceptionMovieNotFound("No movie found with id: " + id));
        movie.setRating(rating);
        return movieRepository.save(movie);
    }


    @Override
    public void deleteMovieById(UUID id) {
        movieRepository.deleteById(id);
    }
 
}
