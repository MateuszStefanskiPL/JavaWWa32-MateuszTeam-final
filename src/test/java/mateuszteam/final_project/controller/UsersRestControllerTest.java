package mateuszteam.final_project.controller;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.post;
import static org.hamcrest.Matchers.equalTo;

class UsersRestControllerTest {



    @Test
    void should_register_new_user() {
     post("http://localhost:8082/users/register")
             .then()
             .statusCode(201)
             .body("email");
    }
}
