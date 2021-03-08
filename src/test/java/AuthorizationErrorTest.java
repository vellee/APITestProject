import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AuthorizationErrorTest {
    private static Response response;

    @Test
    public void invalidTokenTest() {
        response = given().auth().oauth2("blabla").contentType("application/json").get(Consts.URL + Consts.MOVIE_ENDPOINT);
        System.out.println(response.asString());
        response.then().statusCode(401);
        response.then().body("message", equalTo("Unauthorized."));
        response.then().body("message", containsString("Unauth"));
    }
}
