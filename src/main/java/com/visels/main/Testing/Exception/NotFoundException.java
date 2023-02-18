package com.visels.main.Testing.Exception;

public class NotFoundException extends Exception{

    private String message;
    private int status;

    public NotFoundException(String message, int status) {
        this.message = message;
        this.status = status;
    }
}
