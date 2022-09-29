package day3;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import test_util.SpartanNoAuthBaseTest;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

public class SpartanJsonPath_Test extends SpartanNoAuthBaseTest {

    //http://34.230.50.126:8000/api/spartans
    @Test
    public void testOne() {

        Response response = given()
                .log().all()
                .pathParam("id", 120).
                        when()
                .get("/spartans/{id}")
                .prettyPeek();

        int myId = response.path("id");

        JsonPath jp = response.jsonPath();

        myId = jp.getInt("id");

        long myPhone = response.path("phone");
        String myName = jp.getString("name");
        System.out.println("jp.getMap() = " + jp.getMap(""));
        //empty means root path gives me the whole thing
    }

    @DisplayName("EXTRACT data GET/spartans")
    @Test
    public void testGetAllSpartans() {

        //  Response response=get("/spartans");
        //  JsonPath jp=response.jsonPath();

        JsonPath jp = get("/spartans").jsonPath().prettyPeek();

        //print first id in the json array response
        //[{},{},{},{}]

        System.out.println("jp.getInt(\"id[0]\") = " + jp.getInt("id[0]"));
        //print second json object name in the json array response
        System.out.println("jp.getString(\"name[1]\") = " + jp.getString("name[1]"));
        System.out.println("jp.getString(\"[0]\") = " + jp.getString("[0]"));
        System.out.println("jp.getInt(\"[0].id\") = " + jp.getInt("[0].id"));

    }


    @DisplayName("EXTRACT data from GET/spartans/search")
    @Test
    public void testGetSearchSpartans() {

        //http://34.230.50.126:8000/api/spartans/search
        // ?nameContains=ab&gender=Male

        JsonPath jp = given()
                .log().all()
                .queryParam("nameContains", "ab")
                .queryParam("gender", "Male").
                        when()
                .get("/spartans/search")
                .prettyPeek()
                .jsonPath();

        //find out first guy id, second guy name
        //content [0].id   content[1].name

        System.out.println("jp.getInt(\"content.id\") = " + jp.getInt("content[0].id"));
        System.out.println("jp.getString(\"content[1].name\") = " + jp.getString("content[1].name"));

        //store first jsonObject into a map

        Map<String, Object> firstJsonInMap = jp.getMap("content[0]");
        System.out.println("firstJsonInMap = " + firstJsonInMap);


    }


    @DisplayName("Saving json array fields into List")
    @Test
    public void testSavingsJsonArrayFieldsIntoList() {


        JsonPath jp =
                given()
                        .queryParam("nameContains", "ab")
                        .queryParam("gender", "Male")
                        .log().all().
                        when()
                        .get("/spartans/search")
                        .prettyPeek()
                        .jsonPath();
        //save all the ids into a list

        List<Integer> allIds = jp.getList("content.id", Integer.class);

        //2.jp.getList("json path here",class type you want this list to have)

        List<String> allNames = jp.getList("content.name", String.class);
        List<Long> allPhones = jp.getList("content.phone", Long.class);


    }

    @DisplayName("Get List Practice for GET/spartans")
    @Test
    public void testGetListOutOfAllSpartans() {
        JsonPath jp = get("/spartans").jsonPath();
        //save the list into List object and assert the size

        List<Integer> allIds = jp.getList("id", Integer.class);
        System.out.println("allIds.size() = " + allIds.size());
       assertThat(allIds,hasSize(129));


        //2.jp.getList("json path here",class type you want this list to have)

        List<String> allNames = jp.getList("name", String.class);
        System.out.println("allNames.size() = " + allNames.size());
        assertThat(allNames,hasSize(129));

        List<Long> allPhones = jp.getList("phone", Long.class);
        System.out.println("allPhones.size() = " + allPhones.size());
        assertThat(allPhones,hasSize(129));



    }
}