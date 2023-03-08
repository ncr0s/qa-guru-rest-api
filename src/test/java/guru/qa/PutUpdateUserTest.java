package guru.qa;

import com.github.javafaker.Faker;
import guru.qa.models.UserRequestBody;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class PutUpdateUserTest {
    Faker faker = new Faker();

    @Test
    void updateUsersNameAndJobTest() {
        UserRequestBody body = new UserRequestBody()
            .setName(faker.hitchhikersGuideToTheGalaxy().character())
            .setJob(faker.hitchhikersGuideToTheGalaxy().location());

        given()
            .log().uri()
            .log().body()
            .contentType(JSON)
            .body(body)
            .when()
            .put("https://reqres.in/api/users/")
            .then()
            .log().status()
            .log().body()
            .statusCode(200)
            .body("name", Matchers.is(body.getName()))
            .body("job", Matchers.is(body.getJob()));
    }

    @Test
    void updateUsersJobTest() {
        UserRequestBody body = new UserRequestBody()
            .setJob(faker.dune().title());

        given()
            .log().uri()
            .log().body()
            .contentType(JSON)
            .body(body)
            .when()
            .put("https://reqres.in/api/users/")
            .then()
            .log().status()
            .log().body()
            .statusCode(200)
            .body("name", Matchers.is(body.getName()))
            .body("job", Matchers.is(body.getJob()));
    }

    @Test
    void updateUsersNameTest() {
        UserRequestBody body = new UserRequestBody()
            .setName(faker.lordOfTheRings().character());

        given()
            .log().uri()
            .log().body()
            .contentType(JSON)
            .body(body)
            .when()
            .put("https://reqres.in/api/users/")
            .then()
            .log().status()
            .log().body()
            .statusCode(200)
            .body("name", Matchers.is(body.getName()))
            .body("job", Matchers.is(body.getJob()));
    }

    @Test
    void createEmptyUserTest() {
        UserRequestBody body = new UserRequestBody();

        given()
            .log().uri()
            .log().body()
            .contentType(JSON)
            .body(body)
            .when()
            .put("https://reqres.in/api/users/")
            .then()
            .log().status()
            .log().body()
            .statusCode(200)
            .body("name", Matchers.is(body.getName()))
            .body("job", Matchers.is(body.getJob()));
    }
}
