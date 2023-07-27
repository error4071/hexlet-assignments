package exercise;

public class Segment {
    private Point BeginPoint;
    private Point EndPoint;
    private Point midPoint;

    public Segment(Point point1, Point point2) {
        this.BeginPoint = point1;
        this.EndPoint = point2;
    }
    public Point getBeginPoint() {
        return this.BeginPoint;
    }
    public Point getEndPoint() {
        return this.EndPoint;
    }
    public Point getMidPoint() {
        int newX = (getBeginPoint().getX() + getEndPoint().getX()) / 2;
        int newY = (getBeginPoint().getY() + getEndPoint().getY()) / 2;
        return new Point(newX, newY);
    }
}
