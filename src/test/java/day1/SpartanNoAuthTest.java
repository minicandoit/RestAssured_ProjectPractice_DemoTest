package day1;

import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import test_util.SpartanNoAuthBaseTest;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.* ;
@DisplayName("Spartan App Get Requests")
public class SpartanNoAuthTest extends SpartanNoAuthBaseTest {
    //http://34.230.50.126:8000/api/hello
    //http://34.230.50.126:8000/api/spartans


    @Test
    public void sayHello(){

        //the actual request url you have sent is this
        //baseURI + basePath+"/hello"
        get("/hello").prettyPeek();
    }

    @Test
    public void getAllSpartans(){



        //the actual request url you have sent is this
        //baseURI + basePath+"/spartans"

        get("/spartans").prettyPeek();


    }



}


