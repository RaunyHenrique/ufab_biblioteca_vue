package com.equipeRL.backend.Controllers;

import com.equipeRL.backend.DefaultTest;
import org.apache.http.HttpStatus;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class AlunosControllerTest extends DefaultTest {

//    @Test
//    public void login() {
//
//        //fazer login
//        Map<String,String> user = new HashMap<>();
//        user.put("username", "admin@email.com");
//        user.put("password", "admin");
//
//        given()
//                .contentType("application/json")
//                .body(user)
//                .when().post("api/login").then()
//                .statusCode(HttpStatus.SC_OK)
//                .body("empty", equalTo(false));
//
//    }

//    @Test
//    public void getAllAlunos() {
//
//        when()
//                .get("/alunos")
//                .then()
//                .statusCode(HttpStatus.SC_OK);
//
//    }

}
