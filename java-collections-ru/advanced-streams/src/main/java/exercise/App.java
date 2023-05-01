package exercise;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.Stream;

public class App {
    public static String getForwardedVariables(String environment) {
        String[] lines = environment.split("\n");
        return Arrays.stream(lines)
                .filter(x -> x.startsWith("environment="))
                .map(x -> x.replaceAll("environment=", ""))
                .map(x -> x.replaceAll("\"", ""))
                .map(x -> x.split(","))
                .flatMap(Arrays::stream)
                .filter(y -> y.startsWith("X_FORWARDED_"))
                .map(y -> y.replaceFirst("X_FORWARDED_", ""))
                .collect(Collectors.joining(","));

    }
}

