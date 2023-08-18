package exercise;

public class Cottage implements Home {
    double area;
    double floorCount;

    Cottage(double area, double floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    @Override
    public double getArea() {
        return area + floorCount;
    }

    @Override
    public String toString() {
        return floorCount + " этажный коттедж площадью " + getArea() + " метров";
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