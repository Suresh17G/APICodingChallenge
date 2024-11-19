package com.hexaware.CodingChallenge.Exception;

public class TaskNotFoundException extends Exception {
    public TaskNotFoundException(String msg) {
        super(msg);
    }

    @Override
    public String getMessage() {
        return "TaskNotFoundException: " + super.getMessage();
    }
}
