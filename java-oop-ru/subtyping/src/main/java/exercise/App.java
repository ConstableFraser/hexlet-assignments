package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
class App {
    public static void swapKeyValue(KeyValueStorage map) {
        Map<String, String> cloneMap = new HashMap<>(map.toMap());
        map.clear();

        for (Map.Entry<String, String> item : cloneMap.entrySet()) {
            map.set(item.getValue(), item.getKey());
        }
    }
}
// END
