package guru.qa.tests;

import guru.qa.models.GetUserResponseModel;
import org.junit.jupiter.api.Test;

import static guru.qa.helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;


public class GetUsersTest {

    @Test
    void getExistingUserTest() {
        GetUserResponseModel response = given()
            .filter(withCustomTemplates())
            .log().uri()
            .when()
            .get("https://reqres.in/api/users/1")
            .then()
            .log().status()
            .log().body()
            .statusCode(200)
            .extract().body().as(GetUserResponseModel.class);

        assertThat(response.getData().getId()).isEqualTo(1);
        assertThat(response.getData().getEmail()).isEqualTo("george.bluth@reqres.in");
        assertThat(response.getData().getFirstName()).isEqualTo("George");
        assertThat(response.getData().getLastName()).isEqualTo("Bluth");
        assertThat(response.getData().getAvatar()).isEqualTo("https://reqres.in/img/faces/1-image.jpg");
    }

    @Test
    void getNonExistingUserTest() {
        GetUserResponseModel response = given()
            .filter(withCustomTemplates())
            .log().uri()
            .when()
            .get("https://reqres.in/api/users/1312")
            .then()
            .log().status()
            .log().body()
            .statusCode(404)
            .extract().body().as(GetUserResponseModel.class);

        assertThat(response).isEqualTo(new GetUserResponseModel());
    }
}
