package Practice;

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
import java.util.Map;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.* ;
public class withquery {

 @BeforeAll
 public static void setUp(){
     baseURI="http://34.230.50.126:8000";
     basePath="/api";
 }

 @Test
    public void QueryParm1(){
     Response response = given()
             .accept(ContentType.JSON)
             .and().queryParam("gender", "Female")
             .queryParam("nameContains", "S")
             .when()
             .get(baseURI + basePath + "/spartans/search");
     response.prettyPrint()
     ;

  assertThat(response.statusCode(),equalTo(200));
   assertThat(response.contentType(),equalTo("application/json"));

   // assertThat(response.path("gender"),);
//    assertThat(response.path("name"),equalTo("SophiaB"));

 }
 @Test
    public void test2(){
     Map<String, Object>paramsMap=new HashMap<>();
     paramsMap.put("gender","Female");
     paramsMap.put("nameContains","S");

   Response response=given().accept(ContentType.JSON)
             .and().queryParam(String.valueOf(paramsMap))
             .when().get(baseURI + basePath + "/spartans/search")
     ;

     assertThat(response.statusCode(),equalTo(200));
     assertThat(response.contentType(),equalTo("application/json"));


 }









}
