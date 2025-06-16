package nl.inholland.appliedmathematics.oop2;

import java.util.concurrent.CompletableFuture;


public interface IImageHandler {

    CompletableFuture<Void> downloadImage(String imagePath, String destinationFolder);
    CompletableFuture<Void> copyImage(String source, String destination);
}
