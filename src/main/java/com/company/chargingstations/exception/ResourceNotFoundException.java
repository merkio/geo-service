package com.company.chargingstations.exception;

import org.slf4j.helpers.MessageFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.Supplier;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Object... args) {
        super(MessageFormatter.arrayFormat(message, args).getMessage());
    }

    public static Supplier<ResourceNotFoundException> newResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        return () -> new ResourceNotFoundException("{} not found with {} : '{}'", resourceName, fieldName, fieldValue);
    }
}
