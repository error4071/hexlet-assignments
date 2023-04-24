package exercise;

import java.util.Arrays;
import java.util.List;

public class App {
    public static List<String> free = Arrays.asList("yandex.ru", "gmail.com", "hotmail.com");

    public static long getCountOfFreeEmails(List<String> emailsList) {
        return emailsList.stream()
                .map(email -> email.split("@")[1])
                .filter(free::contains)
                .count();
    }
}