package ru.posmanager.util;

import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class StringUtil {
    public static String makeEmptyIfNull(String string) {
        return Objects.isNull(string) ? "" : string;
    }
}
