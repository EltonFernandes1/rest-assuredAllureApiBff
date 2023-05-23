package com.automation.test.bffService;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

@DisplayName("Exemplo de teste usando RestAssured, JUnit 5 e Allure Para testa BFF")
@Slf4j
@Epic("Epic using the Parameterized Test")
@EnabledOnOs({OS.LINUX, OS.MAC, OS.WINDOWS})
public class ExampleTest {

    private static final String BASE_URL = "https://bolsistabffservicosmobile.capes.gov.br";
    private static final String ENDPOINT_BOLSAS = "/bolsas/1234";
    private static final String PARAM_CPF = "cpf=30127797017";

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    @Story("Id da historia")
    @DisplayName("Name test Get OrderBy Id")
    @Tag("RegressiveBff")
    @Description("Description test")
    public void testResponseFields() {
        Response response = given()
                .contentType(ContentType.JSON)
                .param("nome", "teste")
                .param("page", 1)
                .param("size", 10)
                .get("/bancos");

        Allure.step("Verificar se a resposta tem status 401");
        response.then().statusCode(401);

        Allure.step("Verificar o conteúdo do corpo da resposta");
        response.then().body("title", equalTo("Não autorizado."))
                .body("detail", nullValue())
                .body("mensagem", equalTo("A requisição não contém autorização para ser processada (usuário não autenticado)"))
                .body("CODIGO", equalTo("0001"))
                .body("instance", nullValue())
                .body("type", equalTo("/refs/erros/0001"))
                .body("Status", equalTo(401));

    }
        @Test
        @Story("Aceitação")
        @DisplayName("Teste de acesso à API de Falha de bolsas")
        @Tag("RegressiveBff")
        @Description("Description test")
        public void validarRespostaGetBolsasComUsuarioNaoAutenticado() {

            // Realiza a requisição GET e armazena a resposta
            Response response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get(BASE_URL + "/bolsas/1234" + "?" + PARAM_CPF)
                    .then()
                    .extract()
                    .response();

            // Valida o código de status da resposta
            Assertions.assertEquals(401, response.getStatusCode());

            // Valida o corpo da resposta
            String responseBody = response.getBody().asString();
            String expectedResponseBody = "{\"title\":\"Não autorizado.\",\"detail\":null,\"mensagem\":\"A requisição não contém autorização para ser processada (usuário não autenticado)\",\"CODIGO\":\"0001\",\"instance\":null,\"type\":\"/refs/erros/0001\",\"Status\":401}";
            Assertions.assertEquals(expectedResponseBody, responseBody);
        }

    @Test
    @Story("Aceitação")
    @Tag("RegressiveBff")
    @Description("Description test")
    @DisplayName("Validar resposta com body inválido")
    public void testInvalidBody() {
        // Fazer a requisição POST com o body inválido
        Response response = given()
                .contentType(ContentType.JSON)
                .body("{\"idEnderecoCartao\": 1, \"idPassaporte\": 1, \"senha\": \"1234\"}")
                .when()
                .post("/bolsas/1/aceitacao?cpf=30127797017");

        // Validar a resposta esperada
        response.then()
                .statusCode(401)
                .body("title", equalTo("Não autorizado."))
                .body("detail", equalTo(null))
                .body("mensagem", equalTo("A requisição não contém autorização para ser processada (usuário não autenticado)"))
                .body("CODIGO", equalTo("0001"))
                .body("instance", equalTo(null))
                .body("type", equalTo("/refs/erros/0001"))
                .body("Status", equalTo(401));
    }

    }
