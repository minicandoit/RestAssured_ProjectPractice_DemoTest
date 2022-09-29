package day9;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import test_util.DB_Utility;
import test_util.SpartanNoAuthBaseTest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SpartanSearchTest extends SpartanNoAuthBaseTest {
    @DisplayName("Test GET/spartans/search result with DB result")
    @Test
    public void testSearch(){
        //search for nameContains a and gender Female
        //compare the count with DB result
        String query="SELECT * FROM SPARTANS WHERE LOWER(NAME) LIKE '%a%' and GENDER = 'Female'";
        DB_Utility.runQuery(query);
        DB_Utility.displayAllData();

        int expectedCount=DB_Utility.getRowCount();
        System.out.println("expectedCount = " + expectedCount);

        given()
                 .queryParam("nameContains","a")
                 .queryParam("gender","Female").
        when()
                .get("/spartans/search").
        then()
                 .statusCode(200)
                .body("totalElement",equalTo(expectedCount))
                //content jsonpath is pointing to json array, so we can directly check the size using hasSize matcher
                .body("content",hasSize(expectedCount))
                                              ;
/*
{
    "content": [
        {
            "id": 4,
            "name": "Paige",
            "gender": "Female",
            "phone": 3786741487
        },
        {
            "id": 5,
            "name": "Elizabeth",
            "gender": "Female",
            "phone": 1234567890
        },
        {
            "id": 10,
            "name": "Lorenza",
            "gender": "Female",
            "phone": 3312820936
        },
        {
            "id": 11,
            "name": "Nona",
            "gender": "Female",
            "phone": 7959094216
        },
        {
            "id": 13,
            "name": "Jaimie",
            "gender": "Female",
            "phone": 7842554879
        },
        {
            "id": 15,
            "name": "Meta",
            "gender": "Female",
            "phone": 1938695106
        },
        {
            "id": 27,
            "name": "Jeanelle",
            "gender": "Female",
            "phone": 6662999903
        },
        {
            "id": 30,
            "name": "Melania",
            "gender": "Female",
            "phone": 8916944276
        },
        {
            "id": 31,
            "name": "Maressa",
            "gender": "Female",
            "phone": 2018031787
        },
        {
            "id": 33,
            "name": "Diana",
            "gender": "Female",
            "phone": 1800233232
        },
        {
            "id": 42,
            "name": "Atlante",
            "gender": "Female",
            "phone": 4579688654
        },
        {
            "id": 46,
            "name": "Delora",
            "gender": "Female",
            "phone": 4115324496
        },
        {
            "id": 50,
            "name": "Jennica",
            "gender": "Female",
            "phone": 6986436734
        },
        {
            "id": 53,
            "name": "Berna",
            "gender": "Female",
            "phone": 2072918455
        },
        {
            "id": 55,
            "name": "Karmen",
            "gender": "Female",
            "phone": 5052029186
        },
        {
            "id": 57,
            "name": "Freida",
            "gender": "Female",
            "phone": 5712679899
        },
        {
            "id": 58,
            "name": "Alfy",
            "gender": "Female",
            "phone": 6529078662
        },
        {
            "id": 60,
            "name": "Elisabeth",
            "gender": "Female",
            "phone": 8165224005
        },
        {
            "id": 62,
            "name": "Robinia",
            "gender": "Female",
            "phone": 3542611707
        },
        {
            "id": 67,
            "name": "Janette",
            "gender": "Female",
            "phone": 9887020445
        },
        {
            "id": 68,
            "name": "Adda",
            "gender": "Female",
            "phone": 5361981152
        },
        {
            "id": 73,
            "name": "Amalita",
            "gender": "Female",
            "phone": 2663607211
        },
        {
            "id": 75,
            "name": "Leland",
            "gender": "Female",
            "phone": 9672110769
        },
        {
            "id": 77,
            "name": "Stevana",
            "gender": "Female",
            "phone": 1459126818
        },
        {
            "id": 81,
            "name": "Georgianne",
            "gender": "Female",
            "phone": 9594130239
        },
        {
            "id": 82,
            "name": "Catie",
            "gender": "Female",
            "phone": 4758237746
        },
        {
            "id": 87,
            "name": "Fenelia",
            "gender": "Female",
            "phone": 6475942543
        },
        {
            "id": 90,
            "name": "Rhianon",
            "gender": "Female",
            "phone": 5225172555
        },
        {
            "id": 91,
            "name": "Jaquenetta",
            "gender": "Female",
            "phone": 7338210388
        },
        {
            "id": 92,
            "name": "Caitlin",
            "gender": "Female",
            "phone": 6911121800
        },
        {
            "id": 93,
            "name": "Chanda",
            "gender": "Female",
            "phone": 9289012086
        },
        {
            "id": 94,
            "name": "Marylee",
            "gender": "Female",
            "phone": 6385380284
        },
        {
            "id": 95,
            "name": "Lilias",
            "gender": "Female",
            "phone": 5986446659
        },
        {
            "id": 98,
            "name": "Elita",
            "gender": "Female",
            "phone": 1042902741
        },
        {
            "id": 106,
            "name": "Sophia",
            "gender": "Female",
            "phone": 7087777777
        },
        {
            "id": 111,
            "name": "SophiaBee",
            "gender": "Female",
            "phone": 1800237777
        },
        {
            "id": 112,
            "name": "SelahBee",
            "gender": "Female",
            "phone": 1800237777
        },
        {
            "id": 141,
            "name": "Sadye",
            "gender": "Female",
            "phone": 7037777777
        },
        {
            "id": 159,
            "name": "SophiaBee",
            "gender": "Female",
            "phone": 1800237777
        },
        {
            "id": 160,
            "name": "SophiaBee",
            "gender": "Female",
            "phone": 1800237777
        }
    ],
    "totalElement": 40
}
 */

    }

    @ParameterizedTest
    @CsvSource({
            "e, Male",
            "le, Female",
            "k, Male",
            "g, Male",
            "u, Female",
            "f, Male"
    }
    )
    public void testSearchParameterized(String nameArg,String genderArg){

//
//        System.out.println("nameArg = " + nameArg);
//        System.out.println("genderArg = " + genderArg);

        String query="SELECT * FROM SPARTANS WHERE LOWER(NAME) LIKE '%"+nameArg+"%' and GENDER = '"+genderArg+"'";

        System.out.println("query = " + query);
        DB_Utility.runQuery(query) ;

int expectedCount=DB_Utility.getRowCount();
        System.out.println("expectedCount = " + expectedCount);


        given()
                .queryParam("nameContains",nameArg)
                .queryParam("gender",genderArg).
       when()
                .get("/spartans/search").
       then()
                .statusCode(200)
                .body("totalElement",equalTo(expectedCount))
                .body("content.name",everyItem(containsStringIgnoringCase(nameArg)))
                .body("content.gender",everyItem(is(genderArg)))
                ;


    }

    @ParameterizedTest
    @CsvSource({
            "e, Male",
            "le, Female",
            "k, Male",
            "g, Male",
            "u, Female",
            "f, Male"
    })
    public void testSearchParameterized1(String nameArg, String genderArg){

        String query = "SELECT * FROM SPARTANS WHERE LOWER(NAME) LIKE '%" + nameArg + "%' and GENDER = '"+ genderArg +"'" ;
        System.out.println("query = " + query);
        DB_Utility.runQuery(query) ;

        int expectedCount = DB_Utility.getRowCount() ;
        System.out.println("expectedCount = " + expectedCount);

        given()
                .queryParam("nameContains" , nameArg)
                .queryParam("gender" , genderArg).
                when()
                .get("/spartans/search").
                then()
                .statusCode(200)
                .body("totalElement" , equalTo(expectedCount))
                // OPTIONALLY , continue from here and check
                // if every item name containsString ignore case what we search for
                .body("content.name" , everyItem(  containsStringIgnoringCase( nameArg )  )  )
                //    every item gender is what we search for
                .body("content.gender" , everyItem(   is( genderArg)    )     )

        ;


    }





}
