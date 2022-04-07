package mateuszteam.final_project.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;

@Disabled
class AddressRestControllerTest {

    @Test
    void should_get_address_by_userid(){
        get("http://localhost:8082/addresses/1")
                .then()
                .statusCode(200)
                .assertThat()
                .body("fullName", equalTo("First user"));

    }

}
