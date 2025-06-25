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
public class DataBaseImageRouting {

    @Getter private static final String TMDB_APITOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzOTc1ZjdlNzNiMDU2NjJhNjFlZDk5NjMxZGZiYTg3ZCIsIm5iZiI6MTc0OTUwODg2MC4yNDg5OTk4LCJzdWIiOiI2ODQ3NjJmYzhkMWYyODY2M2UzZmNkNzAiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.kyx3DrzFK9Ms0T15LHRGEi1jgFDtC-RIC7ql4m1OTT4";

    public static String requestUnstructutredImageFilePaths(String movieID){

        String unstructuredFilePaths = "";

        try{

            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.themoviedb.org/3/movie/" + movieID + "/images"))
                .header("accept", "application/json")
                .header("Authorization", "Bearer " + TMDB_APITOKEN)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            unstructuredFilePaths = response.body();
            
         
        } catch(InterruptedException e){
            
        } catch(IOException e){
            
        }

        return unstructuredFilePaths;
    }


    public static List<String> findImageFilePaths(String responseBody){

        List<String> filePaths = new ArrayList<>();

        Pattern pattern = Pattern.compile("\"file_path\":\"/(\\w+|\\.)+");
        Matcher matcher = pattern.matcher(responseBody);

        int count = 0;

        while (matcher.find() && count < 3){
            filePaths.add(matcher.group().substring(14).trim());
        }

        return filePaths;
    }

    public static List<String> getImageFilePaths(String movieName){

        return findImageFilePaths(requestUnstructutredImageFilePaths(DataBaseSimilar.findMovieID(DataBaseSimilar.requestUnstructuredMovieID(movieName))));
    }
}
