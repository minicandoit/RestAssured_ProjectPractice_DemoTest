package day9;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Spartan;
import test_util.SpartanNoAuthBaseTest;
import test_util.SpartanWithAuthBaseTest;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;

public class SpartanWithAuthTest extends SpartanWithAuthBaseTest {

@DisplayName("GET/spartans as public user expect 401")
    @Test
    public void testPublicUser(){
when()
        .get("/spartans").
then()
        .statusCode(401)
        .log().all();



}



    @DisplayName("GET/spartans as admin expect 200")
    @Test
    public void testAdmin(){

given()
        .auth()
        .basic("admin","admin").
when()
        .get("/spartans").
then()
        .statusCode(200)
        ;
    }

    @DisplayName("DELETE/spartans/{id} as editor expect 403")
    @Test
    public void testEditor(){

given()
        .pathParam("id",100)
        .auth()
        .basic("editor","editor").
when()
        .delete("/spartans/{id}").
then()
        .log().all()
        .statusCode(403);
    }

    // As a homework ,write a detailed test for Role base access control (RBAC)
    /*
    in Spartan App with auth
     Admin should be able to take all CRUD
     Editor should be able to take all CRUD
        other than Delete
     User Should be able to only Read Data
        Should not be able to add , update , delete
     +

     */

    @DisplayName("GET/spartans as public user expect 401")
    @Test
    public void testPublicUser1(){
        Spartan sp = new Spartan("Abigale", "Female", 1800233232L);
        given()
                .pathParam("id",100)
                .auth()
                .basic("user","user").
        when()
                .delete("/spartans/{id}").
        then()
                 .log().all()
                .statusCode(403);

        given()
                .body(sp)
                .contentType(ContentType.JSON)
                .auth()
                .basic("user","user").
                when()
                .post("/spartans").
                then()
                .log().all()
                .statusCode(403);

    }


    @DisplayName("GET/spartans as public user expect 401")
    @Test
    public void testPublicUser2(){


        String update="{\"phone\":1234577777}";
        given()
                .auth().basic("editor","editor")
                .body(update)
                .pathParam("id",33)
                .contentType(ContentType.JSON).
                when()
                .patch("/spartans/{id}")
                .prettyPeek().
                then()
                .statusCode(204)
        ;
        given()
                .contentType(ContentType.JSON)
                .pathParam("id",33)
                .auth().basic("user","user").
        when()
               .get("/spartans/{id}").
               prettyPeek().
        then()
               .statusCode(200)
        .body("id",is(33))
        .body("name",is("Wilek"))
               ;






    }


}
