package ru.vsu.cs.course1.first;

public class Triangle {
    public Point p1;
    public Point p2;
    public Point p3;

    public Triangle(Point p1, Point p2, Point p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    public double getS() {
        double a = p1.distanceTo(p2);
        double b = p2.distanceTo(p3);
        double c = p3.distanceTo(p1);
        double p = (a + b + c) / 2;
        double s = p * (p - a) * (p - b) * (p - c);
        s = Math.sqrt(s);

        return s;
    }
}
