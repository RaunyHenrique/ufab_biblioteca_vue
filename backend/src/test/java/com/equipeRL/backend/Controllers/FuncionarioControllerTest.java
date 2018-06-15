package com.equipeRL.backend.Controllers;

import com.equipeRL.backend.DefaultTest;
import com.equipeRL.backend.Models.AreaConhecimento;
import com.equipeRL.backend.Models.Curso;
import com.equipeRL.backend.Models.Permissao;
import com.equipeRL.backend.Models.Usuario;
import com.equipeRL.backend.Models.enums.Tipo_curso;
import org.apache.http.HttpStatus;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class FuncionarioControllerTest extends DefaultTest implements TestControllerInterface {

    @Test
    public void listAll() {
        given()
                .when()
                .get("/usuarios")
                .then()
                .statusCode(HttpStatus.SC_OK).log().all();
    }

    @Test
    public void createInvalid() {

        Set<Permissao> permissoes = new HashSet<>();

        Usuario usuario = new Usuario("54645645645", "dasdasddsdadd", "4564564564", "dasdasddsaddss", "dasdasdasdasdad", "4654646", "admin@emaill.com", "123456", );

        given()
                .contentType("application/json")
                .body(usuario)
                .when()
                .post("/usuarios")
                .then()
                .statusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY);

    }

    @Test
    public void updateInvalid() {

    }

    @Test
    public void deleteNotFound() {

    }

}
