package nl.inholland.appliedmathematics.oop2.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ImageHandlerTest {
    
private ImageHandler imageService;

    @BeforeEach
    void setUp() {
        imageService = new ImageHandler();
    }

    @Test
    void testDownloadImage_completesSuccessfully() throws Exception {

        String dummyImagePath = "2w4xG178RpB4MDAIfTkqAuSJzec.jpg";
        String tempDir = System.getProperty("java.io.tmpdir");

        CompletableFuture<Void> future = imageService.downloadImage(dummyImagePath, tempDir);

        future.get(); 

        File createdFile = new File(tempDir, dummyImagePath);
        assertTrue(createdFile.exists(), "File was created");

        createdFile.delete();
    }

    @Test
    void testCopyImage_copiesFileSuccessfully() throws Exception {

        Path source = Files.createTempFile("sourceImage", ".tmp");
        Files.writeString(source, "dummy data");

        Path dest = Path.of(source.getParent().toString(), "copiedImage.tmp");

        CompletableFuture<Void> future = imageService.copyImage(source.toString(), dest.toString());

        future.get(); 

        assertTrue(Files.exists(dest), "Destination now exists");
        assertEquals("dummy data", Files.readString(dest));

    
        Files.deleteIfExists(source);
        Files.deleteIfExists(dest);
    }

    @Test
    void testCopyImage_throwsRuntimeExceptionOnError() {
  

        CompletableFuture<Void> future = imageService.copyImage("nonexistent_file.tmp", "dest.tmp");

        ExecutionException ex = assertThrows(ExecutionException.class, future::get);
        assertTrue(ex.getCause() instanceof RuntimeException);
        assertTrue(ex.getCause().getMessage().contains("Error processing image"));
    }
}