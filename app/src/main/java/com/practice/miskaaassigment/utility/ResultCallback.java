package com.practice.miskaaassigment.utility;

public interface ResultCallback<T>{
    void result(ApiResponse<T> response);
}
