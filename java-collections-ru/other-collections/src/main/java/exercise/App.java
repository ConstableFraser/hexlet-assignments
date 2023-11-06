package exercise;

import java.util.*;

// BEGIN
class App {
    public static Map<String, String> genDiff(Map<String, Object> dictA, Map<String, Object> dictB) {
        if (dictA.isEmpty() && dictB.isEmpty()) {
            return new HashMap<>();
        }
        Set<String> keys = new TreeSet<>(Comparator.naturalOrder());
        keys.addAll(dictA.keySet());
        keys.addAll(dictB.keySet());
        Map<String, String> result = new LinkedHashMap<>();
        for (String key : keys) {
            if (dictA.containsKey(key) && !dictB.containsKey(key)) {
                result.put(key, "deleted");
            } else if (!dictA.containsKey(key) && dictB.containsKey(key)) {
                result.put(key, "added");
            } else if (dictA.get(key) != dictB.get(key)) {
                result.put(key, "changed");
            } else {
                result.put(key, "unchanged");
            }
        }
        return result;
    }
}
//END
