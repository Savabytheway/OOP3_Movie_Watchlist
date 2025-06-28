package nl.inholland.appliedmathematics.oop2.exception;

/**
 * Custom exception thrown when a movie rating is out of the allowed bounds.
 * Extends RuntimeException to allow for unchecked exception handling.
 */
public class ExceptionRatingOutOfBound extends RuntimeException{

    /**
     * Constructs a new ExceptionRatingOutOfBound with a default error message.
     */
    public ExceptionRatingOutOfBound(){
        super("Write a number that is between zero and five.");
    }

}