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

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class DefaultTest {

    private String basePath = "/api";

    @Before
    public void setup() {

        //URI
        RestAssured.baseURI = "http://localhost";

        //BASE PATH.. prefix das url's
        RestAssured.basePath = basePath;

        //PORTA
        RestAssured.port = 8080;//8080?

        System.out.println("CONFIGURAÇÃO BASE DOS TESTES: " + RestAssured.baseURI + " " + RestAssured.port + " " + RestAssured.basePath);

    }

}
