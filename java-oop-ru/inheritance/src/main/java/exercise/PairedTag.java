package exercise;

import java.util.Map;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PairedTag extends Tag {
    private String body;
    private List<Tag> children;

    public PairedTag(String tagName, Map<String, String> attributes, String body, List<Tag> children) {
        super(tagName, attributes);
        this.body = body;
        this.children = children;
    }
    public String toString() {
        String attributes = stringifyAttributes();
        String tagName = getTagName();
        String value = children.stream()
                .map(Object::toString)
                .collect(Collectors.joining(""));
        return String.format("<%%>%%</%>", tagName, attributes, body, value, tagName);
    }
}
