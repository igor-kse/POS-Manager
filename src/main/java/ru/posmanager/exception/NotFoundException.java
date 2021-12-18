package ru.posmanager.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

    private final String className;
    private final int id;

    public NotFoundException(Class<?> clazz, int id) {
        super("Not found " + clazz.getSimpleName() + " with id " + id);
        this.className = clazz.getSimpleName();
        this.id = id;
    }
}