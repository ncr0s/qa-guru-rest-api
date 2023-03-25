package guru.qa.tests;

import com.github.javafaker.Faker;
import guru.qa.models.PutUserResponseBodyModel;
import guru.qa.models.UserRequestBodyModel;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static guru.qa.helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static java.time.format.DateTimeFormatter.ISO_INSTANT;
import static org.assertj.core.api.Assertions.assertThat;

public class PutUpdateUserTest {
    Faker faker = new Faker();
    UserRequestBodyModel request = new UserRequestBodyModel();
    PutUserResponseBodyModel response = new PutUserResponseBodyModel();
    String dateTime = ZonedDateTime.now().minusSeconds(1).format(ISO_INSTANT);

    @Test
    void updateUsersNameAndJobTest() {
        request
            .setName(faker.hitchhikersGuideToTheGalaxy().character())
            .setJob(faker.hitchhikersGuideToTheGalaxy().location());

        response = given()
            .filter(withCustomTemplates())
            .log().uri()
            .log().body()
            .contentType(JSON)
            .body(request)
            .when()
            .put("https://reqres.in/api/users/")
            .then()
            .log().status()
            .log().body()
            .statusCode(200)
            .extract().as(PutUserResponseBodyModel.class);

        assertThat(response.getName()).isEqualTo(request.getName());
        assertThat(response.getJob()).isEqualTo(request.getJob());
        assertThat(response.getUpdatedAt()).isGreaterThanOrEqualTo(dateTime);
    }

    @Test
    void updateUsersJobTest() {
        request
            .setJob(faker.dune().title());

        response = given()
            .filter(withCustomTemplates())
            .log().uri()
            .log().body()
            .contentType(JSON)
            .body(request)
            .when()
            .put("https://reqres.in/api/users/")
            .then()
            .log().status()
            .log().body()
            .extract().as(PutUserResponseBodyModel.class);

        assertThat(response.getName()).isEqualTo(request.getName());
        assertThat(response.getJob()).isEqualTo(request.getJob());
        assertThat(response.getUpdatedAt()).isGreaterThanOrEqualTo(dateTime);
    }

    @Test
    void updateUsersNameTest() {
        request
            .setName(faker.lordOfTheRings().character());

        response = given()
            .filter(withCustomTemplates())
            .log().uri()
            .log().body()
            .contentType(JSON)
            .body(request)
            .when()
            .put("https://reqres.in/api/users/")
            .then()
            .log().status()
            .log().body()
            .statusCode(200)
            .extract().as(PutUserResponseBodyModel.class);

        assertThat(response.getName()).isEqualTo(request.getName());
        assertThat(response.getJob()).isEqualTo(request.getJob());
        assertThat(response.getUpdatedAt()).isGreaterThanOrEqualTo(dateTime);
    }

    @Test
    void createEmptyUserTest() {
        response = given()
            .filter(withCustomTemplates())
            .log().uri()
            .log().body()
            .contentType(JSON)
            .body(request)
            .when()
            .put("https://reqres.in/api/users/")
            .then()
            .log().status()
            .log().body()
            .statusCode(200)
            .extract().as(PutUserResponseBodyModel.class);

        assertThat(response.getName()).isEqualTo(request.getName());
        assertThat(response.getJob()).isEqualTo(request.getJob());
        assertThat(response.getUpdatedAt()).isGreaterThanOrEqualTo(dateTime);
    }
}
