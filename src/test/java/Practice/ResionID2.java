package Practice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static test_util.DB_Utility.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import pojo.Region;
import test_util.HR_ORDS_API_BaseTest;

import java.util.List;
import java.util.Map;

public class ResionID2 extends HR_ORDS_API_BaseTest {


    // We added DB Connection and DB Destroy method into base test
    // We added the base uri of HR ORDS API into configuration file

    @Test
    public void testHR_ORDS_API() {
     runQuery("select * from regions where region_id =2");
     displayAllData();

        // send request to GET /regions/{region_id} and compare the result with DB result

        // save expected data for region_id of 2 into List
        List<String>firstRowExpectedList=getRowDataAsList(1);
        System.out.println("firstRowExpectedList = " + firstRowExpectedList);
//firstRowExpectedList = [2, Americas]
        // send API request to GET /regions/{region_id} with id of 1 , save the result into POJO
        Region regionId2=given()
                                .pathParam("region_id",2).
                        when()
                                .get("/regions/{region_id}")
                                .jsonPath()
                               .getObject("items[0]",Region.class);
        System.out.println("regionId2 = " + regionId2);
        assertThat(regionId2.getRegion_id(),is(Integer.parseInt(firstRowExpectedList.get(0))));
        assertThat(regionId2.getRegion_name(),is(firstRowExpectedList.get(1)));
    }


    @DisplayName("Test GET /regions and compare with expected DB Result")
    @Test
    public void testAllRegionsWithDB() {

runQuery("select * from regions");
displayAllData();

List<Map<String,String>>expectedRowMapList=getAllRowAsListOfMap();
        System.out.println("expectedRowMapList = " + expectedRowMapList);

        List<Region> allRegionPojoLst = get("/regions")
                .jsonPath()
                .getList("items", Region.class);
          System.out.println("allRegionPojoLst = " + allRegionPojoLst);

assertThat(expectedRowMapList.size(),equalTo(allRegionPojoLst.size()));

        for (int i = 0; i <expectedRowMapList.size() ; i++) {
            // compare each region id and region name match the expected region id and name
            assertThat(allRegionPojoLst.get(i).getRegion_id(),is(Integer.parseInt(expectedRowMapList.get(i).get("REGION_ID"))));
            assertThat(allRegionPojoLst.get(i).getRegion_name(),is(expectedRowMapList.get(i).get("REGION_NAME")));
        }

    }
  @DisplayName("homework")

    @ParameterizedTest
  @CsvSource(
          {
     "1, Europe ",
     "2,Americas" ,
     "3, Asia" ,
     "4,Middle East and Africa"
                      }
  )

  public void homeWork(String regionId,String regionName){
      /*
            // HOMEWORK : RUN QUERY  runQuery("SELECT * FROM REGIONS") save result as List of Map
       */       // Write a method to return above List of Map called getAllRegionListOfMap
                // Write a parameterized Test for GET /regions/{region_id}
                // Use getAllRegionListOfMap method as Method Source for your Parameterized Test
         runQuery("SELECT * FROM REGIONS")  ;
         displayAllData();

           List<Map<String,String>>allRegions=getAllRowAsListOfMap();
      System.out.println("allRegions = " + allRegions);

      given()
              .pathParam("region_id",regionId).
               when()
                       .get("/regions/{region_id}").
               then()
                      .body("items[0].region_name",is(regionName))
                      .body("items[0].region_id",is(Integer.parseInt(regionId)))
      ;

  }

    @ParameterizedTest
    @MethodSource("getManyCountryIds")
    public void testCountryWithMethodSource(String countryIdArg){

        System.out.println("countryIdArg = " + countryIdArg);
        // GET /countries/{country_id}
        given()
                .log().uri()
                .pathParam("country_id",countryIdArg).
        when()
                .get("/countries/{country_id}").
        then()
                .log().body()
                .statusCode(200)
                .body("count",is(1));
    }

    public static List<String>getManyCountryIds(){

        List<String>countryNameList=get("/countries")
                                      .jsonPath().getList("items.country_id",String.class);
        return countryNameList;


    }




    }