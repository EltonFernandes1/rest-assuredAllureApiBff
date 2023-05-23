package com.automation.infrastructure.config;


public enum HttpStatus {

    OK (200),
    NOTFOUND (404),
    CREATED (201);

    private int status;

    HttpStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}