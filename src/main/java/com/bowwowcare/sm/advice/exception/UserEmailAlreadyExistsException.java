package com.bowwowcare.sm.advice.exception;

public class UserEmailAlreadyExistsException extends RuntimeException{

    public UserEmailAlreadyExistsException() {
    }

    public UserEmailAlreadyExistsException(String message) {
        super(message);
    }

    public UserEmailAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
