package ru.vsu.cs.course1.first;

public class Point {
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distanceTo(Point other) {
        // d = sqrt((x1 - x2)^2 + (y1 - y2)^2)
        double d = Math.sqrt(
            Math.pow(x - other.x, 2) +
            Math.pow(y - other.y, 2)
        );
        return d;
        /*
        Point p1 = this;
        Point p2 = other;
        double d = Math.sqrt(
            (p1.x - p2.x) * (p1.x - p2.x) +
            (p1.y - p2.y) * (p1.y - p2.y)
        );
        */
    }
}
