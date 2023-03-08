package guru.qa;

import com.github.javafaker.Faker;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class PostCreateTest {
    Faker faker = new Faker();

    @Test
    void createUserTest() {
        PostUsersRequestBody body = new PostUsersRequestBody()
            .setName(faker.hitchhikersGuideToTheGalaxy().character())
            .setJob(faker.hitchhikersGuideToTheGalaxy().location());

        given()
            .log().uri()
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

    @Test
    void createUserWithoutNameTest() {
        PostUsersRequestBody body = new PostUsersRequestBody()
            .setJob(faker.dune().title());

        given()
            .log().uri()
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

    @Test
    void createUserWithoutJobTest() {
        PostUsersRequestBody body = new PostUsersRequestBody()
            .setName(faker.lordOfTheRings().character());

        given()
            .log().uri()
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

    @Test
    void createEmptyUserTest() {
        PostUsersRequestBody body = new PostUsersRequestBody();

        given()
            .log().uri()
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
