package com.equipeRL.backend.Controllers.acervo;

import com.equipeRL.backend.DefaultTest;
import com.equipeRL.backend.Controllers.TestControllerInterface;
import com.equipeRL.backend.Models.AreaConhecimento;
import com.equipeRL.backend.Models.Curso;
import com.equipeRL.backend.Models.acervo.Tcc;
import com.equipeRL.backend.Models.enums.Tipo_curso;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TccControllerTest extends DefaultTest implements TestControllerInterface {

    @Test
    public void listAll() {
        given()
                .when()
                .get("/tccs")
                .then()
                .statusCode(HttpStatus.SC_OK).log().all();
    }

    @Test
    public void createInvalid() {

        given()
                .contentType("application/json")
                .body(new Tcc())
                .when()
                .post("/tccs")
                .then()
                .statusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY);

    }

    @Test
    public void updateInvalid() {

        given()
                .contentType("application/json")
                .body(new Tcc())
                .when()
                .put("/tccs/1")
                .then()
                .statusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY);

    }

    @Test
    public void deleteNotFound() {

        given().
                when().
                delete("/tccs/9999").
                then().
                statusCode(HttpStatus.SC_NOT_FOUND);

    }

}
