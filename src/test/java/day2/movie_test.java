package day2;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import test_util.SpartanNoAuthBaseTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;


import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.* ;

public class movie_test {
    @DisplayName("movie test_search movie")
    @Test
    public void movieSearch() {
      //  GET https://www.ombdapi.com/?t=Superman&apiKey=YOUR KEY GOES HERE
//JsonPath jp=

        given()

                .log().all()
                .queryParam("apikey","33e98cda")
                .queryParam("t","Superman").
        when()
               .get("http://www.omdbapi.com")
                .prettyPeek()
                .jsonPath();



       JsonPath jp = get("http://www.omdbapi.com/?t=Superman&apikey=33e98cda").jsonPath();
        System.out.println("jp.getString(\"Title\") = " + jp.getString("Title"));
        System.out.println("jp.getInt(\"Year\") = " + jp.getInt("Year"));
        System.out.println("jp.getDouble(\"imdbRating\") = " + jp.getDouble("imdbRating"));

        System.out.println("jp.getString(\"Ratings[1].Source\") = " + jp.getString("Ratings[1].Source"));
        System.out.println("jp.getString(\"Ratings[0].Value\") = " + jp.getString("Ratings[0].Value"));

        JsonPath jp1 = get("http://www.omdbapi.com/?s=flash&type=series&apikey=33e98cda").jsonPath();


        System.out.println("jp1.getString(\"\") = " + jp1.getString(""));

        Map<String,Object> ThirdJsonInMap=
                jp1.getMap("Search[2]");
        System.out.println("ThirdJsonInMap = " + ThirdJsonInMap);
        System.out.println("jp1.getString(\"Search[2].Title\") = " + jp1.getString("Search[2].Title"));
        System.out.println("jp1.getString(\"Search[2].Year\") = " + jp1.getString("Search[2].Year"));
        System.out.println("jp1.getString(\"Search[2].imdbID\") = " + jp1.getString("Search[2].imdbID"));

        List<String> allImdbId=jp1.getList("Search.imdbID",String.class);
        System.out.println("allImdbId = " + allImdbId);

        System.out.println("jp1.getString(\"totalResults\") = " + jp1.getString("totalResults"));

        List<String> allMovieTitle=jp1.getList("Search.Title",String.class);
        System.out.println("allMovieTitle = " + allMovieTitle);

    }


    }
