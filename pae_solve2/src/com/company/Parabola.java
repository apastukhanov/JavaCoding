package com.company;

public class Parabola {
    public double x0;
    public double y0;
    public double a;

    public Parabola (double x0, double y0, double a) {
        this.x0=x0;
        this.y0=y0;
        this.a=a;
    }

    public boolean isPointAboveParabola (double x, double y) {
        return y > a * Math.pow(x - x0, 2) + y0;
    }

}
