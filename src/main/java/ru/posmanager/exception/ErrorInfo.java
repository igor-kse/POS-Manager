package ru.posmanager.exception;

import lombok.Getter;

@Getter
public class ErrorInfo {
    private final String url;
    private final ErrorType errorType;
    private final String typeMessage;
    private final String[] details;

    public ErrorInfo(String url, ErrorType errorType, String typeMessage, String[] details) {
        this.url = url;
        this.errorType = errorType;
        this.typeMessage = typeMessage;
        this.details = details;
    }
}
