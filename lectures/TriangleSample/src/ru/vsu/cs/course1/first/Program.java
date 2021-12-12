package ru.vsu.cs.course1.first;

import java.util.Locale;
import java.util.Scanner;

public class Program
{
    public static class Point {
        public double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    /*
    public static double distance(double x1, double y1, double x2, double y2) {
        double l = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
        return l;
    }
    */
    public static double distance(Point p1, Point p2) {
        double l = Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
        return l;
    }

    public static double readDouble(String name) {
        System.out.printf("Input %s = ", name);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextDouble();
    }

    public static Point readPoint(String name) {
        double x = readDouble(String.format("%s.x", name));
        double y = readDouble(String.format("%s.y", name));
        return new Point(x, y);
    }

    /*
    public static double calcTriangeS(double x1, double y1, double x2, double y2, double x3, double y3) {
        double a = distance(x1, y1, x2, y2);
        double b = distance(x2, y2, x3, y3);
        double c = distance(x1, y1, x3, y3);
        double p = (a + b + c) / 2;
        double s = Math.sqrt(p * (p - a) * (p - b) * (p - c));
        return s;
    }
    */
    public static double calcTriangeS(Point p1, Point p2, Point p3) {
        double a = distance(p1, p2);
        double b = distance(p2, p3);
        double c = distance(p1, p3);
        double p = (a + b + c) / 2;
        double s = Math.sqrt(p * (p - a) * (p - b) * (p - c));
        return s;
    }

    public static void main(String[] args) {
		Locale.setDefault(Locale.ROOT);

        Point p1 = readPoint("p1");
        Point p2 = readPoint("p2");
        Point p3 = readPoint("p3");

        double s = calcTriangeS(p1, p2, p3);

		System.out.printf("S = %.03f%n", s);
	}
}
