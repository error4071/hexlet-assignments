package exercise;

public class Cottage implements Home {
    double area;
    int floorCount;

    Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    public double getArea() {
        return this.area + floorCount;
    }

    public String toString() {
        return String.format("%d этажный коттедж площадью %s метров", floorCount, getArea());
    }
    public int compareTo(Home another) {
        if (this.getArea() == another.getArea()) {
            return 0;
        }
        if (this.getArea() > another.getArea()) {
            return 1;
        }
        return -1;
    }
}