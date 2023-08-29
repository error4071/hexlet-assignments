package exercise;

public class Circle {
    public int radius;

    public Circle(Point point, int radius) {
        this.radius = radius;
        Point point1 = point;
    }

    public int getRadius() {
        return radius;
    }

    public double getSquare() {
        return Math.PI * radius * radius;
    }
    public void validateRadius(int radius) throws NegativeRadiusException {
        if (radius < 0) {
            throw new NegativeRadiusException();
        }
    }
}
