package exercise;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

// BEGIN
public class Sorter {
    public static List<String> takeOldestMans(List<Map<String, String>> users) {
        Predicate<Map<String, String>> onlyMans = person -> person.get("gender").equals("male");
        Function<Map<String, String>, String> shortName = person -> person.get("name");

        List<Map<String, String>> people = new ArrayList<>(users);
        people.sort(Comparator.comparing(p -> LocalDateTime.parse(p.get("birthday")+"T00:00:00")));
        return people.stream()
                .filter(onlyMans)
                .map(shortName)
                .collect(Collectors.toList());
    }
}
// END
