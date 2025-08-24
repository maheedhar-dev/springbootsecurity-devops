package com.devops.springsecuritydevops.exception;

public class BusinessException extends  RuntimeException{
   public BusinessException(String message){
        super(message);
    }
}
