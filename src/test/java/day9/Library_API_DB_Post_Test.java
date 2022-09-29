package day9;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import test_util.DB_Utility;
import test_util.LibraryAppBaseTest;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;

public class Library_API_DB_Post_Test extends LibraryAppBaseTest {
    //Add random book using POST /add_book
    //grab the id and write a query to book information with this id

    //assert All data match exactly as it was posted
    @DisplayName("Add One book, validate from DB")
@Test
    public void testAddBookPersisted(){
        Map<String,Object>randomBookMapBody=getRandomBook();
        System.out.println("randomBookMapBody = " + randomBookMapBody);

        int newBookId=given()
                             .header("X-LIBRARY-TOKEN",LibrarianToken)
                             .contentType(ContentType.URLENC)
                             .formParams(randomBookMapBody).
                       when()
                             .post("/add_book").

                        then()
                              .statusCode(200)//normally it return 201,this one decided to return 200
                              .log().body()
                .extract()
                                .jsonPath().getInt("book_id");
        System.out.println("newBookId = " + newBookId);

        DB_Utility.runQuery("SELECT * FROM books where id="+newBookId);
     //   DB_Utility.displayAllData();
        Map<String,String>dbResultMap=DB_Utility.getRowMap(1);
        System.out.println("dbResultMap = " + dbResultMap);

//randomBookMapBody = {year=1616, author=Selene Pollich, isbn=83876896, name=Mr Standfast, description=No statement can catch the ChuckNorrisException., book_category_id=5}
 //dbResultMap = {id=3381, name=Mr Standfast, isbn=83876896, year=1616, author=Selene Pollich, book_category_id=5, description=No statement can catch the ChuckNorrisException., added_date=2021-04-10 15:40:04}

   assertThat(dbResultMap.get("name"),is(randomBookMapBody.get("name")));
      //  assertThat(dbResultMap.get(Integer.parseInt("year")),is(randomBookMapBody.get("year")));
//   assertAll(
//           ()->   assertThat(dbResultMap.get("name"),is(randomBookMapBody.get("name"))),
//           ()->   assertThat(dbResultMap.get("year"),is(randomBookMapBody.get(Integer.parseInt("year"))),
//           ()->   assertThat(dbResultMap.get("isbn"),is(randomBookMapBody.get(("isbn"))
//
//   );

    }


}
