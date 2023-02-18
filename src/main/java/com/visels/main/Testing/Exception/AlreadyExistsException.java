package com.visels.main.Testing.Exception;

public class AlreadyExistsException extends Exception {

    private String message;

    private int status;

    public AlreadyExistsException(String message, int status) {
        this.message = message;
        this.status = status;
    }
}
