package ru.posmanager.web.validator;

import java.util.Map;

public interface UniqueDTO {
    Map<UniqueDataType, Object> resolveType();
}
