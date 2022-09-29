package Practice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import test_util.DB_Utility;
import test_util.SpartanNoAuthBaseTest;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.withNoArgs;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.*;

public class SpartanSearchTest extends SpartanNoAuthBaseTest {
    @DisplayName("Test GET /spartans/search result with DB result")
    @Test
    public void testSearch(){


        // Search for nameContains a and gender Female
        // compare the count with DB result

        DB_Utility.runQuery("select * from spartans where LOWER(NAME) LIKE '%a%' and gender='Female'");

      // DB_Utility.displayAllData();count=40

        int expectedCount=DB_Utility.getRowCount();//40

        given()
                .queryParam("nameContains","a")
                .queryParam("gender","Female").
        when()
                .get("/spartans/search")
                .prettyPeek().
       then()
                .statusCode(200)
        .body("totalElement", is(expectedCount))

                ; }


    @ParameterizedTest
    @CsvSource({
            "e, Male",
            "le, Female",
            "k, Male",
            "g, Male",
            "u, Female",
            "f, Male"
    })
    public void testSearchParameterized(String nameArg, String genderArg){

        DB_Utility.runQuery("select * from spartans where LOWER(NAME) LIKE '%"+nameArg+"%' and gender='"+genderArg+"'");
        int expectedCount=DB_Utility.getRowCount();

//        given()
//                .queryParam("nameContains",nameArg)
//                .queryParam("gender",genderArg).
//        when()
//                .get("/spartans/search")
//                .prettyPeek().
//        then()
//                .statusCode(200)
//                .body( "totalElement",is(expectedCount))
//             .body("content.name",everyItem(containsStringIgnoringCase(nameArg)))
//             .body("content.gender",is(genderArg))
//        ;
//

      List<String>contentList = given()
                .queryParam("nameContains", nameArg)
                .queryParam("gender", genderArg).
                        when()
                .get("/spartans/search")
                .prettyPeek().
                        then()
                .statusCode(200)
                .extract()
                .jsonPath().getList("content")
              ;
        System.out.println("contentList = " + contentList);


    }







    }
