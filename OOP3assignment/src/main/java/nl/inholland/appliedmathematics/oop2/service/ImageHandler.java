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

@Service
public class ImageHandler implements IImageHandler {
    @Override
    @Async("taskExecutor")
    public CompletableFuture<Void> downloadImage(String imagePath, String destinationFolder) {

        return CompletableFuture.runAsync(() -> {

            try{

                URL movieImageURL = new URI("https://image.tmdb.org/t/p/w780/" + imagePath).toURL();
                URLConnection movieImageUrlConnection = movieImageURL.openConnection();

                movieImageUrlConnection.setRequestProperty("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4MDMxMzVlYWM0YWYxZmZjMzMwNGJiYmE2MjIyMTUwZCIsIm5iZiI6MTc0NjA4OTc4NC4yODMsInN1YiI6IjY4MTMzNzM4YjYzNzA2NTVmYjkwZmYxZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.R_OxsImltSnT9Tk9IJuwm53fwGpBZQ6eMNe9ldM8FfA");
                movieImageUrlConnection.connect();

                InputStream in = movieImageUrlConnection.getInputStream();
                OutputStream out = new FileOutputStream(destinationFolder + "/" + imagePath);

                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = in.read(buffer)) != -1){

                    out.write(buffer, 0, bytesRead);

                }

                in.close();
                out.close();
       
            } catch(IOException e){
               
            } catch(URISyntaxException e){
                
            }

        });
        
    }

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
