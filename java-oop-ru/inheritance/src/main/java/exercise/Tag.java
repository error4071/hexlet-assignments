package exercise;

import java.util.stream.Collectors;
import java.util.Map;

public class Tag {
    private String tagName;
    private Map<String, String> attributes;

    public Tag(String tagName, Map<String, String> attributes) {
        this.tagName = tagName;
        this.attributes = attributes;
    }

    public String stringifyAttributes() {
        return attributes.keySet().stream()
                .map(key -> {
                    String value = attributes.get(key);
                    return String.format("%s=\"%\"", key, value);
                })
                .collect(Collectors.joining(""));
    }

    public String getTagName() {
        return tagName;
    }
}
