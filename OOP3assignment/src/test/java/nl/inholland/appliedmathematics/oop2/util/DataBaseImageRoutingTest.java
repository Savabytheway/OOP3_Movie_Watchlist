package nl.inholland.appliedmathematics.oop2.util;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class DataBaseImageRoutingTest {
    @Test
    void testRequestUnstructutredImageFilePaths() {

        String TMDB_APITOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzOTc1ZjdlNzNiMDU2NjJhNjFlZDk5NjMxZGZiYTg3ZCIsIm5iZiI6MTc0OTUwODg2MC4yNDg5OTk4LCJzdWIiOiI2ODQ3NjJmYzhkMWYyODY2M2UzZmNkNzAiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.kyx3DrzFK9Ms0T15LHRGEi1jgFDtC-RIC7ql4m1OTT4";
        String testString = "";

        try{

            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.themoviedb.org/3/movie/" + testString + "/images"))
                .header("accept", "application/json")
                .header("Authorization", "Bearer " + TMDB_APITOKEN)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            testString = response.body();
            
         
        } catch(InterruptedException e){
           
        } catch(IOException e){
         
        }

        String emtpyMethodResult = DataBaseImageRouting.requestUnstructutredImageFilePaths("");

        assertEquals(testString, emtpyMethodResult);
    }

    
    @Test
    void testFindImageFilePaths() {

        String TMDB_APITOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4MDMxMzVlYWM0YWYxZmZjMzMwNGJiYmE2MjIyMTUwZCIsIm5iZiI6MTc0NjA4OTc4NC4yODMsInN1YiI6IjY4MTMzNzM4YjYzNzA2NTVmYjkwZmYxZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.R_OxsImltSnT9Tk9IJuwm53fwGpBZQ6eMNe9ldM8FfA";
        String testString = "11";
        List<String> testEmptyList = new ArrayList<>();

        try{

            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.themoviedb.org/3/movie/" + testString + "/images"))
                .header("accept", "application/json")
                .header("Authorization", "Bearer " + TMDB_APITOKEN)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            testString = response.body();
            
         
        } catch(InterruptedException e){
           
        } catch(IOException e){
           
        }

        List<String> filePaths = new ArrayList<>();

        Pattern pattern = Pattern.compile("\"file_path\":\"/(\\w+|\\.)+");
        Matcher matcher = pattern.matcher(testString);

        int count = 0;

        while (matcher.find() && count < 3){
            filePaths.add(matcher.group().substring(14).trim());
            count++;
        }

        List<String> methodResult = DataBaseImageRouting.findImageFilePaths(testString);
        List<String> emptyMethodResult = DataBaseImageRouting.findImageFilePaths("");

        assertEquals(testEmptyList, emptyMethodResult);
        assertEquals(methodResult, filePaths);
        

    }


    @Test
    void testGetImageFilePaths() {

        List<String> empty = new ArrayList<>();

        List<String> methodResult = DataBaseImageRouting.getImageFilePaths("");

        assertEquals(empty, methodResult);

    }
}

