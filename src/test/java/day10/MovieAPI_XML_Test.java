package day10;

import io.restassured.path.xml.XmlPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class MovieAPI_XML_Test {
    /*
    SEND request to GET http://www.omdbapi.com/?t=Superman&r=xml&apikey=YOUR KEY GOES HERE
    get the movie attribute out from the xml response
    Above is for getting one movie information
    Now Send Separate request to http://www.omdbapi.com/?s=Superman&type=series&r=xml&apikey=YOUR KEY GOES HERE
    and get all movie titles from this response into the list
     */
    @DisplayName("Get movie attributes in xml")
    @Test
    public void testAttributes(){

        XmlPath xp1=given()
                .baseUri("http://www.omdbapi.com")
                .queryParam("apikey","33e98cda")
                .queryParam("t","Superman")
                .queryParam("r","xml").
                        when()
                .get()
                .xmlPath();

        String title= xp1.getString("root.movie.@title");
        System.out.println("title = " + title);



        XmlPath xp2=given()
                .baseUri("http://www.omdbapi.com")
                .queryParam("apikey","33e98cda")
                .queryParam("type","series")
                .queryParam("s","Superman")
                .queryParam("r","xml").
                        when()
                .get()
                .xmlPath();

        List<String>allMovieTitles= xp2.getList("root.result.@title");
        System.out.println("allMovieTitles = " + allMovieTitles);



    }


}
