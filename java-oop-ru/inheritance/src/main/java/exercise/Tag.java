package exercise;

import java.util.Map;

// BEGIN
public abstract class Tag {
    protected String name;
    protected Map<String, String> attributes;
    public abstract String stringifyAttributes();
    public abstract String toString();


    public Tag(String name, Map<String, String> attributes) {
        this.name = name;
        this.attributes = attributes;
    }
}
// END
