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

import org.springframework.stereotype.Service;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Service
public class DataBaseSimilar {

    @Getter private static final String TMDB_APITOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzOTc1ZjdlNzNiMDU2NjJhNjFlZDk5NjMxZGZiYTg3ZCIsIm5iZiI6MTc0OTUwODg2MC4yNDg5OTk4LCJzdWIiOiI2ODQ3NjJmYzhkMWYyODY2M2UzZmNkNzAiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.kyx3DrzFK9Ms0T15LHRGEi1jgFDtC-RIC7ql4m1OTT4";

    public static String requestUnstructuredMovieID(String movieName){
    
        String searchMovieName = movieName.replaceAll("\\s+", "%20");
        String result = "";
        
        try{

            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.themoviedb.org/3/search/movie?query=" + searchMovieName + "&include_adult=false&language=en-US&page=1"))
                .header("accept", "application/json")
                .header("Authorization", "Bearer " + TMDB_APITOKEN)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            result = response.body();

        } catch(InterruptedException e){
          
        } catch(IOException e){
           
        }

        return result;
    }


    public static String findMovieID(String movieInfo){

        String result = "";

        Pattern pattern = Pattern.compile("\"id\":\\d+");
        Matcher matcher = pattern.matcher(movieInfo);

        if (matcher.find()){
            return matcher.group().substring(5);
        } else {
            System.out.println("No movie found.");
        }

        return result;
    }


    public static String requestUnstructuredSimilarMovies(String movieID){

        String result = "";

        try{

            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.themoviedb.org/3/movie/" + movieID + "/similar?language=en-US&page=1"))
                .header("accept", "application/json")
                .header("Authorization", "Bearer " + TMDB_APITOKEN)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            result = response.body();

        } catch(InterruptedException e){
            
        } catch(IOException e){
            
        }

        return result;
    }


    public static List<String> findSimilarMovies(String responseBody){

        Pattern pattern = Pattern.compile("\"original_title\"\\:\"(\\w+|\\s|&)+");
        Matcher matcher = pattern.matcher(responseBody);

        List<String> similarMovieList = new ArrayList<>();

        int count = 0;

        while (matcher.find() && count < 3){
            similarMovieList.add(matcher.group().substring(18).trim());
            count++;
        }

        return similarMovieList;
    }

    public static List<String> getSimilarMovies(String movieName){

        return findSimilarMovies(requestUnstructuredSimilarMovies(findMovieID(requestUnstructuredMovieID(movieName))));
    }

}
