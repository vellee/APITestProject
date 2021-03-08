import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AllMoviesEndpointTest {
    private static Response response;
    public static final Logger logger = LogManager.getLogger(AllMoviesEndpointTest.class);

    @BeforeAll
    public static void setup() {
        logger.info("Initializing response with value");
        response = given().auth().oauth2(Consts.TOKEN).get(Consts.URL + Consts.MOVIE_ENDPOINT);
        System.out.println(response.asString());
    }

    @Test
    public void getAllMoviesRespondCodeTest() {
        response.then().statusCode(200);
    }

    @ParameterizedTest
    @ValueSource(strings = {"5cd95395de30eff6ebccde56"})
    public void getAllBooksById(String id) {
        response = given().auth().oauth2(Consts.TOKEN).contentType("application/json").get(Consts.URL + Consts.MOVIE_ENDPOINT + "/" + id);
        logger.info("Info about every movie");
        System.out.println(response.asString());
        response.then().body("total", equalTo(1));
        response.then().body("docs._id", contains(id));
        response.then().body("docs.name", notNullValue());

    }
}
