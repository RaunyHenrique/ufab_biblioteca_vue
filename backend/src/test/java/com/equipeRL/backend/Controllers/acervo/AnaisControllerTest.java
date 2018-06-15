package com.equipeRL.backend.Controllers.acervo;

import com.equipeRL.backend.DefaultTest;
import com.equipeRL.backend.Controllers.TestControllerInterface;
import com.equipeRL.backend.Models.acervo.Anal;

import org.apache.http.HttpStatus;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class AnaisControllerTest extends DefaultTest implements TestControllerInterface {
	
    @Test
    public void listAll() {
        given()
                .when()
                .get("/anais")
                .then()
                .statusCode(HttpStatus.SC_OK).log().all();
    }

    @Test
    public void createInvalid() {

        given()
                .contentType("application/json")
                .body(new Anal())
                .when()
                .post("/anais")
                .then()
                .statusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY);

    }

    @Test
    public void updateInvalid() {

        given()
                .contentType("application/json")
                .body(new Anal())
                .when()
                .put("/anais/1")
                .then()
                .statusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY);

    }

    @Test
    public void deleteNotFound() {

        given().
                when().
                delete("/anais/9999").
                then().
                statusCode(HttpStatus.SC_NOT_FOUND);

    }

}
