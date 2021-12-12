package ru.vsu.cs.cource1;

import java.util.Locale;
import java.util.Scanner;


public class Main {

    public static class Point {
        public double x;
        public double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static double readDouble(String name) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Input %s: ", name);
        double v = scanner.nextDouble();
        return v;
    }

    public static Point readPoint(String name) {
        Scanner scanner = new Scanner(System.in);
        return new Point(readDouble(name + ".x"), readDouble(name + ".y"));
    }

    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    public static double distance(Point p1, Point p2) {
        return distance(p1.x, p1.y, p2.x, p2.y);
    }

    public static double triangleS(double x1, double y1, double x2, double y2, double x3, double y3) {
        double a = distance(x1, y1, x2, y2);
        double b = distance(x2, y2, x3, y3);
        double c = distance(x1, y1, x3, y3);
        double p = (a + b + c) / 2;
        double s = Math.sqrt(p * (p - a) * (p - b) * (p - c));
        return s;
    }

    public static double triangleS(Point p1, Point p2, Point p3) {
        return triangleS(p1.x, p1.y, p2.x, p2.y, p3.x, p3.y);
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);

        Point p1 = readPoint("p1");
        Point p2 = readPoint("p2");
        Point p3 = readPoint("p3");

        double s = triangleS(p1, p2, p3);

        System.out.printf("S = %.03f%n", s);
    }
}

