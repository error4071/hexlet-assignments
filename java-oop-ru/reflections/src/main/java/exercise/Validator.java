package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Validator {
    public static List<String> validate(Object obj) {
        List<String> notValidAnnotation = new ArrayList<>();
        Field[] field = obj.getClass().getDeclaredFields();
            for (Field fields : field) {
                if (fields.isAnnotationPresent(NotNull.class)) {
                    fields.setAccessible(true);
                }
                    try {
                        Object value = fields.get(obj);
                        if (value == null) {
                            notValidAnnotation.add(fields.getName());
                        }
                    } catch (IllegalAccessException exception) {
                        exception.printStackTrace();
                    }
                }
            return notValidAnnotation;
    }
}