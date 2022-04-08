package com.example.g3s.exception;

import com.example.g3s.model.APIError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({QuantityNotValidException.class})
    ResponseEntity<?> notValidExceptionHandler(Exception e, ServletWebRequest request){
        APIError apiError = new APIError();
        apiError.setStatus(HttpStatus.BAD_REQUEST);
        apiError.setCode("400");
        apiError.setTitle(e.getMessage());
        apiError.setSource(request.getDescription(true));
        return new ResponseEntity(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({StockNotFoundException.class})
    ResponseEntity<?> notFoundExceptionHandler(Exception e, ServletWebRequest request){
        APIError apiError = new APIError();
        apiError.setStatus(HttpStatus.NOT_FOUND);
        apiError.setCode("404");
        apiError.setTitle(e.getMessage());
        apiError.setSource(request.getDescription(true));
        return new ResponseEntity(apiError, new HttpHeaders(), apiError.getStatus());
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        FieldError fieldError = ex.getBindingResult().getFieldError();
        APIError apiError = new APIError();
        apiError.setStatus(HttpStatus.BAD_REQUEST);
        apiError.setSource(request.getDescription(true));
        apiError.setTitle(fieldError.getDefaultMessage());
        apiError.setCode("400");

        return new ResponseEntity(apiError, headers, apiError.getStatus());
    }
}