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
        if (radius > 0) {
            return Math.PI * radius * radius;
        }
        if (radius < 0) {
            throw new NegativeRadiusException();
        }
        return radius;
    }
}


