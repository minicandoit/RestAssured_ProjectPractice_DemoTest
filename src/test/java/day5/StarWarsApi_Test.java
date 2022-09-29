package day5;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static io.restassured.RestAssured.*;
public class StarWarsApi_Test {
    /**
     * Interview Questions :
     * Send request to  GET https://swapi.dev/api/people/
     * Find out average height of all people showed up in the response
     */
@BeforeAll
    public static void init(){
    baseURI="https://swapi.dev";
    basePath="/api";
}

    @DisplayName("GET average height from GET/people response")
    @Test
    public  void testGetAverageHeight() {

        List<Integer> allHeights = get("/people")
                .jsonPath()
                .getList("results.height", Integer.class);
        System.out.println("allHeights = " + allHeights);
int total=0;
int average=0;
        for (Integer each : allHeights) {
            total+=each;
            average=total/allHeights.size();

        }
        System.out.println("average = " + average);


    }


    @DisplayName("GET all height from all the pages and find average")
    @Test
    public void testGetAllPagesAverageHeight() {
List<String>allHeights=new ArrayList<>();

        JsonPath jp = get("/people").jsonPath();
        int peopleCount = jp.getInt("count");
        int pageCount=(peopleCount%10==0)?peopleCount/10:(peopleCount/10)+1;

        for (int pageNum = 1; pageNum <=pageCount ; pageNum++) {

            List<String>heightsOnThisPage=get("/people?page="+pageNum)
                                              .jsonPath()
                                              .getList("results.height");
            allHeights.addAll(heightsOnThisPage);
            System.out.println("heightsOnThisPage = " +pageNum+ heightsOnThisPage);


        }

        allHeights.remove("unknown");
        System.out.println("allHeights = " + allHeights);
        System.out.println("allHeights.size() = " + allHeights.size());

        List<String> lst= new ArrayList<>(allHeights);
        System.out.println("lst = " + lst);
        System.out.println("lst.size() = " + lst.size());
        int total=0;
        int average=0;
        for (String each : lst) {


             total+=Integer.parseInt(each);
            average=total/allHeights.size();
        }

        System.out.println("average = " + average);
    }
@AfterAll
    public static void cleanUp(){
    reset();
}







}
