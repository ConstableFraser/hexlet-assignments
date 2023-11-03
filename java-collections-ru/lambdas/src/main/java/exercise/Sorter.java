package exercise;

import java.time.LocalDate;
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

        return users.stream()
                .filter(onlyMans)
                .sorted(Comparator.comparing(p -> LocalDate.parse(p.get("birthday")).toEpochDay()))
                .map(shortName)
                .collect(Collectors.toList());
    }
}
// END
