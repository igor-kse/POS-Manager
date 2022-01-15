package ru.posmanager.web.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static java.util.Objects.isNull;

@Slf4j
@Component
public class UniqueValidator implements org.springframework.validation.Validator {
    private final HttpServletRequest request;

    public UniqueValidator(@Nullable HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UniqueDTO.class.isAssignableFrom(clazz)
                || Map.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (target instanceof Map) {
            return;
        }

        Assert.notNull(request, "HttpServletRequest missed");
        Map<UniqueDataType, Object> data = ((UniqueDTO) target).resolveType();
        Integer savedId = data.get(UniqueDataType.SAVED_ID) != null ? (Integer) data.get(UniqueDataType.SAVED_ID) : null;

        // Handling update request: isn't there already saved value or is it updating itself
        if (request.getMethod().equals("PUT")) {
            if (isNull(savedId) || request.getRequestURI().endsWith(String.valueOf(savedId))) {
                return;
            }
        }
        // Handling create request: there must not be any saved entity with the value
        if (request.getMethod().equals("POST")) {
            if (isNull(savedId)) {
                return;
            }
        }
        errors.rejectValue((String) data.get(UniqueDataType.FIELD), (String) data.get(UniqueDataType.ERROR_CODE));
    }
}
