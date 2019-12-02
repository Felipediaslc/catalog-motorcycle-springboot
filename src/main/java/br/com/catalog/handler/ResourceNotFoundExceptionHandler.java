package br.com.catalog.handler;

import br.com.catalog.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class ResourceNotFoundExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException exception) {
        String message = exception.getMessage();

        HashMap<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("type", HttpStatus.NOT_FOUND);
        body.put("error", "Search not found");
        body.put("message", "Resource not found");

        return new ResponseEntity(body, HttpStatus.NOT_FOUND);
    }

}
