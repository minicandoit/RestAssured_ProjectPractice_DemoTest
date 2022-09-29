package test_util;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public abstract class SpartanNoAuthBaseTest {

    @BeforeAll
    public static void init(){
        //as a homework, put these information
        //in configurations.properties file
        //this will set the part of URL at RestAssured

        RestAssured.baseURI=ConfigurationReader.getProperty("baseURI");
        //  RestAssured.port=8000;
     //   RestAssured.basePath=ConfigurationReader.getProperty("basePath");
        RestAssured.basePath    = "/api" ;
        String url = ConfigurationReader.getProperty("spartan.database.url");
        String username = ConfigurationReader.getProperty("spartan.database.username");
        String password = ConfigurationReader.getProperty("spartan.database.password");
        DB_Utility.createConnection(url,username,password);


    }
    @AfterAll
    public static void cleanUp(){
        RestAssured.reset();
    }



}
