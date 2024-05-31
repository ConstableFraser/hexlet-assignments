package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] values) {
        if (values.length == 0) {
            return Map.of();
        }

        var map = new HashMap<String, Integer>();
        var max = new MaxThread(values);
        var min = new MinThread(values);

        max.start();
        min.start();

        map.put("min", min.getMinValue());
        map.put("max", max.getMaxValue());
        return map;
    }
    // END
}
