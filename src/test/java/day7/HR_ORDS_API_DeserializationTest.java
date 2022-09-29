package day7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Country;
import pojo.Department;
import pojo.Employee;
import pojo.Region;
import test_util.HR_ORDS_API_BaseTest;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class HR_ORDS_API_DeserializationTest extends HR_ORDS_API_BaseTest {

    //send request to /regions and save the result into List<Region>
    //assert the list has size 4

   @DisplayName("GET/regions")
    @Test
    public void testGetAllRegionAndSaveToListOfPOJO(){

       //savind json array into List<Region>==>De-Serialization
       List<Region>allRegions =
               get("/regions")
                                   .jsonPath()
                                   .getList("items",Region.class);
       System.out.println("allRegions = " + allRegions);
   allRegions.forEach(p-> System.out.println(p));
   }


   @DisplayName("GET/Countries")
    @Test
    public void testAllCountries(){
       Country c1=new Country("AR","Argentina",1);
       System.out.println("c1 = " + c1);

       //save 3rd country as Country POJO
      Country thirdCountry=get("/countries").jsonPath()
              .getObject("items[2]",Country.class);

      //save all countries as List<POJO>

       List<Country>allCountries=get("/countries").jsonPath()
                                   .getList("items",Country.class);

       allCountries.forEach(p-> System.out.println(p));


   }

   //We want to create pojo that represent Employee data
    //we only care about employee_id, first_name,last_name,salary, department_id
    //we want to save the json data as pojo and we want to ignore any other fields other than specified above

    @DisplayName("GET /employees")
    @Test
    public void testAllEmployees(){

       //get the first employee and sava it into employee object
        Employee e1=get("/employees").jsonPath()
                .getObject("items[0]",Employee.class);
        System.out.println("e1 = " + e1);
   }

   //till this moment we have been creating our pojo class field names
    //to match exact name from json field
    //and yet there will be situations that the json field name
    //we want to be able to name the POJO field

//creat a POJO class called departments with below fields
    //department_id,name, manager_id,location_id

    @DisplayName("GET /departments")
    @Test
    public void testAllDepartments(){

        Department d1=get("/departments").jsonPath()
                        .getObject("items[0]",Department.class);
        System.out.println("d1 = " + d1);


    }
























}
