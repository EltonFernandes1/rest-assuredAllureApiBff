package com.automation.infrastructure.config;

public enum TestEnvironment {
    DEV("https://petstore.swagger.io/v2"),
    QA(""),
    PROD("");

    private final String baseUrl;

    TestEnvironment(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
