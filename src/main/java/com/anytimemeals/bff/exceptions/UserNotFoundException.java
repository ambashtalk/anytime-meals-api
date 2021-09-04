package com.anytimemeals.bff.exceptions;

public class UserNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 10001;

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
