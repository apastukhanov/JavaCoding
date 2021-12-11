package com.company;

public class Rectangle {
    public double a;
    public double b;
    public double c;
    public double d;

    public Rectangle(double a, double b, double c, double d) {
        this.a=a;
        this.b=b;
        this.c=c;
        this.d=d;
    }

    public boolean isPointInsideSquare (double x, double y) {
        return 0 < Math.sqrt((a-x)*(x-c))* Math.sqrt((b-y)*(y-d));
    }
}
