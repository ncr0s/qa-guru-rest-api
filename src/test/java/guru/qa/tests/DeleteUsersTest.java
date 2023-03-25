package guru.qa.tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.hamcrest.Matchers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static guru.qa.specs.userSpecs.getResponseSpec;
import static guru.qa.specs.userSpecs.requestSpec;
import static io.restassured.RestAssured.given;

public class DeleteUsersTest {

    @Tags({@Tag("REST-API"), @Tag("DELETE")})
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Delete user test")
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
