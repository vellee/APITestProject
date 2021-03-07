import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AllBooksEndpointTest {

    private static Response response;

    @BeforeAll
    public static void setup() {
        response = given().get(Consts.URL + Consts.BOOKS_ENDPOINT);
        System.out.println(response.asString());
    }

    @Test
    public void getAllBooksResponseCodeTest() {
//        Response response = given().get(Consts.URL + Consts.BOOKS_ENDPOINT);
//        System.out.println(response.asString());
        response.then().statusCode(200);
    }

    @Test
    public void getAllBooksNumOfResultsTest() {
//        Response response = given().get(Consts.URL + Consts.BOOKS_ENDPOINT);
//        System.out.println(response.asString());
        response.then().body("total", notNullValue());
        response.then().body("total", greaterThan(0));
        response.then().body("total", equalTo(3));
    }

}
