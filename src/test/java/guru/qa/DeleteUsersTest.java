package guru.qa;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class DeleteUsersTest {

    @Test
    void getExistingUserTest() {
        given()
            .log().uri()
            .when()
            .delete("https://reqres.in/api/users/1")
            .then()
            .log().status()
            .log().body()
            .statusCode(204)
            .body(Matchers.anything());
    }

}
