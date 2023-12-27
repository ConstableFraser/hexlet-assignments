package exercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static List<String> buildApartmentsList(List<Home> homes, int count) {
        List<Home> sortedHomes = new ArrayList<>(homes);
        Collections.sort(sortedHomes);

        var result = sortedHomes.subList(0, Math.min(count, homes.size()));
        return result.stream().map(Home::toString).collect(Collectors.toList());
    }
}
// END
