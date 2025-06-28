package nl.inholland.appliedmathematics.oop2.model;

/**
 * Data Transfer Object (DTO) for transferring movie data.
 * Uses Java record for concise, immutable data representation.
 * Fields:
 *  - movieName: Name of the movie.
 *  - watched: Watched status.
 *  - rating: Movie rating.
 *  - destinationFolder: Destination folder for the movie.
 */
public record MovieEntityDTO(String movieName, boolean watched, int rating, String destinationFolder) {

    /**
     * Returns a string representation of the MovieEntityDTO in JSON-like format.
     * @return formatted string with movie details.
     */
    @Override
    public String toString(){
        return String.format("{ \"movieName\": %s, \"watched\": %b, \"rating\": %d, \"destinationFolder\": %s }", 
            movieName, watched, rating, destinationFolder);
    }
}

