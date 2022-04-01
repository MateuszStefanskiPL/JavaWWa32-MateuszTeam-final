package mateuszteam.final_project.controller;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;
import static mateuszteam.final_project.controller.MoviesUrl.BASE_URL;
import static org.apache.http.HttpStatus.SC_OK;

public class GetAllMoviesRequest {

    public static JsonPath getAllMovies() {

        return given()
                .when()
                .get(BASE_URL + "/all")
                .then()
                .statusCode(SC_OK)
                .extract()
                .response()
                .jsonPath();
    }
}
