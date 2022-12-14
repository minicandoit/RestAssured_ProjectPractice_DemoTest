package day10;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import test_util.SpartanNoAuthBaseTest;

import java.io.File;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
public class jsonSchemaValidationTest extends SpartanNoAuthBaseTest {


    @DisplayName("Check GET /spartans/{id} json schema")
    @Test
    public void test1SpartanJsonSchema(){

        given()
                .pathParam("id",33).
       when()
                .get("/spartans/{id}").
        then()
                .log().body()
                .statusCode(200)
                //check the response body against the schema file
                // we added under resources folder===>copy path source root
                .body(matchesJsonSchemaInClasspath("singleSpartanSchema.json"))
                ;
    }



    @DisplayName("Check GET /spartans json schema")
    @Test
    public void testAllSpartansJsonSchema(){

        when()
                .get("/spartans").
                then()
                .body(matchesJsonSchemaInClasspath("allSpartansSchema.json")  )
        //what if this schema file was under day 10 package
        .body(matchesJsonSchema(new File("src/test/java/day10/allSpartansSchema.json")))
              ;






    }





}
