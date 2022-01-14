package ru.posmanager.domain.request;

public enum RequestStatus {
    NEW("NEW"),
    IN_PROGRESS("IN_PROGRESS"),
    SOLVED("SOLVED");

    private String value;

    RequestStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return this.value;
    }
}
