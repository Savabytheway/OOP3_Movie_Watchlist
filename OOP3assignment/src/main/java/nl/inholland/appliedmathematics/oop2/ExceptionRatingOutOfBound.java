package nl.inholland.appliedmathematics.oop2;

public class ExceptionRatingOutOfBound extends RuntimeException{

    public ExceptionRatingOutOfBound(){
        super("Write a number that is between zero and five.");
    }

}