package exercise;

import org.apache.commons.lang3.StringUtils;

import exercise.model.Address;
import exercise.annotation.Inspect;
import java.lang.reflect.Method;

public class Application {
    public static void main(String[] args) {
        var address = new Address("London", 12345678);

        for (Method method : Address.class.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Inspect.class)) {
                String methodName = StringUtils.capitalize(method.getName());
                String typeName = method.getReturnType().toString().split("\\.").length > 1 ?
                        method.getReturnType().toString().split("\\.")[2] :
                        method.getReturnType().toString();
                System.out.println("Method get" + methodName + " returns a value of type " + typeName);
            }
        }
    }
}
