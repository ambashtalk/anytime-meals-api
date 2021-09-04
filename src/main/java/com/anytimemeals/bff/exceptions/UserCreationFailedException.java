package com.anytimemeals.bff.exceptions;

public class UserCreationFailedException extends RuntimeException{
    private static final long serialVersionUID = 10003;

    public UserCreationFailedException() {
    }

    public UserCreationFailedException(String errorMessage) {
        super(errorMessage);
    }
}
