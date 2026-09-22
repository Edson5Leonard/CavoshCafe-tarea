package com.example.demo.dto;

import java.time.LocalDateTime;

public class ApiResponse<T> {
    private int status;
    private boolean success;
    private String message;
    private T data;
    private Object error;
    private LocalDateTime timestamp;

    public ApiResponse(int status, boolean success, String message, T data, Object error) {
        this.status = status;
        this.success = success;
        this.message = message;
        this.data = data;
        this.error = error;
        this.timestamp = LocalDateTime.now();
    }

    
    
    
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(200, true, message, data, null);
    }

    
    public static <T> ApiResponse<T> created(String message, T data) {
        return new ApiResponse<>(201, true, message, data, null);
    }

    
    public static <T> ApiResponse<T> error(int status, String message, Object errorDetail) {
        return new ApiResponse<>(status, false, message, null, errorDetail);
    }

   
    public int getStatus() { return status; }
    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public T getData() { return data; }
    public Object getError() { return error; }
    public LocalDateTime getTimestamp() { return timestamp; }
}