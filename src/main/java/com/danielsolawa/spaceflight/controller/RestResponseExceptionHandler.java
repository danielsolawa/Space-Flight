package com.danielsolawa.spaceflight.controller;

import com.danielsolawa.spaceflight.dto.ErrorInfoDto;
import com.danielsolawa.spaceflight.exception.AlreadyAddedException;
import com.danielsolawa.spaceflight.exception.LimitExceededException;
import com.danielsolawa.spaceflight.exception.NotFoundException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(Exception exception, WebRequest request){
        return new ResponseEntity<>(ErrorInfoDto.builder()
                                                .message(exception.getMessage())
                                                .status(HttpStatus.NOT_FOUND.value()).build(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({LimitExceededException.class})
    public ResponseEntity<Object> handleLimitExceededException(Exception exception, WebRequest request){
        return new ResponseEntity<>(ErrorInfoDto.builder()
                                                .message(exception.getMessage())
                                                .status(HttpStatus.BAD_REQUEST.value()).build(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler({AlreadyAddedException.class})
    public ResponseEntity<Object> handleAlreadyAddedException(Exception exception, WebRequest request){
        return new ResponseEntity<>(ErrorInfoDto.builder()
                .message(exception.getMessage())
                .status(HttpStatus.CONFLICT.value()).build(),
                new HttpHeaders(), HttpStatus.CONFLICT);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
         return new ResponseEntity<>(ErrorInfoDto.builder()
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value()).build(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NumberFormatException.class})
    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(ErrorInfoDto.builder()
                                                .message(ex.getMessage())
                                                .status(HttpStatus.BAD_REQUEST.value()).build(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
