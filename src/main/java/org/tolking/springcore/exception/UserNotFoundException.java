package org.tolking.springcore.exception;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(Long id) {
        super("User with id = %d not found".formatted(id));
    }
}
