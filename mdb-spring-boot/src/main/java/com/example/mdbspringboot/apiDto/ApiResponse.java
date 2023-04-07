package com.example.mdbspringboot.apiDto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ApiResponse {
    private String message;
    private String status;
    private HttpStatus httpStatus;
    private Integer statusCode;
    private Object result;
    private List<String> errors;

    public ApiResponse(Integer statusCode, String message, HttpStatus httpStatus) {
        super();
        this.statusCode = statusCode;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public ApiResponse(Integer statusCode, String message, HttpStatus httpStatus, Object result) {
        super();
        this.statusCode = statusCode;
        this.message = message;
        this.httpStatus = httpStatus;
        this.result = result;
    }

    public ApiResponse(String message, String status, HttpStatus httpStatus, Integer statusCode) {
        super();
        this.message = message;
        this.status = status;
        this.httpStatus = httpStatus;
        this.statusCode = statusCode;
    }
    public ApiResponse(String status, HttpStatus httpStatus, Object result) {
        super();
        this.status = status;
        this.httpStatus = httpStatus;
        this.result = result;
    }

    public ApiResponse(String status, Integer statusCode, Object result) {
        super();
        this.status = status;
        this.statusCode = statusCode;
        this.result = result;
    }
}
