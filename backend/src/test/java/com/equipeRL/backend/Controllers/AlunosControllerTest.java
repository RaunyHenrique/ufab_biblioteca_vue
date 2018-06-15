package com.equipeRL.backend.Controllers;

import com.equipeRL.backend.DefaultTest;
import com.equipeRL.backend.Models.*;
import com.equipeRL.backend.Models.enums.Tipo_nivel;
import org.apache.http.HttpStatus;
import org.junit.Test;

import java.sql.Date;
import java.util.*;

import static io.restassured.RestAssured.given;

public class AlunosControllerTest extends DefaultTest {

    @Test
    public void listAll() {
        given()
                .when()
                .get("/alunos")
                .then()
                .statusCode(HttpStatus.SC_OK).log().all();
    }

    @Test
    public void createInvalid() {

        Aluno aluno = new Aluno("54655646565", "546445646565",
                "sadasdasdsadasdasd", "adasdsasdddddaasss", "dasdssssssadasd", "asdasdasddsssadasdasd",
                "545465014401", new Curso(),
                Tipo_nivel.GRADUACAO, new Date(Calendar.getInstance().getTime().getTime()), 1);

        given()
                .contentType("application/json")
                .body(aluno)
                .when()
                .post("/alunos")
                .then()
                .statusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY);

    }

    @Test
    public void updateInvalid() {

        Aluno aluno = new Aluno("7777891201000", "546445646565",
                "sadasdasdsadasdasd", "adasdsasdddddaasss", "dasdssssssadasd", "asdasdasddsssadasdasd",
                "545465014401", new Curso(),
                Tipo_nivel.GRADUACAO, new Date(Calendar.getInstance().getTime().getTime()), 1);

        given()
                .contentType("application/json")
                .body(aluno)
                .when()
                .put("/alunos/1")
                .then()
                .statusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY);
    }

    @Test
    public void deleteNotFound() {
        given().
                when().
                delete("/alunos/9999").
                then().
                statusCode(HttpStatus.SC_NOT_FOUND);
    }

}
