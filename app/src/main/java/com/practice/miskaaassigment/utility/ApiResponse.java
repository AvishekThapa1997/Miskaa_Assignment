package com.practice.miskaaassigment.utility;

public class ApiResponse<T> {
    private final Status status;
    private T data;
    private String errorMessage;

    public ApiResponse(Status status, T data) {
        this.status = status;
        this.data = data;
    }

    public ApiResponse(Status status,T data,String errorMessage) {
        this.status = status;
        this.data = data;
        this.errorMessage = errorMessage;
    }

    public Status getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
