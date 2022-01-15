package ru.posmanager.util;

import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class StringUtil {
    public static String getEmptyIfNull(String string) {
        return Objects.isNull(string) ? "" : string;
    }
}
