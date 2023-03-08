package guru.qa;

import com.github.javafaker.Faker;
import guru.qa.models.UserRequestBody;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class PostCreateUserTest {
    Faker faker = new Faker();

    @Test
    void createUserTest() {
        UserRequestBody body = new UserRequestBody()
            .setName(faker.hitchhikersGuideToTheGalaxy().character())
            .setJob(faker.hitchhikersGuideToTheGalaxy().location());

        given()
            .log().uri()
            .log().body()
            .contentType(JSON)
            .body(body)
            .when()
            .post("https://reqres.in/api/users/")
            .then()
            .log().status()
            .log().body()
            .statusCode(201)
            .body("name", is(body.getName()))
            .body("job", is(body.getJob()));
    }

    @Test
    void createUserWithoutNameTest() {
        UserRequestBody body = new UserRequestBody()
            .setJob(faker.dune().title());

        given()
            .log().uri()
            .log().body()
            .contentType(JSON)
            .body(body)
            .when()
            .post("https://reqres.in/api/users/")
            .then()
            .log().status()
            .log().body()
            .statusCode(201)
            .body("name", is(body.getName()))
            .body("job", is(body.getJob()));
    }

    @Test
    void createUserWithoutJobTest() {
        UserRequestBody body = new UserRequestBody()
            .setName(faker.lordOfTheRings().character());

        given()
            .log().uri()
            .log().body()
            .contentType(JSON)
            .body(body)
            .when()
            .post("https://reqres.in/api/users/")
            .then()
            .log().status()
            .log().body()
            .statusCode(201)
            .body("name",is(body.getName()))
            .body("job", is(body.getJob()));
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
            .post("https://reqres.in/api/users/")
            .then()
            .log().status()
            .log().body()
            .statusCode(201)
            .body("name", Matchers.is(body.getName()))
            .body("job", Matchers.is(body.getJob()));
    }
}
