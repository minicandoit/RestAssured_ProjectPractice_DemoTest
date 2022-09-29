package day11;

import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sun.reflect.generics.tree.ReturnType;
import test_util.LibraryAppBaseTest;

import static io.restassured.RestAssured.*;

public class ReusableSpecLibraryAppTest extends LibraryAppBaseTest {

    @DisplayName("GET/dashboard_stats")
    @Test
    public void testDashboardStats(){




       given()
               .spec(librarianSpec).
        when()
               .get("/dashboard_stats")
       .then()
//
       .spec(libraryResponseSpec)
               ;
    }


    @DisplayName("GET /get_all_users")
    @Test
    public void testGetAllUser(){

        given()
                .spec(librarianSpec).
        when()
               .get("/get_all_users").
        then()
              .spec(libraryResponseSpec)
        //check status code using reusable response specification method we build

     //   .spec(getDynamicResponseSpec(200))

//                .statusCode(200)
//                .contentType(ContentType.JSON)
//                .log().all()
               ;




    }

    public static ResponseSpecification getDynamicResponseSpec(int statusCode){

        return expect().statusCode(statusCode);

    }




    }
