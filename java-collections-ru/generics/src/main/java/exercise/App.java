package exercise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Map.Entry;

public class App<T> {
    public static List<Map<String, String>> findWhere(List<Map<String, String>> map, Map<String, String> keys) {

        List<Map<String, String>> books = new ArrayList<>();

        for (Map<String, String> element1 : map) {
            boolean find = true;

            for (Entry<String, String> entry : keys.entrySet()) {
                String itemValue = element1.getOrDefault(entry.getKey(), "");
                if (!itemValue.equals(entry.getValue())) {
                    find = false;
                }
            }
            if (find) {
                books.add(element1);
            }
        }
        return books;
    }
}
