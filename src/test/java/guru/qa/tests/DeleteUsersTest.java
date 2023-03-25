package guru.qa.tests;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static guru.qa.specs.userSpecs.getResponseSpec;
import static guru.qa.specs.userSpecs.requestSpec;
import static io.restassured.RestAssured.given;

public class DeleteUsersTest {

    @Test
    void deleteExistingUserTest() {
        given(requestSpec)
            .when()
            .delete("/users/1")
            .then()
            .spec(getResponseSpec)
            .statusCode(204)
            .body(Matchers.anything());
    }

}
