package com.equipeRL.backend;

import com.sun.javafx.collections.MappingChange;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendApplication.class)
public class DefaultTest {

    private String basePath = "/api";

    String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ7XCJlbWFpbFwiOlwiYWRtaW5AZW1haWwuY29tXCIsXCJhdXRoXCI6W3tcImF1dGhvcml0eVwiOlwiUk9MRV9BRE1JTlwifV19IiwiZXhwIjoxNTI5OTQ3ODE1fQ.eROz0mFaWvNo6PgCc6nfSxSl0W6teXNXMz3Ys-0RWS0sG4OBteZ66jqF_BU3RLLM-keHJlB0mNBMcG9N-nlctA";

    @Before
    public void setup() {

        //URI
        RestAssured.baseURI = "http://localhost";

        //BASE PATH.. prefix das url's
        RestAssured.basePath = basePath;

        //PORTA
        RestAssured.port = 8080;//8080?

        //form("admin@email.com", "admin")
        RestAssured.authentication = oauth2(token);

        System.out.println("CONFIGURAÇÃO BASE DOS TESTES: " + RestAssured.baseURI + " " + RestAssured.port + " " + RestAssured.basePath);

    }

}
