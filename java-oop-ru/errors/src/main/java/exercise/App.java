package exercise;

import java.util.Arrays;

public class App {
    public static void printSquare(Circle circle) throws NegativeRadiusException {
        try {
            System.out.println(circle);
        } catch (NegativeRadiusException exception) {
            System.out.println("Не удалось посчитать площадь");
        } finally {
            System.out.println("Вычисление окончено");
        }
    }
}
