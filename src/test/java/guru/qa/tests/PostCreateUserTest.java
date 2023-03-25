package guru.qa.tests;

import com.github.javafaker.Faker;
import guru.qa.models.UserRequestBodyModel;
import guru.qa.models.PostUserResponseBodyModel;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static guru.qa.specs.userSpecs.requestSpec;
import static guru.qa.specs.userSpecs.postResponseSpec;
import static io.restassured.RestAssured.given;
import static java.time.format.DateTimeFormatter.ISO_INSTANT;
import static org.assertj.core.api.Assertions.assertThat;

public class PostCreateUserTest {
    Faker faker = new Faker();
    UserRequestBodyModel request = new UserRequestBodyModel();
    PostUserResponseBodyModel response = new PostUserResponseBodyModel();
    String dateTime = ZonedDateTime.now().minusSeconds(1).format(ISO_INSTANT);

    @Test
    void createUserTest() {
        request
            .setName(faker.hitchhikersGuideToTheGalaxy().character())
            .setJob(faker.hitchhikersGuideToTheGalaxy().location());

        response = given(requestSpec)
            .body(request)
            .when()
            .post("/users")
            .then()
            .spec(postResponseSpec)
            .extract().as(PostUserResponseBodyModel.class);

        assertThat(response.getName()).isEqualTo(request.getName());
        assertThat(response.getJob()).isEqualTo(request.getJob());
        assertThat(response.getCreatedAt()).isGreaterThanOrEqualTo(dateTime);
    }

    @Test
    void createUserWithoutNameTest() {
        request
            .setJob(faker.dune().title());

        response = given(requestSpec)
            .body(request)
            .when()
            .post("/users")
            .then()
            .spec(postResponseSpec)
            .extract().as(PostUserResponseBodyModel.class);

        assertThat(response.getName()).isEqualTo(request.getName());
        assertThat(response.getJob()).isEqualTo(request.getJob());
        assertThat(response.getCreatedAt()).isGreaterThanOrEqualTo(dateTime);
    }

    @Test
    void createUserWithoutJobTest() {
        request
            .setName(faker.lordOfTheRings().character());

        response = given(requestSpec)
            .body(request)
            .when()
            .post("/users")
            .then()
            .spec(postResponseSpec)
            .extract().as(PostUserResponseBodyModel.class);

        assertThat(response.getName()).isEqualTo(request.getName());
        assertThat(response.getJob()).isEqualTo(request.getJob());
        assertThat(response.getCreatedAt()).isGreaterThanOrEqualTo(dateTime);
    }

    @Test
    void createEmptyUserTest() {
        response = given(requestSpec)
            .body(request)
            .when()
            .post("/users")
            .then()
            .spec(postResponseSpec)
            .extract().as(PostUserResponseBodyModel.class);

        assertThat(response.getName()).isEqualTo(request.getName());
        assertThat(response.getJob()).isEqualTo(request.getJob());
        assertThat(response.getCreatedAt()).isGreaterThanOrEqualTo(dateTime);
    }
}
