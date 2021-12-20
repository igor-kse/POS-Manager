package ru.posmanager.util;

import lombok.experimental.UtilityClass;
import org.springframework.util.StringUtils;

import java.util.Map;

@UtilityClass
public class CollectionUtil {
    public static <K, V> void addIfNotEmpty(V value, K key, Map<K, V> map) {
        if (value != null && StringUtils.hasLength(value.toString())) {
            map.put(key, value);
        }
    }

    @SafeVarargs
    public static <K, V> void addAllIfNotEmpty(Map<K, V> source, Map<K, V> destination, K... keys) {
        for (K key : keys) {
            addIfNotEmpty(source.get(key), key, destination);
        }
    }
}
