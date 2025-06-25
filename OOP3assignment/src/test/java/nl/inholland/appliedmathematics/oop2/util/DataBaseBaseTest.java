package nl.inholland.appliedmathematics.oop2.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class DataBaseBaseTest {
    
 @Test
    void testRequestUnstructuredMovieInfo() {
        
        String test = "";

        try{ 

            URL movieUrl = new URI("http://www.omdbapi.com/?apikey=4e034029&" + "t=" + "Blade+Runner+2049").toURL();
            BufferedReader in = new BufferedReader(new InputStreamReader(movieUrl.openStream()));

            test = in.readLine();
            in.close();
            
            } catch (IOException e) {
               
            } catch (URISyntaxException e){
                
        }
        
        String methodResult = DataBaseBase.requestUnstructuredMovieInfo("Blade Runner 2049");

        assertEquals(test, methodResult);

    }


    @Test
    void testFindMovieInfo() {

        List<String> testDataList = new ArrayList<>();
        testDataList.add("Blade Runner 2049");
        testDataList.add("2017");
        testDataList.add("Denis Villeneuve");
        testDataList.add("Action");

        String testDataString = "";

        try{ 

            URL movieUrl = new URI("http://www.omdbapi.com/?apikey=3975f7e73b05662a61ed99631dfba87d&&" + "t=" + "Blade+Runner+2049").toURL();
            BufferedReader in = new BufferedReader(new InputStreamReader(movieUrl.openStream()));

            testDataString = in.readLine();
            in.close();
            
            } catch (IOException e) {
               
            } catch (URISyntaxException e){
          
        }

        List<String> methodResult = DataBaseBase.findMovieInfo(testDataString);

        assertEquals(testDataList, methodResult);

    }


    @Test
    void testGetMovieInfo() {
        List<String> test = new ArrayList<>();

        List<String> methodResult = DataBaseBase.getMovieInfo("");

        assertEquals(test, methodResult);

    }
}

