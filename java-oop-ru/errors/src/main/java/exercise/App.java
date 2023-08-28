package exercise;

public class App {
    public static int printSquare(Circle circle) {
        try {
            System.out.println(circle);
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("Не удалось посчитать площадь");
        } finally {
            System.out.println("Вычисление окончено");
        }
        return printSquare(circle);
    }
}
