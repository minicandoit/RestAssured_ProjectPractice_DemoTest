package day2;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.* ;
public class zipcode_test {
    @Test
    public void test(){
        //api.zippopotam.us/us/22030
        given()
                .log().all()
                .accept(ContentType.JSON)
                .pathParam("zip",22030).
        when()
                .get("http://api.zippopotam.us/us/{zip}").
        then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                ;
    }
}
