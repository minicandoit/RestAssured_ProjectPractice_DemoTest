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
public class SpartanTest_QueryParam extends SpartanNoAuthBaseTest{

    @DisplayName("Test GET/spartans/search endpoint")
    @Test
    public void testSearch(){
        Response response=
                given()
                        .log().all()
                       .queryParam("nameContains","Sinclair")
                      .queryParam("gender","Male").
                when()
                      .get("/spartans/search")
                      .prettyPeek();

        //print the total element field value from the response

        System.out.println("response.path(\"totalElement\") = " + response.path("totalElement"));


    }

}
