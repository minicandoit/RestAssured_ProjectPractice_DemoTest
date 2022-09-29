package Practice;

import org.junit.jupiter.api.Test;
import test_util.SpartanNoAuthBaseTest;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.BeforeAll;
import test_util.SpartanNoAuthBaseTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import test_util.SpartanNoAuthBaseTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.* ;
public class SpartanTEstWithPathMethod extends SpartanNoAuthBaseTest {

    @Test
    public void test1(){
     Response response= given().accept(ContentType.JSON)
                .pathParam("id",10)
                .when().get("/spartans/{id}")
             .prettyPeek();

        assertThat(response.contentType(),equalTo("application/json"));
        assertThat(response.statusCode(),is(200));
        assertThat(response.path("id"),is(10));
        assertThat(response.path("name"),is("Lorenza"));
    }
    @Test
    public void test2(){
        JsonPath jp=given().accept(ContentType.JSON)
             //   .pathParam("id",10)
                .queryParam("nameContains","S")
                .queryParam("gender","Female")
                .when().get("/spartans/search")
                .prettyPeek()
                .jsonPath();

    //    assertThat(js.getInt("id"),is(10));

        List<String> allID=jp.getList("content.id");
        System.out.println("allID = " + allID);
        System.out.println("jp.getString(\"content[0].name\") = " + jp.getString("content[0].name"));
        Map<String,Object> secondMap=jp.getMap("content[1]");
        System.out.println("secondMap = " + secondMap);

        System.out.println("jp.getString(\"\") = " + jp.getString(""));


    }
}
