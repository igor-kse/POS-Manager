package ru.posmanager.util;

import lombok.experimental.UtilityClass;
import ru.posmanager.HasId;
import ru.posmanager.exception.IllegalRequestDataException;
import ru.posmanager.exception.NotFoundException;

@UtilityClass
public class ValidationUtil {

    public static void checkNew(HasId bean) {
        if (bean.getId() != null) {
            throw new IllegalArgumentException(bean.getClass().getSimpleName()+ ": the id must be null!");
        }
    }

    public static void assureIdConsistent(HasId bean, int id) {
        if (bean.isNew()) {
            bean.setId(id);
        } else if (bean.id() != id) {
            throw new IllegalRequestDataException(bean + " must be with id=" + id);
        }
    }

    public static  <T> T checkNotFound(T object, String message) {
        checkNotFound(object != null, message);
        return object;
    }

    public static void checkNotFound(boolean found, String message) {
        if (!found) {
            throw new NotFoundException(message);
        }
    }

    public static HasId checkNotFoundWithId(HasId bean, int id) {
        checkNotFound(bean != null, "id = " + id);
        return bean;
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id = " + id);
    }
}