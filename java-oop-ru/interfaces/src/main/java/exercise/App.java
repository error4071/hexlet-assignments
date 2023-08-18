package exercise;

import java.util.List;
import java.util.stream.Collectors;

public class App {
    public static List buildApartmentList(List<Home> apartments, int count) {
        return apartments.stream()
                .sorted(Home::compareTo)
                .limit(count)
                .map(Home::toString)
                .toList();
    }
}
