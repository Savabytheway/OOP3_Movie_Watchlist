# OOP3_Movie_Watchlist
OOP3_Movie_Watchlist

This project is a RESTful API for managing a movie database, built with Maven and the Spring Boot framework. When a new movie is created via the API, the service asynchronously fetches detailed information from external sources wgich is The Movie Database, downloads related images, and saves the final, consolidated movie object to the database.

Features
    Full CRUD Functionality: Create, Read, Update, and Delete movie records.
    Asynchronous Operations: 
                Fetching movie details.
                Fetching similar movies and image paths.
                Downloading images.
                Saving the movie entity.
    External API Integration: Gathers movie data:
            OMDB API: For basic movie details (Title, Year, Director, Genre).
            TMDB API: For finding similar movies and poster/backdrop image paths.
    Rich Querying: API endpoints to search and filter movies by attributes like title, release year, director, genre, and rating.
    Exception Handling: error handling for issues like "Movie Not Found" or invalid input.

Technologies Used
    Java 21
    Spring Boot 3.x
    Spring Data JPA
    Lombok
    Maven

Project Structure and every file explained:

    nl/inholland/appliedmathematics/oop2
├── async/
│   └── AsyncManager.java       # Configures the virtual thread pool executor.
├── controller/
│   └── MovieManager.java       # REST controller for handling API requests.
├── exception/
│   ├── ExceptionMovieNotFound.java # exception for 404 errors.
│   └── ExceptionRatingOutOfBound.java # exception for invalid input.
├── model/
│   ├── Movie.java              # JPA entity for the Movie object.
│   └── MovieEntityDTO.java     # DTO for the movie creation request.
├── repository/
│   └── MovieRepo.java          # Spring Data JPA repository interface.
├── service/
│   ├── IImageHandler.java      # Interface for image processing services.
│   ├── ImageHandler.java       # image downloading.
│   ├── IMovieHandler.java      # Interface for the movie logic.
│   └── MovieHandler.java       # Implements the movie logic.
└── util/
    ├── DataBaseBase.java       # Utility for fetching data.
    ├── DataBaseImageRouting.java # Utility for fetching image paths.
    └── DataBaseSimilar.java    # Utility for fetching similar movies.
