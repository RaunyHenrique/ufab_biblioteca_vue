package com.equipeRL.backend.Controllers.resources;

import com.equipeRL.backend.DefaultTest;
import com.equipeRL.backend.Controllers.TestControllerInterface;
import com.equipeRL.backend.Models.Orientador;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class OrientadoresControllerTest extends DefaultTest implements TestControllerInterface {

    @Test
    public void listAll() {
        given()
                .when()
                .get("/orientadores")
                .then()
                .statusCode(HttpStatus.SC_OK).log().all();
    }

    @Test
    public void createInvalid() {

        given()
                .contentType("application/json")
                .body(new Orientador())
                .when()
                .post("/orientadores")
                .then()
                .statusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY);

    }

    @Test
    public void updateInvalid() {

        given()
                .contentType("application/json")
                .body(new Orientador())
                .when()
                .put("/orientadores/1")
                .then()
                .statusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY);

    }

    @Test
    public void deleteNotFound() {

        given().
                when().
                delete("/orientadores/9999").
                then().
                statusCode(HttpStatus.SC_NOT_FOUND);

    }

}
