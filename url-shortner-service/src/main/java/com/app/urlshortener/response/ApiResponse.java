package com.app.urlshortener.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {
    private String status;
    private String message;
    private Object result;

    public ApiResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public ApiResponse(String status, String message, Object result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }

    @Override
    public String toString() {
        return "ApiResponse [STATUS=" + status + ", MESSAGE=" + message + "]";
    }
}