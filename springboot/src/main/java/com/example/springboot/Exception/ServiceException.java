package com.example.springboot.Exception;

public class ServiceException extends RuntimeException{

    public ServiceException(String message) {
        super(message);
    }
}
