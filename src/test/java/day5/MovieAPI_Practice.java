package day5;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.* ;
public class MovieAPI_Practice {

    @DisplayName("Search movives JsonPath paractice")
    @Test
    public void testSearch() {

        //GET http://www.omdbapi.com/?s=Superman&type=series&Your_API_KEY_GOES_HERE
        //if you do not have other place you are using it,
        // you directly provide your bseURI using bse URI method in given
        JsonPath jp =
                given()
         .baseUri("http://www.omdbapi.com")
        .log().all()
        .queryParam("apikey","33e98cda")
        .queryParam("s","Superman")
        .queryParam("type").
       when()
                .get()
                .then()
                .log().all()
                .statusCode(200)
                //CONTINUE from here save Titles as list
                .extract()
                .jsonPath();

        //json path to get all movie title is Search.Title
        List<String> allTitles=jp.getList("Search.Title",String.class);
        System.out.println("allTitles = " + allTitles);

    }



    @DisplayName("Search movies response body validation in the chain")
    @Test
    public void testSearch2() {

        //GET http://www.omdbapi.com/?s=Superman&type=series&Your_API_KEY_GOES_HERE
        //if you do not have other place you are using it,
        // you directly provide your bseURI using bse URI method in given


        given()
                .baseUri("http://www.omdbapi.com")
                .log().all()
                .queryParam("apikey", "33e98cda")
                .queryParam("s", "Superman")
                .queryParam("type", "series").
                when()
                .get()
                .then()
                .log().all()
                .statusCode(200)
                .body("Search.Title",hasSize(10))
                .body("Search[0].Title",containsString("Superman"))
                .body("Search.Title",hasItem("Superman and Lois"))
                .body("Search.Title",hasItems("Superman and Lois","The New Adventures of Superman"))
                .body("Search.Title",everyItem(containsString("Superman")))
                ;

    }

}
