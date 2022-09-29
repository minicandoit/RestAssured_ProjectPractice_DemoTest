package day2;

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
@DisplayName("Spartan Test with path variable and query param")
public class SpartanTest_PathVariable extends SpartanNoAuthBaseTest {

@Test
    public void getOneSpartan(){
    //get("/spartans/16").prettyPeek();

    //16 as path variable/parameter
    //accept header

    //returns response object

  Response r1=

                    given()
                              .pathParam("spartan_id",16)
                              .header("Accept","application/json").

                when()
                              .get("/spartans/{spartan_id}")
                              .prettyPeek();

  Response r2=
                given()
                          .accept("application/json").
                when()
                          .get("/spartans/{spartan_id}",16)
                        .prettyPeek();
}


@DisplayName("logging the request")
    @Test
    public void getOneSpartanWithLog(){
    Response response=
            given()
                    .log().all()
                    .accept("application/json")
                     .pathParam("id",16).

             when()
                     .get("/spartans/{id}")
            .prettyPeek()
            ;

    assertThat(response.statusCode(), equalTo(200));
    assertThat(response.contentType(),equalTo("application/json"));
    assertThat(response.path("name"),is("Sinclair"));




}









}
