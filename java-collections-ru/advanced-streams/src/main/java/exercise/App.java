package exercise;

import java.util.List;
import java.util.stream.Stream;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;

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

        //solution #2
        //~~~~~~~~~~~
//        Pattern pattern = Pattern.compile("(?<=X_FORWARDED_)(?<config>\\S*?)(?=[,\"])");
//        Matcher matcher = pattern.matcher(content);
//        StringBuilder contentBuilder = new StringBuilder();
//        for (int i = 0; matcher.find(); i++) {
//            contentBuilder.append(i == 0 ? "" : ",");
//            contentBuilder.append(matcher.group("config"));
//        }
//        return contentBuilder.toString();
    }
}
//END
