package ru.posmanager.exception;

public class TimeExpiredException extends RuntimeException {

    public TimeExpiredException(String message) {
        super(message);
    }
}
