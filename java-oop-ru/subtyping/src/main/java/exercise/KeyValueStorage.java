package exercise;

import java.util.Map;

interface KeyValueStorage {
    void set(String key, String value);
    void unset(String key);
    String get(String key, String defaultValue);
    default void clear() {

    }
    Map<String, String> toMap();
}
