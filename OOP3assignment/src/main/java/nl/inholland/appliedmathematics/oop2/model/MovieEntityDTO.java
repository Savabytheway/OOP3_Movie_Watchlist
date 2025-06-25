package nl.inholland.appliedmathematics.oop2.model;

public record MovieEntityDTO(String movieName, boolean watched, int rating, String destinationFolder) {

        @Override
        public String toString(){
            return String.format("{ \"movieName\": %s, \"watched\": %b, \"rating\": %d, \"destinationFolder\": %s }", 
            movieName, watched, rating, destinationFolder);
        }
}

