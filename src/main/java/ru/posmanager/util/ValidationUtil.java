package ru.posmanager.util;

import lombok.experimental.UtilityClass;
import ru.posmanager.HasId;
import ru.posmanager.exception.IllegalRequestDataException;
import ru.posmanager.exception.NotFoundException;

@UtilityClass
public class ValidationUtil {

    public static void checkNew(HasId bean) {
        if (bean.getId() != null) {
            throw new IllegalArgumentException(bean.getClass().getSimpleName() + ": the id must be null!");
        }
    }

    public static void assureIdConsistent(HasId bean, int id) {
        if (bean.isNew()) {
            bean.setId(id);
        } else if (bean.id() != id) {
            throw new IllegalRequestDataException(bean + " must be with id=" + id);
        }
    }

    public static HasId checkNotFoundWithId(HasId bean, Class<?> clazz, int id) {
        checkNotFoundWithId(bean != null, clazz, id);
        return bean;
    }

    public static void checkNotFoundWithId(boolean found, Class<?> clazz, int id) {
        if (!found) {
            throw new NotFoundException(clazz, id);
        }
    }
}