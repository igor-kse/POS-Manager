package ru.posmanager.util.mappers;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class EntityManagerReferenceSupplier implements ReferenceSupplier<Integer> {
    private final EntityManager entityManager;

    public EntityManagerReferenceSupplier(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Object getReference(Integer id, Class<?> clazz) {
        return entityManager.getReference(clazz, id);
    }
}
