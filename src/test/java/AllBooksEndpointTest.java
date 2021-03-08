import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AllBooksEndpointTest {

    private static Response response;
    public static final Logger logger = LogManager.getLogger(AllBooksEndpointTest.class);

    @BeforeAll
    public static void setup() {
        logger.info("Initializing response with value");
        response = given().get(Consts.URL + Consts.BOOKS_ENDPOINT);
        System.out.println(response.asString());
    }

    @Test
    public void getAllBooksResponseCodeTest() {
//        Response response = given().get(Consts.URL + Consts.BOOKS_ENDPOINT);
//        System.out.println(response.asString());
        logger.info("Checking for correct response code");
        response.then().statusCode(200);
    }

    @Test
    public void getAllBooksNumOfResultsTest() {
//        Response response = given().get(Consts.URL + Consts.BOOKS_ENDPOINT);
//        System.out.println(response.asString());
        logger.info("Checking value of total, and comparing it to given expected value");
        response.then().body("total", notNullValue());
        response.then().body("total", greaterThan(0));
        response.then().body("total", equalTo(3));
    }

    @Test
    public void getAllBooksResultHasItemsTest() {

        response.then().body("docs._id", notNullValue());
        response.then().body("docs._id", hasItem("5cf5805fb53e011a64671582"));
        response.then().body("docs.name", hasItem("The Fellowship Of The Ring"));
        logger.info("Checking if all books are in return list of books");
        response.then().body("docs.name", hasItems("The Fellowship Of The Ring", "The Two Towers", "The Return Of The King"));
    }

    @Test
    public void getAllBooksResultContainsItemsTest() {
        response.then().body("docs._id", notNullValue());
        response.then().body("docs._id", hasItem("5cf5805fb53e011a64671582"));
        response.then().body("docs.name", hasItem("The Fellowship Of The Ring"));
        logger.info("Checking if all books are in return list of books");
        response.then().body("docs.name", contains("The Fellowship Of The Ring", "The Two Towers", "The Return Of The King"));
    }

    // Performance test. How long it takes to get response from system
    @Test
    public void getAllBooksResponseTest() {
        response.then().time(lessThan(2000l));

    }

}

