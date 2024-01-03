package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag{
    private final String body;
    private final List<Tag> listTag;

    public PairedTag(String name, Map<String, String> attributes, String body, List<Tag> listTag) {
        super(name, attributes);
        this.body = body;
        this.listTag = listTag;
    }

    @Override
    public String stringifyAttributes() {
        StringBuilder result = new StringBuilder(attributes.entrySet().stream()
                .map(k -> k.getKey() + "=\"" + k.getValue() + "\"")
                .collect(Collectors.joining(" ")));
        var suffix = result.isEmpty() ? "" : " " + result;
        result = new StringBuilder("<" + name + suffix + ">");
        for (Tag tag : listTag) {
            result.append(tag.toString());
        }
        return String.valueOf(result);
    }
    public String toString() {
        var result = stringifyAttributes();
        result += body;
        result += "</" + name + ">";
        return result;
    }
}
// END
