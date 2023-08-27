package exercise;

import java.awt.font.ShapeGraphicAttribute;
import java.util.Map;

public class SingleTag extends Tag {
    public SingleTag(String tagName, Map<String, String> attributes) {
        super(tagName, attributes);
    }

    @Override
    public String toString() {
        return String.format("<%%s>", getTagName(), stringifyAttributes());
    }
}
