package mateuszteam.final_project.controller;

import io.restassured.path.json.JsonPath;


import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;
import static mateuszteam.final_project.controller.MoviesUrl.BASE_URL;

public class GetAllMoviesRequest {

    public static JsonPath getAllMovies() {

        return given()
                .when()
                .get(BASE_URL + "/all")
                .then()
                .statusCode(SC_NOT_FOUND)
                .extract()
                .response()
                .jsonPath();
    }
}
