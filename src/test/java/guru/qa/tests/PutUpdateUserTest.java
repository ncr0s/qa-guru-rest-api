package guru.qa.tests;

import com.github.javafaker.Faker;
import guru.qa.models.PutUserResponseBodyModel;
import guru.qa.models.UserRequestBodyModel;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static guru.qa.specs.UserSpecs.putResponseSpec;
import static guru.qa.specs.UserSpecs.requestSpec;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static java.time.format.DateTimeFormatter.ISO_INSTANT;
import static org.assertj.core.api.Assertions.assertThat;

@Tags({@Tag("REST-API"), @Tag("PUT")})
public class PutUpdateUserTest {
    Faker faker = new Faker();
    UserRequestBodyModel request = new UserRequestBodyModel();
    PutUserResponseBodyModel response = new PutUserResponseBodyModel();
    String dateTime = ZonedDateTime.now().minusSeconds(1).format(ISO_INSTANT);

    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Updating name and job of the user")
    @Test
    void updateUsersNameAndJobTest() {
        request
            .setName(faker.hitchhikersGuideToTheGalaxy().character())
            .setJob(faker.hitchhikersGuideToTheGalaxy().location());

        response = given(requestSpec)
            .body(request)
            .when()
            .put("https://reqres.in/api/users/")
            .then()
            .spec(putResponseSpec)
            .extract().as(PutUserResponseBodyModel.class);

        assertThat(response.getName()).isEqualTo(request.getName());
        assertThat(response.getJob()).isEqualTo(request.getJob());
        assertThat(response.getUpdatedAt()).isGreaterThanOrEqualTo(dateTime);
    }

    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Updating job of the user")
    @Test
    void updateUsersJobTest() {
        request
            .setJob(faker.dune().title());

        response = given(requestSpec)
            .body(request)
            .when()
            .put("https://reqres.in/api/users/")
            .then()
            .spec(putResponseSpec)
            .extract().as(PutUserResponseBodyModel.class);

        assertThat(response.getName()).isEqualTo(request.getName());
        assertThat(response.getJob()).isEqualTo(request.getJob());
        assertThat(response.getUpdatedAt()).isGreaterThanOrEqualTo(dateTime);
    }

    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Updating name of the user")
    @Test
    void updateUsersNameTest() {
        request
            .setName(faker.lordOfTheRings().character());

        response = given(requestSpec)
            .contentType(JSON)
            .body(request)
            .when()
            .put("https://reqres.in/api/users/")
            .then()
            .spec(putResponseSpec)
            .extract().as(PutUserResponseBodyModel.class);

        assertThat(response.getName()).isEqualTo(request.getName());
        assertThat(response.getJob()).isEqualTo(request.getJob());
        assertThat(response.getUpdatedAt()).isGreaterThanOrEqualTo(dateTime);
    }

    @Severity(SeverityLevel.MINOR)
    @DisplayName("Updating request without parameters")
    @Test
    void createEmptyUserTest() {
        response = given(requestSpec)
            .contentType(JSON)
            .body(request)
            .when()
            .put("https://reqres.in/api/users/")
            .then()
            .spec(putResponseSpec)
            .extract().as(PutUserResponseBodyModel.class);

        assertThat(response.getName()).isEqualTo(request.getName());
        assertThat(response.getJob()).isEqualTo(request.getJob());
        assertThat(response.getUpdatedAt()).isGreaterThanOrEqualTo(dateTime);
    }
}
