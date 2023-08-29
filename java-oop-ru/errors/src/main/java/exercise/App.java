package exercise;

import java.util.Arrays;

public class App {
    public static void printSquare(Circle circle) throws NegativeRadiusException {
        try {
            int square = (int) Math.ceil(circle.getSquare());
            System.out.println(square);
        } catch (NegativeRadiusException exception) {
            System.out.println("Не удалось посчитать площадь");
        } finally {
            System.out.println("Вычисление окончено");
        }
    }
}
