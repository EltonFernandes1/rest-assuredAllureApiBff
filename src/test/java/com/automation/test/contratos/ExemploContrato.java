package com.automation.test.contratos;

import org.apache.http.HttpStatus;
import org.junit.Test;
import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ExemploContrato {

    @Test
    public void exemploContratoValidar() {

        when()
                .get("https://petstore.swagger.io/v2/store/order/10")
                .then().statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchemaInClasspath("contracts/exemplo-schema.json"));
    }
}