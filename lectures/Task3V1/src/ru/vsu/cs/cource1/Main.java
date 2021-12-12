package ru.vsu.cs.cource1;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    // Соответствуют первому варианту задачи
    public static final Line L1 = new Line(-1, 6, 5.0 / 2);
    public static final Line L2 = new Line(0, -2, 0);
    public static final HorizontalParabola P1 = new HorizontalParabola(2, 2, 1);

    public static SimpleColor getColor(double x, double y) {
        // реализовать самостоятельно
        if (P1.isPointRightOfParabola(x, y)) {
            return SimpleColor.ORANGE;
        }
        if (!L2.isPointAboveLine(x, y) && L1.isPointAboveLine(x, y)) {
            return SimpleColor.GREEN;
        }
        if (!L2.isPointAboveLine(x, y) && !L1.isPointAboveLine(x, y)) {
            return SimpleColor.YELLOW;
        }
        return SimpleColor.GRAY;
    }

    public static void printColorForPoint(double x, double y) {
        System.out.printf("(%.3f, %.3f) -> %s%n", x, y, getColor(x, y));
    }

    public static void tests() {
        printColorForPoint(3, 2);
        printColorForPoint(0, 5);
        printColorForPoint(-8, -3);
        printColorForPoint(3, -3);
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);

        tests();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Input x, y: ");
        double x = scanner.nextDouble();
        double y = scanner.nextDouble();

        printColorForPoint(x, y);
    }
}
