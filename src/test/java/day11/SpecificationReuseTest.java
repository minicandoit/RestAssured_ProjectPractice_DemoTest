package day11;

import test_util.SpartanWithAuthBaseTest;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import test_util.SpartanWithAuthBaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpecificationReuseTest extends SpartanWithAuthBaseTest {

    //all test in this test class will use admin credentials
    //all test in this class will expect json result from response
    //all test in this class response status code expected to be 200
    //all test in this class response content type header is json


    private static RequestSpecification requestSpec=  given().log().all()
               .auth().basic("admin","admin")
               .contentType(ContentType.JSON);;
   private static  ResponseSpecification responseSpec=expect().logDetail(LogDetail.ALL)
           .statusCode(200)
           .contentType(ContentType.JSON);
    ;

    @DisplayName("Admin GET / spartans and  expect 200 amd json")
    @Test
    public void testAdminGetAll(){





    given()
            .spec(requestSpec).

            when()
                .get("/spartans").
      then()
                .spec(responseSpec);


//        given()
//                .auth().basic("admin","admin")
//                .contentType(ContentType.JSON).
//       when()
//                .get("/spartans").
//       then()
//                .statusCode(200)
//                .contentType(ContentType.JSON);

    }

    @DisplayName("Admin GET /spartans/{id} and expect 200 and json , expect id match ")
    @Test
    public void testAdminGetOne(){

        given()

               .spec(requestSpec)
                 .pathParam("id",1).
        when()
                .get("/spartans/{id}").
        then()
             //   .log().body()
               .spec(responseSpec)
                .body("id",is(1))
        ;




    }



    }
