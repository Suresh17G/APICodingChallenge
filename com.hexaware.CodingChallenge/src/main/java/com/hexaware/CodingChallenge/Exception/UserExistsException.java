package com.hexaware.CodingChallenge.Exception;

public class UserExistsException extends Exception {
    public UserExistsException(String msg) {
        super(msg);
    }

    @Override
    public String getMessage() {
        return "UserExistsException: " + super.getMessage();
    }
}
