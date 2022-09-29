package day11;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Spartan;
import spartan_util.SpartanUtil;
import test_util.SpartanNoAuthBaseTest;
import test_util.SpartanWithAuthBaseTest;

import java.io.File;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.Matchers.is;

public class SpartanPostSchemaTest extends SpartanWithAuthBaseTest {
    @DisplayName("Test Json Schema for Post Response")
    @Test
    public void testAdd1SpartanResponse(){

        Spartan bodyPOJO= SpartanUtil.getRandomSpartanPOJO();
        System.out.println("bodyPOJO = " + bodyPOJO);
        spartanResponseSpec=expect()
                  .body("data.name",is(bodyPOJO.getName()))
                .body("data.gender",is(bodyPOJO.getGender()))
                .body("data.phone",is(bodyPOJO.getPhone()));


        given()
                .spec(spartanSpec)
//             .auth().basic("admin","admin")
//             .contentType(ContentType.JSON)
              .body(bodyPOJO).
      when()
             .post("/spartans").
      then()
        .spec(spartanResponseSpec)



//             .statusCode(201)
//              .log().all()
//             .body(matchesJsonSchemaInClasspath("spartanPostJsonSchema.json"))
//             .body(matchesJsonSchema(new File("src/test/java/day11/spartanPostJsonSchema.json")))
        ;
        // if the schema file is in different location (anywhere other than resources folder)
        // then we use matchesJsonSchema ( new File("Your full path goes here"))

        // as a homework
        // try to store the request spec into reusable variable
        // try to add the body validation into response spec

    }


}
