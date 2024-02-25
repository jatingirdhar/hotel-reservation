package com.org.hotel.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.org.hotel.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> invalidDataExpHandler(MethodArgumentNotValidException e) {
        ErrorResponse err = new ErrorResponse();
        err.setTimestamp(LocalDateTime.now());
        err.setMessage("Validation Error");
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> errors = fieldErrors.stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        err.setErrorList(errors);
        return new ResponseEntity<ErrorResponse>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setMessage("Validation Error");
        if (ex.getCause() instanceof InvalidFormatException) {
            InvalidFormatException invalidFormatException = (InvalidFormatException) ex.getCause();
            String fieldName = invalidFormatException.getPath().stream()
                    .map(JsonMappingException.Reference::getFieldName).collect(Collectors.joining("."));
            errorResponse.setError("Invalid value for field: " + fieldName);
        } else {
            errorResponse.setError("Malformed JSON request");
        }
        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception e, WebRequest req) {
        ErrorResponse err = new ErrorResponse(LocalDateTime.now(), e.getMessage(), null, null);
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}

