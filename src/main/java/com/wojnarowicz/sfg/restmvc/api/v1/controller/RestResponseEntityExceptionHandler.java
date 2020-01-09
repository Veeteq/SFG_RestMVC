package com.wojnarowicz.sfg.restmvc.api.v1.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.wojnarowicz.sfg.restmvc.api.v1.model.ErrorDTO;
import com.wojnarowicz.sfg.restmvc.exception.ResourceNotFoundException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleNotFoundException(Exception exception, WebRequest webRequest) {
        ErrorDTO errorDTO = new ErrorDTO(exception.getLocalizedMessage(), HttpStatus.NOT_FOUND.name());
        return new ResponseEntity<ErrorDTO>(errorDTO, new HttpHeaders(),  HttpStatus.NOT_FOUND);
    }
}
