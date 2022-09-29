package Practice;

import org.junit.jupiter.api.Test;
import test_util.DB_Utility;
import test_util.DB_Utility.*;
import test_util.LibraryAppBaseTest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Library_test extends LibraryAppBaseTest {


    @Test
    public  void LibraryTest(){

        DB_Utility.runQuery("select count(*) from book_borrow where returned_date is null ");
        DB_Utility.displayAllData();

        String borrowedBooks=DB_Utility.getFirstRowFirstColumn();
        System.out.println("borrowedBooks = " + borrowedBooks);

        given()
                .header("X-LIBRARY-TOKEN",LibrarianToken).
        when()
                .get("/dashboard_stats")
//        .prettyPeek()
//                ;

        .then()
                .statusCode(200)
                .body("borrowed_books",is(borrowedBooks))
                ;


    }


}
