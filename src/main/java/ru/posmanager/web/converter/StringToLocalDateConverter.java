package ru.posmanager.web.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

@Component
public class StringToLocalDateConverter implements Converter<String, LocalDate> {

    @Override
    public @Nullable LocalDate convert(@Nullable String source) {
        return StringUtils.hasLength(source) ? LocalDate.parse(source) : null;
    }
}
