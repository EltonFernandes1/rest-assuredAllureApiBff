package com.automation.test.apiService;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import com.automation.infrastructure.config.UrlEnvironment;
import com.automation.infrastructure.config.ValidStatusCode;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;


@DisplayName("Exemplo Execucao API")
@Slf4j
@Epic("Epic using the Parameterized Test")
@EnabledOnOs({OS.LINUX, OS.MAC, OS.WINDOWS})
public class PetStoreTest {
    
    @BeforeAll
    public static void setUp() {
        UrlEnvironment environment = UrlEnvironment.DEV;
        RestAssured.baseURI = environment.getBaseUrl();
    }

    @Test
    @Story("Id da historia")
    @DisplayName("Nome Display")
    @Tag("RegressiveApi")
    @Description("Description test")
    void testGetOrderById() {
        Response response = given()
                .contentType(ContentType.JSON)
                .get("/store/order/1");

        Allure.step("Verificar se a resposta tem status 200");
        ValidStatusCode statusCode = ValidStatusCode.OK;
        response.then().statusCode(statusCode.getStatusCode());

        Allure.step("Verificar se o ID do pedido é 1");
        response.then().body("id", equalTo(1));

        Allure.step("Verificar se o status do pedido é 'placed'");
        response.then().body("status", equalTo("placed"));


    }

    @Test
    @Description("Description Testing the GET")
    @DisplayName("Pet Id")
    @Story("Id da Historia")
    @Tag("RegressiveApi")
    @Tag("regression")
       void testCreateOrder() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", 1);
        requestBody.put("petId", 1);
        requestBody.put("quantity", 1);
        requestBody.put("shipDate", "2023-04-27T10:00:00.000Z");
        requestBody.put("status", "placed");
        requestBody.put("complete", true);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .post("/store/order");

        Allure.step("Verificar se a resposta tem status 200");
        ValidStatusCode statusCode = ValidStatusCode.OK;
        response.then().statusCode(statusCode.getStatusCode());
        Allure.step("Verificar se o ID do pedido é 1");
        response.then().body("id", equalTo(1));
        Allure.step("Verificar se o status do pedido é 'placed'");
        response.then().body("status", equalTo("placed"));
        Allure.step("validar contrato");
    }

    @Test
    @MethodSource("com.automation.provider.UserProvider#getUsers")
    @Story("Testing the user has been received")
    @Description("Testing the user has been received using ParameterizedTest")
    @DisplayName("Add new Pet Store")
    @Tag("RegressiveApi")
    @Tag("regression")
    public void testAddNewPet() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", 1);
        requestBody.put("name", "Rex");
        requestBody.put("status", "available");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .post("/pet");

        Allure.step("Verificar se a resposta tem status 200");
        ValidStatusCode statusCode = ValidStatusCode.OK;
        response.then().statusCode(statusCode.getStatusCode());

        Allure.step("Verificar se o ID do pet é 1");
        response.then().body("id", equalTo(1));

        Allure.step("Verificar se o nome do pet é 'Rex'");
        response.then().body("name", equalTo("Rex"));

        Allure.step("Verificar se o status do pet é 'available'");
        response.then().body("status", equalTo("available"));
    }

}
