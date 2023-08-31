package exercise;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class App {
    public static void save(Path path, Car instance) throws Exception {
        String jsonRepresentation = instance.serialize();
        Files.writeString(path, jsonRepresentation, StandardOpenOption.WRITE);
    }

    public static Car extract(Path path) throws Exception {
        String jsonRepresentation = Files.readString(path);
        Car instance = Car.unserialize(jsonRepresentation);
        return instance;
    }
}
