package br.com.catalog.exception;

import java.util.function.Supplier;

public class CategoryNotFoundException extends ResourceNotFoundException {

    public CategoryNotFoundException(String message) {
        super(message);
    }

    public CategoryNotFoundException() {
        super();
    }

}
