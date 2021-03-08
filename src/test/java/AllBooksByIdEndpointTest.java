import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class AllBooksByIdEndpointTest {
    private static Response response;
    public static final Logger logger = LogManager.getLogger(AllBooksByIdEndpointTest.class);

    @BeforeAll
    public static void setup() {
        logger.info("BeforeAll procedure");
        response = given().get(Consts.URL + Consts.BOOKS_ENDPOINT);
        System.out.println(response.asString() + "\n");

    }

    @Test
    public void getAllBooksResponseCodeTest() {
        logger.info("Checking for correct response code");
        response.then().statusCode(200);
    }

    @ParameterizedTest
    @ValueSource(strings = {"5cf5805fb53e011a64671582", "5cf58077b53e011a64671583", "5cf58080b53e011a64671584"})
    public void getAllBooksById(String id) {
        response = given().get(Consts.URL + Consts.BOOKS_ENDPOINT + "/" + id);
        logger.info("Info about every book");
        System.out.println(response.asString());
        response.then().body("total", equalTo(1));
        response.then().body("docs._id", contains(id));
        response.then().body("docs.name", notNullValue());

    }

}
