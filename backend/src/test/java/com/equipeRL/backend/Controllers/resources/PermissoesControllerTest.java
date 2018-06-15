package com.equipeRL.backend.Controllers.resources;

import com.equipeRL.backend.DefaultTest;
import com.equipeRL.backend.Controllers.TestControllerInterface;
import com.equipeRL.backend.Models.Permissao;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PermissoesControllerTest extends DefaultTest implements TestControllerInterface {

    @Test
    public void listAll() {
        given()
                .when()
                .get("/permissoes")
                .then()
                .statusCode(HttpStatus.SC_OK).log().all();
    }

    @Test
    public void createInvalid() {

        given()
                .contentType("application/json")
                .body(new Permissao())
                .when()
                .post("/permissoes")
                .then()
                .statusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY);

    }

    @Test
    public void updateInvalid() {

        given()
                .contentType("application/json")
                .body(new Permissao())
                .when()
                .put("/permissoes/1")
                .then()
                .statusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY);

    }

    @Test
    public void deleteNotFound() {

        given().
                when().
                delete("/permissoes/9999").
                then().
                statusCode(HttpStatus.SC_NOT_FOUND);

    }

}
