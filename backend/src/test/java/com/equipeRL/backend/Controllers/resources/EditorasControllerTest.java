package com.equipeRL.backend.Controllers.resources;

import com.equipeRL.backend.DefaultTest;
import com.equipeRL.backend.Controllers.TestControllerInterface;
import com.equipeRL.backend.Models.Editora;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class EditorasControllerTest extends DefaultTest implements TestControllerInterface {

    @Test
    public void listAll() {
        given()
                .when()
                .get("/editoras")
                .then()
                .statusCode(HttpStatus.SC_OK).log().all();
    }

    @Test
    public void createInvalid() {

        given()
                .contentType("application/json")
                .body(new Editora())
                .when()
                .post("/editoras")
                .then()
                .statusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY);

    }

    @Test
    public void updateInvalid() {

        given()
                .contentType("application/json")
                .body(new Editora())
                .when()
                .put("/editoras/1")
                .then()
                .statusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY);

    }

    @Test
    public void deleteNotFound() {

        given().
                when().
                delete("/editoras/9999").
                then().
                statusCode(HttpStatus.SC_NOT_FOUND);

    }

}
