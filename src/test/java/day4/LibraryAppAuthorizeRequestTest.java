package day4;
import apple.laf.JRSUIConstants;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Spartan;
import spartan_util.SpartanUtil;
import test_util.LibraryAppBaseTest;
import test_util.SpartanNoAuthBaseTest;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

public class LibraryAppAuthorizeRequestTest extends LibraryAppBaseTest {

    @DisplayName("GET /get_user_by_id/{user_id}")
    @Test
    public void testOneUser(){
        System.out.println("myToken = " + LibrarianToken);
        //we are sending request to this endpoint  : GET /get_user_by_id/1
        
        given()
                .log().all()
                .header("x-library-token", LibrarianToken)
                      .pathParam("user_id", 1).
        when()
                     .get("/get_user_by_id/{user_id}").
        then()
                      .log().all()
                      .statusCode(200)
                ;
         }
         
      @DisplayName("GET /get_all_users")     
        @Test     
        public void testGetAllUsers() {
          System.out.println("myToken = " + LibrarianToken);

          List<String> allNames = given()
                  .header("x-library-token", LibrarianToken).
                          when()
                  .get("/get_all_users").
                          then()
                  //.log().all()
                  .statusCode(200).
                          extract()
                  .jsonPath()
                  .getList("name", String.class);

          // assert the size is 8465
          assertThat(allNames, hasSize(8665));

          // print the unique names count
          Set<String>uniqueNames=new HashSet<>(allNames);
          System.out.println("uniqueNames.size() = " + uniqueNames.size());
      }

    @DisplayName("POST /add_book")
    @Test
    public void testAddOneBook() {
        /*
            name Awesome book
            isbn IMDBS132
            year 2019
            author Ike
            book_category_id 2
            description good book
         */

        Map<String, Object> newBook = getRandomBook();

        int newBookId = given()
                .log().all()
                .header("x-library-token", LibrarianToken)
                .contentType(ContentType.URLENC)
                // using formParams with s we can pass multiple param in one shot
                .formParams(newBook).
                        when()
                .post("/add_book").
                        then()
                .log().body()
                .statusCode(200)
                .extract()
                .jsonPath().getInt("book_id");

// Send additional request to GET /get_book_by_id/{book_id}
        // to verify all data has been added correctly
        given()

                .header("x-library-token", LibrarianToken)
               .log().uri()
                .pathParam("book_id",newBookId).
        when()
                .get("/get_book_by_id/{book_id}").
        then()
               .log().body()
               .statusCode(200)


                //    .body("id",is(String.valueOf(newBookId)))
                .body("id" , is( newBookId+"" ) )
                .body("name",  is ( newBook.get("name") ) )
                .body("isbn",  is ( newBook.get("isbn") ) )
                .body("year",  is ( newBook.get("year")+"" ) )
                .body("author",  is ( newBook.get("author") ) )
                .body("book_category_id",  is ( newBook.get("book_category_id")+"" ) )
                .body("description",  is ( newBook.get("description") ) )




        ;
    }    
      
}
