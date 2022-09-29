package day1;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
@DisplayName("Intro to RestAssured")
public class RestAssured_Intro {
    @DisplayName("Testing hello endpoint")
    @Test
    public void testHelloEndPoint() {
//Get http://3.92.203.41:8000/api/hello
        //save the response into a object with type Response
        Response response = get("http://3.92.203.41:8000/api/hello");
        //extracting information from Response object
        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("response.contentType() = " + response.contentType());
        System.out.println("response.getHeader(\"Content-Type\") = " + response.getHeader("Content-Type"));
        System.out.println("response.asString() = " + response.asString());

    }
}