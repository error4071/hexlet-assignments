package exercise;

import java.awt.font.ShapeGraphicAttribute;
import java.util.Map;

class SingleTag extends Tag {

    SingleTag(String name, Map<String, String> attributes) {
        super(name, attributes);
    }

    public String toString() {
        return String.format("<%s%s>", getName(), stringifyAttributes());
    }
}