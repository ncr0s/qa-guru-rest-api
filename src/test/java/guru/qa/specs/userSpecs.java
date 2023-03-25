package guru.qa.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static guru.qa.helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.notNullValue;

public class userSpecs {
    public static RequestSpecification requestSpec = with()
        .filter(withCustomTemplates())
        .log().uri()
        .log().body()
        .contentType(JSON)
        .baseUri("https://reqres.in")
        .basePath("/api");

    public static ResponseSpecification postResponseSpec = new ResponseSpecBuilder()
        .log(STATUS)
        .log(BODY)
        .expectStatusCode(201)
        .expectBody("createdAt", notNullValue())
        .build();

    public static ResponseSpecification putResponseSpec = new ResponseSpecBuilder()
        .log(STATUS)
        .log(BODY)
        .expectStatusCode(200)
        .expectBody("updatedAt", notNullValue())
        .build();

    public static ResponseSpecification getResponseSpec = new ResponseSpecBuilder()
        .log(STATUS)
        .log(BODY)
        .build();
}
