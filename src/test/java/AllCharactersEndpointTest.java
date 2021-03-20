import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AllCharactersEndpointTest {
    private static Response response;
    public static final Logger logger = LogManager.getLogger(AllMoviesEndpointTest.class);

    @BeforeAll
    public static void setup() {
        logger.info("Initializing response with value");
        response = given().auth().oauth2(Consts.TOKEN).get(Consts.URL + Consts.CHARACTER_ENDPOINT);
        System.out.println(response.asString());
    }

    @Test
    public void getAllCharactersRespondCodeTest() {
        response.then().statusCode(200);
    }

    @ParameterizedTest
    @ValueSource(strings = {"5cd99d4bde30eff6ebccfbbe", "5cd99d4bde30eff6ebccfbc1", "5cd99d4bde30eff6ebccfbc2"})
    public void getCharacterById(String id) {
        response = given().auth().oauth2(Consts.TOKEN).get(Consts.URL + Consts.CHARACTER_ENDPOINT + "/" + id);
        logger.info("Info about character");
        System.out.println(response.asString());
        //response.then().body("docs.gender", equalTo("Female"));
        response.then().body("docs._id", contains(id));
        response.then().body("docs.name", notNullValue());

    }
}
