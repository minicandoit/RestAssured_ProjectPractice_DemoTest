package day10;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.w3c.dom.stylesheets.LinkStyle;
import test_util.SpartanWithAuthBaseTest;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class XML_ResponseTest extends SpartanWithAuthBaseTest {

    @DisplayName("Test GET /spartans xml response ")
    @Test
    public void testXML(){
        //provide credentials and provide header as xml and send request
        //assert status code 200
        //assert content type is xml
        //assert first data name is something else

        given()
                .auth()
                .basic("user","user")
                .accept(ContentType.XML).
        when()
                .get("/spartans").
        then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.XML)
                .body("List.item[0].name",is("Meade"))
                .body("List.item[0].id",is("1"))

        ;
    }

    @DisplayName("Test GET /spartans xml response with XmlPath")
    @Test
    public void testXML_extractData(){

       Response response=given()
                         .auth()
                          .basic("user","user")
                          .accept(ContentType.XML).
              when()
                         .get("/spartans")
                ;

        XmlPath xp=response.htmlPath();
        int firstId=xp.getInt("List.item[0].id");
        System.out.println("firstId = " + firstId);
        String firstName=xp.getString("List.item[0].name");
        System.out.println("firstName = " + firstName);


        Long ThirdPhoneNumber=xp.getLong("List.item[2].phone");
        System.out.println("ThirdPhoneNumber = " + ThirdPhoneNumber);

        // get all IDs into String
        List<Integer>allIds= xp.getList("List.item.id");
        System.out.println("allIds = " + allIds);
//
//        List<String>allIds1= xp.getList("List.item.id");
//       for (String allId : allIds1) {
//            System.out.println("allId = " + allId);
//        }

       //assert below in one shot with Junit 5 Assert all

 /*
             firstId = 1
            firstName = Meade
            thirdPhoneNumber = 6105035231
            allIds has size of 112
          */


       // assertAll()

     assertEquals(1,firstId);
     assertEquals("Meade",firstName);
     assertEquals(6105035231L,ThirdPhoneNumber);
     assertEquals(112,allIds.size());

        assertAll(

                () -> assertEquals(1, firstId),
                () -> assertEquals("Meade", firstName),
                () -> assertEquals(6105035231L, ThirdPhoneNumber),
                () -> assertEquals(112, allIds.size())

        );

    }



}
