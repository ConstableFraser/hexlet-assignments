package exercise;

//import java.util.Arrays;
import java.util.List;
//import java.util.Optional;
import java.util.stream.Stream;

// BEGIN
class App {
    public static String getForwardedVariables(String content) {
        String[] result = content.split("\"");

        List<String> configValues = Stream.of(result)
                .filter(x -> x.trim().contains("X_FORWARDED_"))
                .toList();

        result = String.join(",", configValues).split(",");

        configValues = Stream.of(result)
                .filter(x -> x.trim().startsWith("X_FORWARDED_"))
                .filter(x -> !x.contains(" "))
                .map(x -> x.replace("X_FORWARDED_", ""))
                .toList();
        return String.join(",", configValues);
    }
}
//END
