package day2;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import test_util.SpartanNoAuthBaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import test_util.SpartanNoAuthBaseTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;

public class StarWars_test {
    @Test
    public void test(){
        //https://swapi.dev/api
      given()
              .log().all()
             .accept(ContentType.JSON)
              .pathParam("id",1).
      when()
              .get("https://swapi.dev/api/people/{id}").
      then()
              .log().all()
              .contentType(ContentType.JSON)
              .statusCode(200)


                   ;

    }
}
