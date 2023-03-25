package guru.qa.tests;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static guru.qa.helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.given;

public class DeleteUsersTest {

    @Test
    void deleteExistingUserTest() {
        given()
            .filter(withCustomTemplates())
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
