package com.example.g3s.model;


import lombok.Data;
import org.springframework.http.HttpStatus;

import javax.persistence.Embedded;

@Data
public class APIError {

    private HttpStatus status;
    private String code;
    private String title;
    @Embedded
    private ErrorSource source;
}

