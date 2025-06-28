package nl.inholland.appliedmathematics.oop2.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Service implementation for handling image operations asynchronously.
 * Provides methods to download and copy images using CompletableFuture.
 */
@Service
public class ImageHandler implements IImageHandler {

    /**
     * Downloads an image asynchronously from a remote URL to the specified destination folder.
     * Uses a bearer token for authorization.
     * @param imagePath Path or filename of the image to download.
     * @param destinationFolder Folder where the image will be saved.
     * @return CompletableFuture representing the asynchronous operation.
     */
    @Override
    @Async("taskExecutor")
    public CompletableFuture<Void> downloadImage(String imagePath, String destinationFolder) {

        return CompletableFuture.runAsync(() -> {

            try{
                // Build the full image URL using the provided image path.
                URL movieImageURL = new URI("https://image.tmdb.org/t/p/w780/" + imagePath).toURL();
                URLConnection movieImageUrlConnection = movieImageURL.openConnection();

                // Set the authorization header for the image request.
                movieImageUrlConnection.setRequestProperty("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4MDMxMzVlYWM0YWYxZmZjMzMwNGJiYmE2MjIyMTUwZCIsIm5iZiI6MTc0NjA4OTc4NC4yODMsInN1YiI6IjY4MTMzNzM4YjYzNzA2NTVmYjkwZmYxZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.R_OxsImltSnT9Tk9IJuwm53fwGpBZQ6eMNe9ldM8FfA");
                movieImageUrlConnection.connect();

                // Open input and output streams for downloading the image.
                InputStream in = movieImageUrlConnection.getInputStream();
                OutputStream out = new FileOutputStream(destinationFolder + "/" + imagePath);

                byte[] buffer = new byte[1024];
                int bytesRead;

                // Read from the input stream and write to the output stream.
                while ((bytesRead = in.read(buffer)) != -1){
                    out.write(buffer, 0, bytesRead);
                }

                // Close streams after operation.
                in.close();
                out.close();

            } catch(IOException e){
                // Handle IO exceptions (e.g., log or rethrow as needed).
            } catch(URISyntaxException e){
                // Handle URI syntax exceptions.
            }

        });

    }

    /**
     * Copies an image asynchronously from the source path to the destination path.
     * @param source Source path of the image file.
     * @param destination Destination path for the copied image.
     * @return CompletableFuture representing the asynchronous operation.
     */
    @Override
    @Async("taskExecutor")
    public CompletableFuture<Void> copyImage(String source, String destination) {
        return CompletableFuture.runAsync(() -> {
            try {
                System.out.println("Processing image: " + source + " at " + System.currentTimeMillis());
                Files.copy(Path.of(source), Path.of(destination), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Image processing finished: " + destination + " at " + System.currentTimeMillis());
            } catch (IOException e) {
                throw new RuntimeException("Error processing image: " + source, e);
            }
        });
    }
}
