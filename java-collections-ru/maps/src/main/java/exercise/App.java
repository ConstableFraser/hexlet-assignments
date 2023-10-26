package exercise;

import java.util.Map;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Collections;

// BEGIN
class App {
    public static Map<String, Integer> getWordCount(String string) {
        List<String> words = Arrays.asList(string.split(" "));
        Map<String, Integer> map = new HashMap<>();
        if (string.isEmpty()) {
            return new HashMap<>() {
            };
        }
        for (String word : words) {
            map.put(word, Collections.frequency(words, word));
        }
        return map;
    }
    public static String toString(Map<String, Integer> map) {
        StringBuilder result = new StringBuilder();
        if (map.isEmpty()) {
            return "{}";
        }
        for (Map.Entry<String, Integer> item : map.entrySet()) {
            result.append("  ").append(item.getKey()).append(": ").append(item.getValue()).append("\n");
        }
        return "{\n" + result.toString() + "}";
    }
}
//END
