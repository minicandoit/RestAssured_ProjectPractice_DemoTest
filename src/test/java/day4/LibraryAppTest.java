package day4;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Spartan;
import spartan_util.SpartanUtil;
import test_util.SpartanNoAuthBaseTest;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
@DisplayName("Library App Simple Test")
public class LibraryAppTest {


    @BeforeAll
    public static void init() {

        baseURI = "http://library1.cybertekschool.com";
        basePath = "/rest/v1";
    }

    @AfterAll
    public static void cleanup() {
        reset();
    }


    @DisplayName("test POST/login")
    @Test
    public void testLogin() {
//librarian69@library  ,KNPXrm3S
        given()
                .log().all()
                .contentType(ContentType.URLENC)
                .formParam("email", "librarian69@library")
                .formParam("password", "KNPXrm3S").
                when()
                .post("/login").
                then()
                .statusCode(200)
                .log().all()
                .body("token", is(not(blankOrNullString())))
        ;

    }

    @DisplayName("test the token")
    @Test
    public void testGetTokenDecodeToken() {
        //first send request to POST/login extract token
        //then send request to POST /decode to verify emails and other info
        String username = "librarian69@library";
        String password = "KNPXrm3S";

        String myToken = given()
                .log().all()
                .contentType(ContentType.URLENC)
                .formParam("email", "librarian69@library")
                .formParam("password", "KNPXrm3S").
                        when()
                .post("/login").
                        then()
                .log().all()
                .statusCode(200)
                .extract()
                .path("token");

        given()
                .log().all()
                .contentType(ContentType.URLENC)
                .formParam("token", myToken).
                when()
                .post("/decode").
                then()
                .log().all()
                .statusCode(200)
                .body("email", is(username))
        ;
    }


    @DisplayName("GET//dashboard_stats endpoint1")
    @Test
    public void testDashboardNumbers1() {

        given()
                .header("x-library-token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjp7ImlkIjoiMjA5MCIsImZ1bGxfbmFtZSI6IlRlc3QgTGlicmFyaWFuIDY5IiwiZW1haWwiOiJsaWJyYXJpYW42OUBsaWJyYXJ5IiwidXNlcl9ncm91cF9pZCI6IjIifSwiaWF0IjoxNjE3MTI4ODM1LCJleHAiOjE2MTk3MjA4MzV9.iAA-WqPrETnHX1MBYStKtO8cXZTBtKyxnw6n7ZDUrx0").
                when()
                .get("/dashboard_stats").
                then()
                .log().all()
                .statusCode(200)
//                .body("book_count" , is("2108")  )
//                .body("borrowed_books" , is("775")  )
//                .body("users" , is("8666")  )
        ;
    }

    @DisplayName("GET//dashboard_stats endpoint")
    @Test
    public void testDashboardNumbers() {
        String username = "librarian69@library";
        String password = "KNPXrm3S";

       String myToken = given()
                .log().all()
                .contentType(ContentType.URLENC)
                .formParam("email", username)
                .formParam("password", password).
                        when()
                .post("/login")
                .path("token");

       given()
               .log().all()
               .header("x-library-token",myToken).
               when()
               .get("/dashboard_stats").
       then()
                .log().all()
               .statusCode(200)
               .body("book_count" , is("2854")  )
               .body("borrowed_books" , is("782")  )
               .body("users" , is("8773")  )
               ;
        // alternatively , extract JsonPath object after status code check
        // assert each numbers separately
        JsonPath jp = given()
                .header("x-library-token", myToken).
                        when()
                .get("/dashboard_stats").
                        then()
                .statusCode(200).
                        extract()
                .jsonPath();

        assertThat(jp.getInt("book_count"),is(2854));
        assertThat(jp.getInt("borrowed_books"),is(782));
        assertThat(jp.getInt("users"),is(8773));

    }
}