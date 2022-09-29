package day2;
import io.restassured.http.ContentType;
import org.omg.CORBA.PUBLIC_MEMBER;
import test_util.SpartanNoAuthBaseTest;
import test_util.SpartanNoAuthBaseTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import test_util.SpartanNoAuthBaseTest;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.* ;
public class BreakingBad_Test {
   //https://www.breakingbadapi.com/api/characters?name=Walter

    @BeforeAll
    public static void init(){
        baseURI= "https://www.breakingbadapi.com" ;
        basePath="/api";

    }
    @AfterAll
    public static void cleanup(){
        reset();
    }
    @DisplayName("GET/ characters with name query param")
    @Test
    public void testFilterCharacterName(){
        given()
                .log().uri()
                .queryParam("name","Walter").
        when()
                .get("/characters").
        then()
                .log().all()
                .statusCode(200)
                .header("Content-Type","application/json; charset=utf-8")
                .contentType("application/json; charset=utf-8")
                ;
    }
    @DisplayName("GET/characters{char_id}")
    @Test
    public void    test1Character(){
        given()
               // .log().all()
                .pathParam("char_id",1)
                .log().uri().
        when()
                .get("/characters/{char_id}").
        then()
                .log().all()
                .statusCode(200)
                .header("Content-Type","application/json; charset=utf-8")
                .contentType("application/json; charset=utf-8")
                ;
    }
    //  /episodes/60
    @DisplayName("GET/episodes{episode_id}")
    @Test
    public void    test1Episode() {
      given()

             .pathParam("episode_id",60)
              .log().all().
      when()
              .get("/episodes/{episode_id}").
      then()
              .log().all()
              .statusCode(200)
              .contentType(ContentType.JSON)

              ;






    }

}
