package com.equipeRL.backend.Controllers;

import com.equipeRL.backend.DefaultTest;
import com.equipeRL.backend.Models.AreaConhecimento;
import com.equipeRL.backend.Models.Curso;
import com.equipeRL.backend.Models.enums.Tipo_curso;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CursoControllerTest extends DefaultTest implements TestControllerInterface {

    @Test
    public void listAll() {
        given()
                .when()
                .get("/cursos")
                .then()
                .statusCode(HttpStatus.SC_OK).log().all();
    }

    @Test
    public void createInvalid() {

        Curso curso = new Curso("dasdasdasd", "dasdasdasd", new AreaConhecimento(), Tipo_curso.GRADUACAO);

        given()
                .contentType("application/json")
                .body(curso)
                .when()
                .post("/cursos")
                .then()
                .statusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY);

    }

    @Test
    public void updateInvalid() {

        Curso curso = new Curso("dasdasdasd", "dasdasdasd", new AreaConhecimento(), Tipo_curso.GRADUACAO);

        given()
                .contentType("application/json")
                .body(curso)
                .when()
                .put("/cursos/1")
                .then()
                .statusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY);

    }

    @Test
    public void deleteNotFound() {

        given().
                when().
                delete("/cursos/9999").
                then().
                statusCode(HttpStatus.SC_NOT_FOUND);

    }

}
