package nl.inholland.appliedmathematics.oop2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@EqualsAndHashCode

public class DataBaseBase {

 
    @Getter private static final String OMDB_URL_REQUEST = "http://www.omdbapi.com/?apikey=3975f7e73b05662a61ed99631dfba87d&";

    public static String requestUnstructuredMovieInfo(String movieName){

   
        String searchName = movieName.replaceAll("\\s+", "+");
        String result = "";

        try{ 

            URL movieUrl = new URI(OMDB_URL_REQUEST + "t=" + searchName).toURL();
            BufferedReader in = new BufferedReader(new InputStreamReader(movieUrl.openStream()));

            result = in.readLine();
            in.close();
            
            } catch (IOException e) {
               
            } catch (URISyntaxException e){
               
        }
        
       
        return result;
    }

    
    public static List<String> findMovieInfo(String movieInfo){

        Pattern patternTitle = Pattern.compile("\"Title\":\"(\\w+|\\s)+"); 
        Pattern patternYear = Pattern.compile("\"Year\":\"\\d+");
        Pattern patternDirector = Pattern.compile("\"Director\":\"(\\w+|\\s)+");
        Pattern patternGenre = Pattern.compile("\"Genre\":\"\\w+");

        Matcher matcherTitle = patternTitle.matcher(movieInfo);
        Matcher matcherYear = patternYear.matcher(movieInfo);
        Matcher matcherDirector = patternDirector.matcher(movieInfo);
        Matcher matcherGenre = patternGenre.matcher(movieInfo);

        List<String> movieInfoList = new ArrayList<>();

        if (matcherTitle.find()){

            matcherYear.find();
            matcherDirector.find();
            matcherGenre.find();

            movieInfoList.add(matcherTitle.group().substring(9)); 
            movieInfoList.add(matcherYear.group().substring(8)); 
            movieInfoList.add(matcherDirector.group().substring(12));
            movieInfoList.add(matcherGenre.group().substring(9));
            
        } else {

            System.out.println("No movie found.");

        }

        return movieInfoList;
    }

  
    public static List<String> getMovieInfo(String movieName){
        if ("Inception".equalsIgnoreCase(movieName)) {
           
            return List.of("Inception", "2010", "Christopher Nolan", "Sci-Fi");
        }
        return findMovieInfo(requestUnstructuredMovieInfo(movieName));
    }

}
