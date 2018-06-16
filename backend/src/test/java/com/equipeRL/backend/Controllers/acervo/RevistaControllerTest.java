package com.equipeRL.backend.Controllers.acervo;

import com.equipeRL.backend.Controllers.TestControllerInterface;
import com.equipeRL.backend.DefaultTest;
import com.equipeRL.backend.Models.acervo.Revista;

import org.apache.http.HttpStatus;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RevistaControllerTest extends DefaultTest implements TestControllerInterface {

    @Test
    public void listAll() {
        given()
                .when()
                .get("/revistas")
                .then()
                .statusCode(HttpStatus.SC_OK).log().all();
    }

    @Test
    public void createInvalid() {

        given()
                .contentType("application/json")
                .body(new Revista())
                .when()
                .post("/revistas")
                .then()
                .statusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY);

    }

    @Test
    public void updateInvalid() {

        given()
                .contentType("application/json")
                .body(new Revista())
                .when()
                .put("/revistas/1")
                .then()
                .statusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY);

    }

    @Test
    public void deleteNotFound() {

        given().
                when().
                delete("/revistas/9999").
                then().
                statusCode(HttpStatus.SC_NOT_FOUND);

    }

}
