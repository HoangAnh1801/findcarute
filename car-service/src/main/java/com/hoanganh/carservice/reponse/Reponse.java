package com.hoanganh.carservice.reponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class Reponse {
    private HttpStatus status;
    private String message;
}
