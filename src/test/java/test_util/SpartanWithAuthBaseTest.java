package test_util;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.io.File;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.is;

public abstract class SpartanWithAuthBaseTest {
   public static RequestSpecification spartanSpec;
   public static ResponseSpecification spartanResponseSpec;
    @BeforeAll
    public static void init(){

        RestAssured.baseURI=ConfigurationReader.getProperty("AuthBaseURI");
        RestAssured.basePath=ConfigurationReader.getProperty("AuthBasePath");

        spartanSpec=given()
                .auth().basic("admin","admin")
                .contentType(ContentType.JSON);

        spartanResponseSpec=expect()
                .logDetail(LogDetail.ALL)
                .statusCode(201)
                .contentType(ContentType.JSON)
                 .body(matchesJsonSchemaInClasspath("spartanPostJsonSchema.json"))
                .body(matchesJsonSchema(new File("src/test/java/day11/spartanPostJsonSchema.json")))
                .body("success",is("A Spartan is Born!"))


        ;

    }
    @AfterAll
    public static void cleanUp(){
        RestAssured.reset();
    }



}
