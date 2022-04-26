package com.example.g3s.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Embedded;

import java.util.List;

@Data
@AllArgsConstructor
public class ErrorResponse {
    @Embedded
    private List<APIError> error;
}
