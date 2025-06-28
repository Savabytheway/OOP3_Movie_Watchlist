package nl.inholland.appliedmathematics.oop2.service;

import java.util.concurrent.CompletableFuture;

/**
 * Interface for handling image operations.
 * Provides methods for downloading and copying images using CompletableFuture.
 */
public interface IImageHandler {

    /**
     * Downloads an image asynchronously to the specified destination folder.
     * @param imagePath Path or URL of the image to download.
     * @param destinationFolder Folder where the image will be saved.
     * @return CompletableFuture representing the asynchronous operation.
     */
    CompletableFuture<Void> downloadImage(String imagePath, String destinationFolder);

    /**
     * Copies an image asynchronously from the source to the destination.
     * @param source Source path of the image.
     * @param destination Destination path for the copied image.
     * @return CompletableFuture representing the asynchronous operation.
     */
    CompletableFuture<Void> copyImage(String source, String destination);
}
