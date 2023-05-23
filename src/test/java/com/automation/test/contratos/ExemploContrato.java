package com.automation.test.contratos;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ExemploContrato {

    @Test
    @org.junit.jupiter.api.Test
    @Story("teste contrato")
    @Tag("RegressiveBff")
    @Description("Description test")
    public void exemploContratoValidar() {

        when()
                .get("https://petstore.swagger.io/v2/store/order/10")
                .then().statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchemaInClasspath("contracts/exemplo-schema.json"));
        Allure.step("Validar contrato ");
    }
}