package day4;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Spartan;
import spartan_util.SpartanUtil;
import test_util.SpartanNoAuthBaseTest;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpartanRandomPOST_Test extends SpartanNoAuthBaseTest {

    @DisplayName("/POST/spartans with random Data")
    @Test
    public void addOneRandomSpartanTest() {
//this is the map object we sent as body, it's expected result
        Map<String, Object> randomRequestBodyMap = SpartanUtil.getRandomSpartanMap();

        given()
                .log().body()
                .contentType(ContentType.JSON)
                .body(randomRequestBodyMap).
                when()
                .post("/spartans").
                then()
                .log().all()
                .statusCode(201)
                .body("data.name", is(randomRequestBodyMap.get("name")))
                .body("data.gender", is(randomRequestBodyMap.get("gender")))
                .body("data.phone", is(randomRequestBodyMap.get("phone")))
        ;
    }

    @DisplayName("/POST/spartans with random Spartan POJO")
    @Test
    public void addOneRandomSpartanPOJOTest() {
        Spartan randomPOJO = SpartanUtil.getRandomSpartanPOJO();

        given()
                .log().body()
                .contentType(ContentType.JSON)
                .body(randomPOJO).
                when()
                .post("/spartans").
                then()
                .log().all()
                .body("data.name", is(randomPOJO.getName()))
                .body("data.gender", is(randomPOJO.getGender()))
                .body("data.phone", is(randomPOJO.getPhone()))
                .body("success",is("A Spartan is Born!"))
        ;


    }


    @DisplayName("POST/spartans and send GET /spartans/{id} to verify ")
    @Test
    public void testAddOneDataThenGetOneDataTOVerify() {
        //send post request ,save the id that generated
        //check status code is 201
        //send get request using the id you saved from above
        //check status code is 200 and body match for exactly what you send

        Spartan randomPOJO=SpartanUtil.getRandomSpartanPOJO();
        Response response = given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(randomPOJO).
                        when()
                .post("/spartans").prettyPeek();

        int newId = response.path("data.id");
        System.out.println("newId = " + newId);
        //  int newId=response.jsonPath().getInt("data.id");
assertThat(response.statusCode(),is(201));


      given()
              .log().all()
              .pathParam("id",newId)
              .when()
              .get("/spartans/{id}").
              then()
              .log().body()
              .statusCode(200)
              .body("id",is(newId))
              .body("name",is(randomPOJO.getName()))
              .body("gender",is(randomPOJO.getGender()))
              .body("phone",is(randomPOJO.getPhone()))
              ;

    }

    @DisplayName("POST/spartans and send GET /spartans/{id} to verify 2")
    @Test
    public void testAddOneDataThenGetOneDataTOVerifyInTheChain() {

        //send post request verify 201 and then extract the id in the same method chain
Spartan randomPOJO=SpartanUtil.getRandomSpartanPOJO();
        int newId = given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(randomPOJO).
                        when()
                .post("/spartans").
                        then()
                .log().body()
                .statusCode(201)
                .extract()
                //  .path("data.id");
                .jsonPath().getInt("data.id");
        System.out.println("newId = " + newId);
    }

    @DisplayName("POST/spartans and send GET /spartans/{id} to verify 3")
    @Test
    public void testAddOneDataThenExtractHeader() {

        //check status code 201 and extract Location header
        Spartan randomPOJO = SpartanUtil.getRandomSpartanPOJO();

        String locationHeader = given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(randomPOJO).
                        when()
                .post("/spartans").
                        then()
                .log().body()
                .statusCode(201)
                .extract()
                .header("Location");
        System.out.println("locationHeader = " + locationHeader);

    }
    }