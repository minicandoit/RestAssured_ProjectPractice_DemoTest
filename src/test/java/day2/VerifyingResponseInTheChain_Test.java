package day2;

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
public class VerifyingResponseInTheChain_Test extends SpartanNoAuthBaseTest {
    @DisplayName("Verifying the GET/ spartans/{id} response directly in the chain")
    @Test
    public void testOneSpartanInOneShot(){
        given()
                  .log().all()//this will log the request
                  .pathParam("id",16).
        when()
                  .get("/spartans/{id}").
        then()
                  .log().all()//this will log the response
                  .statusCode(200)
                  .header("Content-Type",is("application/json"))
                  .contentType("application/json")
                  .body("id",equalTo(16))
                  .body("name",is("Sinclair"))
                  .body("phone",equalTo(9714460354L))
       
        ;
    }
}
