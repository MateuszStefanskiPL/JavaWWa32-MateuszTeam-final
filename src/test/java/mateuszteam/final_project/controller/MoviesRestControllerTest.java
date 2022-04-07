package mateuszteam.final_project.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;

@Disabled
class MoviesRestControllerTest {

    @Test
    public void should_get_first_movie_by_id() {
        get("http://localhost:8082/movies/1")
                .then()
                .statusCode(200)
                .assertThat()
                .body("title", equalTo("A Nightmare on Elm Street"))
                .body("director", equalTo("Wes Craven"))
                .body("movieId", equalTo(1));
    }

    @Test
    public void should_get_second_movie_by_id() {
        get("http://localhost:8082/movies/2")
                .then()
                .statusCode(200)
                .assertThat()
                .body("title", equalTo("Sing 2"))
                .body("director", equalTo("Garth Jennings"))
                .body("movieId", equalTo(2));
    }

}
