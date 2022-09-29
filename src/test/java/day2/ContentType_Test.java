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
@DisplayName("Spartan ContentType Summary")
public class ContentType_Test extends SpartanNoAuthBaseTest{
    @DisplayName("GET/hello")
    @Test
    public void testHelloContentType(){
        //anything to given()
      when()
              .get("/hello").

      then()
              .contentType(ContentType.TEXT)
              .body(is("Hello from Sparta"))
              ;

    }
    @DisplayName("GET/spartans in json")
    @Test
    public void testSpartansContentType(){

        given()
                .accept(ContentType.JSON).
        when()
                .get("/spartans").
        then()
                .contentType(ContentType.JSON)
                ;
    }
    @DisplayName("GET/spartans in Xml")
    @Test
    public void testSpartansXmlType(){

        given()
                .accept(ContentType.XML).

        when()
                .get("/spartans").
        then()
                .contentType(ContentType.XML)
                ;


    }
}
