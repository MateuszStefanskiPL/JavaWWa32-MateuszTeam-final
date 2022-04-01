package mateuszteam.final_project.controller;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;

class MoviesRestControllerTest {

    @Test
    public void should_get_movie_by_id() {
        get("http://localhost:8082/movies/1")
                .then()
                .statusCode(200)
                .assertThat()
                .body("title", equalTo("A Nightmare on Elm Street"));
    }

}
