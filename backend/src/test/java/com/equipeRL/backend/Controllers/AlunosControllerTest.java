package com.equipeRL.backend.Controllers;

import com.equipeRL.backend.DefaultTest;
import com.equipeRL.backend.Models.Permissao;
import com.equipeRL.backend.Models.Usuario;
import io.restassured.authentication.FormAuthConfig;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class AlunosControllerTest extends DefaultTest {

    @Test
    public void getAllAlunos() {

        //fazer login
//        Set<Permissao> permissoes = new HashSet<>();
//        Permissao p = new Permissao();
//        p.setId(1L);
//        p.setNome("ROLE_ADMIN");
//        permissoes.add(p);
//
//        Usuario user = new Usuario("5465465", "dasdasdasd", "5454545", "dasdasdad", "dasdasdasd", "545646", "admin@email.com", "admin", permissoes);

//        given()
//                .contentType("application/json")
//                .body(user)
//                .when().post("/usuarios").then()
//                .statusCode(HttpStatus.SC_OK).log();

        given()
                .when().get("http://localhost:8080/api/usuarios")
                .then().statusCode(HttpStatus.SC_OK).log().all();

    }

}
