package guru.qa.tests;

import guru.qa.models.GetUserResponseModel;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static guru.qa.specs.userSpecs.getResponseSpec;
import static guru.qa.specs.userSpecs.requestSpec;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;


@Tags({@Tag("REST-API"), @Tag("GET")})
public class GetUsersTest {

    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Existing user should be returned by get request")
    @Test
    void getExistingUserTest() {
        GetUserResponseModel response = given(requestSpec)
            .when()
            .get("/users/1")
            .then()
            .spec(getResponseSpec)
            .statusCode(200)
            .extract().body().as(GetUserResponseModel.class);

        assertThat(response.getData().getId()).isEqualTo(1);
        assertThat(response.getData().getEmail()).isEqualTo("george.bluth@reqres.in");
        assertThat(response.getData().getFirstName()).isEqualTo("George");
        assertThat(response.getData().getLastName()).isEqualTo("Bluth");
        assertThat(response.getData().getAvatar()).isEqualTo("https://reqres.in/img/faces/1-image.jpg");
    }

    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("For non existed user should be returned 404 status with empty body")
    @Test
    void getNonExistingUserTest() {
        GetUserResponseModel response = given(requestSpec)
            .when()
            .get("/users/1312")
            .then()
            .spec(getResponseSpec)
            .statusCode(404)
            .extract().body().as(GetUserResponseModel.class);

        assertThat(response).isEqualTo(new GetUserResponseModel());
    }

    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Check users email in response on get users list")
    @Test
    void checkEmailWithGroovy() {
        given(requestSpec)
            .when()
            .get("/users")
            .then()
            .spec(getResponseSpec)
            .body("data.findAll{it.email =~/.?@reqres.in/}.email.flatten()",
                hasItem("george.bluth@reqres.in"));
    }

    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Check users last name in response on get users list with pagination")
    @Test
    void checkPaginationWithGroovy() {
        given(requestSpec)
            .when()
            .get("/users?page=2")
            .then()
            .spec(getResponseSpec)
            .body("data.findAll{it.last_name=~/./}.last_name",
                hasItem("Edwards"));
    }
}
