package com.qipaishi.common;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private int code;
    private String msg;
    private T data;

    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.code = 200;
        response.msg = "success";
        response.data = data;
        return response;
    }

    public static ApiResponse<?> error(String message) {
        ApiResponse<?> response = new ApiResponse<>();
        response.code = 500;
        response.msg = message;
        return response;
    }
}
