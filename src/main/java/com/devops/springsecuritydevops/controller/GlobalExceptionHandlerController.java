package com.devops.springsecuritydevops.controller;

import com.devops.springsecuritydevops.exception.BusinessException;
import com.devops.springsecuritydevops.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandlerController {

@ExceptionHandler(BusinessException.class)
public ResponseEntity<ErrorMessage> handleGlobalException(BusinessException ex){
    ErrorMessage errorMessage = new ErrorMessage();
    errorMessage.setCode(HttpStatus.NO_CONTENT.toString());
    errorMessage.setMessage(ex.getMessage());
    return new ResponseEntity<>(errorMessage,HttpStatus.OK);
}
}
