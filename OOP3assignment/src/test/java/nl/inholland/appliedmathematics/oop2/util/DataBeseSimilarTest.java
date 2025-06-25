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

public class DataBeseSimilarTest {
    
 @Test
    void testRequestUnstructuredMovieID() {

        String TMDB_APITOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzOTc1ZjdlNzNiMDU2NjJhNjFlZDk5NjMxZGZiYTg3ZCIsIm5iZiI6MTc0OTUwODg2MC4yNDg5OTk4LCJzdWIiOiI2ODQ3NjJmYzhkMWYyODY2M2UzZmNkNzAiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.kyx3DrzFK9Ms0T15LHRGEi1jgFDtC-RIC7ql4m1OTT4";
        String test = "";

        String testName = "Star%20Wars";
        
        try{

            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.themoviedb.org/3/search/movie?query=" + testName + "&include_adult=false&language=en-US&page=1"))
                .header("accept", "application/json")
                .header("Authorization", "Bearer " + TMDB_APITOKEN)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            test = response.body();

        } catch(InterruptedException e){
           
        } catch(IOException e){
           
        }
        
        String methodResult = DataBaseSimilar.requestUnstructuredMovieID("Star Wars");

        assertEquals(test, methodResult);
        
    }


    @Test
    void testFindMovieID() {

        String TMDB_APITOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4MDMxMzVlYWM0YWYxZmZjMzMwNGJiYmE2MjIyMTUwZCIsIm5iZiI6MTc0NjA4OTc4NC4yODMsInN1YiI6IjY4MTMzNzM4YjYzNzA2NTVmYjkwZmYxZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.R_OxsImltSnT9Tk9IJuwm53fwGpBZQ6eMNe9ldM8FfA";
        String testString = "";
        
        try{

            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.themoviedb.org/3/search/movie?query=" + "Star%20Wars" + "&include_adult=false&language=en-US&page=1"))
                .header("accept", "application/json")
                .header("Authorization", "Bearer " + TMDB_APITOKEN)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            testString = response.body();

        } catch(InterruptedException e){
            
        } catch(IOException e){
           
        }

        Pattern pattern = Pattern.compile("\"id\":\\d+");
        Matcher matcher = pattern.matcher(testString);

        String testBody = "";

        if (matcher.find()){
            testBody =  matcher.group().substring(5);
        } else {
            System.out.println("No movie found.");
        }

        String methodResult = DataBaseSimilar.findMovieID(testString);
        String emptyMethodResult = DataBaseSimilar.findMovieID("methodResult");

        assertEquals(testBody, methodResult);
        assertEquals("", emptyMethodResult);

    }


        @Test
    void testRequestUnstructuredSimilarMovies() {

        String TMDB_APITOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4MDMxMzVlYWM0YWYxZmZjMzMwNGJiYmE2MjIyMTUwZCIsIm5iZiI6MTc0NjA4OTc4NC4yODMsInN1YiI6IjY4MTMzNzM4YjYzNzA2NTVmYjkwZmYxZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.R_OxsImltSnT9Tk9IJuwm53fwGpBZQ6eMNe9ldM8FfA";
        String testId = DataBaseSimilar.findMovieID(DataBaseSimilar.requestUnstructuredMovieID("StarWars"));

        String testString = "";

        try{

            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.themoviedb.org/3/movie/" + testId + "/similar?language=en-US&page=1"))
                .header("accept", "application/json")
                .header("Authorization", "Bearer " + TMDB_APITOKEN)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            testString = response.body();

        } catch(InterruptedException e){
            
        } catch(IOException e){
           
        }

        String methodResult = DataBaseSimilar.requestUnstructuredSimilarMovies(testId);

        assertEquals(testString, methodResult);

    }


    @Test
    void testFindSimilarMovies() {

        List<String> emptyTestBody = new ArrayList<>();

        List<String> methodResult = DataBaseSimilar.findSimilarMovies("");

        assertEquals(emptyTestBody, methodResult);


    }

    @Test
    void testGetSimilarMovies() {

        List<String> emptyTestList = new ArrayList<>();
        emptyTestList.add("The Fountain");
        emptyTestList.add("Superman Returns");
        emptyTestList.add("Thelma & Louise");

        List<String> methodResult = DataBaseSimilar.getSimilarMovies("Star Wars");

        assertEquals(emptyTestList, methodResult);

    }
}

