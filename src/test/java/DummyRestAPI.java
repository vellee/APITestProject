import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DummyRestAPI {
    public static final Logger logger = LogManager.getLogger(DummyRestAPI.class);
    public static final String EMPLOYEE_NAME = "name";
    public static final String EMPLOYEE_AGE = "age";
    public static final String EMPLOYEE_SALARY = "salary";

    @Test
    public void createEmployeeTest() {
        Response response = given().contentType("application/json").body("\t{\"name\":\"Jason Statham\",\"salary\":\"150150\",\"age\":\"50\"}").post(Consts.EMPLOYEE_URL + Consts.CREATE_EMPLOYEE_ENDPOINT);
        System.out.println(response.asString());
        logger.info(response);
        response.then().statusCode(200);


    }

    @Test
    public void getAllEmployeeDataTest() {
        Response response = given().get(Consts.EMPLOYEE_URL + Consts.ALL_EMPLOYEE_ENDPOINT);
        response.then().statusCode(200);
        System.out.println(response.asString());
        //response.then().body("data.employee_name",containsString("Airi Satou"));

    }

    @Test
    public void getEmployeeDataByIdTest() {
        Response response = given().get(Consts.EMPLOYEE_URL + Consts.EMPLOYEE_BY_ID_ENDPOINT + "5");
        System.out.println(response.asString());
        response.then().statusCode(200);
        response.then().body("data.employee_name", equalTo("Airi Satou"));
    }

    @Test
    public void createEmployeeTestHashMapExample() {
        HashMap employee = new HashMap();
        employee.put(EMPLOYEE_NAME, "Boris Levin");
        employee.put(EMPLOYEE_AGE, "70");
        employee.put(EMPLOYEE_SALARY, "150150");
        Response response = given().contentType("application/json").body(employee).post(Consts.EMPLOYEE_URL + Consts.CREATE_EMPLOYEE_ENDPOINT);
        System.out.println(response.asString());
        response.then().body("data.name",equalTo(employee.get(EMPLOYEE_NAME)));
        response.then().body("data.age",equalTo(employee.get(EMPLOYEE_AGE)));
        response.then().body("data.salary",equalTo(employee.get(EMPLOYEE_SALARY)));

    }
}
