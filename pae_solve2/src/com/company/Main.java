package com.company;

import java.util.Scanner;

public class Main {
    public static SimpleColor getColor(double x, double y) {
        Parabola p1 = new Parabola(-1.0f, 4.0f, 0.5f);
        Parabola p2 = new Parabola(4.0f, 6.0f, 1.0f);
        Rectangle s1 = new Rectangle(-1.0f, 4.0f, 7.0, -5.0);

        Circle c1 = new Circle(5.0f, 0.0f, 3.0f);
        Circle c2 = new Circle(-7.0f, -4.0f, 2.0f);

        if(c2.isPointInsideCircle(x,y)) {
            return SimpleColor.GRAY;
        } else if (s1.isPointInsideSquare(x,y) && c1.isPointInsideCircle(x,y)) {
            return SimpleColor.WHITE;
        } else if (s1.isPointInsideSquare(x,y)) {
            return SimpleColor.GRAY;
        } else if (p1.isPointAboveParabola(x,y)
                && p2.isPointAboveParabola(x,y)) {
            return SimpleColor.YELLOW;
        } else if (p1.isPointAboveParabola(x,y)) {
            return SimpleColor.WHITE;
        }
        return SimpleColor.BLUE;
    }
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.print("Input X: ");
        double x = in.nextDouble();
        System.out.print("Input Y: ");
        double y = in.nextDouble();

        System.out.println("("+x+", "+y+") -> " + getColor(x,y));

    }
}
