package exercise;

// BEGIN
public class LabelTag implements TagInterface{
    private final String value;
    private final TagInterface tagChild;

    LabelTag(String value, TagInterface tagChild) {
        this.value = value;
        this.tagChild = tagChild;
    }

    @Override
    public String render() {
        return String.format("<label>%s%s</label>", value, tagChild.render());
    }
}
// END
