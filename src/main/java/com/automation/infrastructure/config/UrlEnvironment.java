package com.automation.infrastructure.config;

public enum UrlEnvironment {
    DEV("https://petstore.swagger.io/v2"),
    QA("https://api.qa.example.com"),
    PROD("https://api.example.com");

    private final String baseUrl;

    UrlEnvironment(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
