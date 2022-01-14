package ru.posmanager.domain.device;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum DeviceType {
    ATM("ATM"),
    TERMINAL("TERMINAL");

    private static final Map<String, DeviceType> FORMAT_MAP = Stream
            .of(DeviceType.values())
            .collect(Collectors.toMap(s -> s.formatted, Function.identity()));

    private final String formatted;

    DeviceType(String formatted) {
        this.formatted = formatted;
    }

    @JsonCreator
    public static DeviceType fromString(String string) {
        return Optional
                .ofNullable(FORMAT_MAP.get(string))
                .orElseThrow(() -> new IllegalArgumentException(string));
    }
}
