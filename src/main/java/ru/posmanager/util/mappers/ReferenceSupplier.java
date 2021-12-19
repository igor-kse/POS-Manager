package ru.posmanager.util.mappers;

public interface ReferenceSupplier<ID> {

    Object getReference(ID id, Class<?> clazz);
}
