package nl.inholland.appliedmathematics.oop2.exception;

/**
 * Custom exception thrown when a movie is not found.
 * Extends RuntimeException to allow for unchecked exception handling.
 */
public class ExceptionMovieNotFound extends RuntimeException {

    /**
     * Constructs a new ExceptionMovieNotFound with the specified detail message.
     * @param message the detail message explaining the exception.
     */
    public ExceptionMovieNotFound(String message) {
        super(message);
    }

}
