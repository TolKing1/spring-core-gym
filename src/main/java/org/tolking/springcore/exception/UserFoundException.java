package org.tolking.springcore.exception;

public class UserFoundException extends RuntimeException{
    public UserFoundException(Long id) {
        super("User with id = %s already exists");
    }
}
