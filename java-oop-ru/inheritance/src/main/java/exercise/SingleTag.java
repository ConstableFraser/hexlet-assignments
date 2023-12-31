package exercise;

import java.util.Map;
import java.util.stream.Collectors;

// BEGIN
public class SingleTag extends Tag {
    public SingleTag(String name, Map<String, String> map) {
        super(name, map);
    }

    @Override
    public String stringifyAttributes() {
        return attributes.entrySet().stream()
                .map(k -> k.getKey() + "=\"" + k.getValue() + "\"")
                .collect(Collectors.joining(" "));
    }

    @Override
    public String toString() {
        var result = stringifyAttributes();
        var suffix = result.isEmpty() ? "" : " " + result;
        return "<" + name + suffix + ">";
    }
}
// END
