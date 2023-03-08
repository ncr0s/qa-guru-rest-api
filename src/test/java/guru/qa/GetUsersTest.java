package guru.qa;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class GetUsersTest {

    @Test
    void getExistingUserTest() {
        given()
            .log().uri()
            .when()
            .get("https://reqres.in/api/users/1")
            .then()
            .log().status()
            .log().body()
            .statusCode(200)
            .body("data.id", is(1))
            .body("data.email", is("george.bluth@reqres.in"))
            .body("data.first_name", is("George"))
            .body("data.last_name", is("Bluth"))
            .body("data.avatar", is("https://reqres.in/img/faces/1-image.jpg"));
    }

    @Test
    void getNonExistingUserTest() {
        given()
            .log().uri()
            .when()
            .get("https://reqres.in/api/users/1312")
            .then()
            .log().status()
            .log().body()
            .statusCode(404)
            .body(Matchers.anything());
    }
}
