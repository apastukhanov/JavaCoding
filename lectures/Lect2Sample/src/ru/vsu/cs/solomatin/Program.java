package ru.vsu.cs.solomatin;

import java.util.Locale;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);

        //varDeclarationDemo()
        bitOperationsDemo();
        //doubleDemo();
        //typeConvertionDemo();

        //scannerDemo();
    }

    /**
     * демострация правил объявления переменных
     */
    static void varDeclarationDemo() {
        final int N = 100;
        final String HELLO = "Hello, World!";

        int a;  // a = 0

        double b = 1.5;

        int c, d; // c = 0, d = 0
    }

    /**
     * функция перевода double в бинарный вид, представленный в виде строки
     */
    public static String doubleToBinaryStr(double value) {
        return String.format("%64s", Long.toBinaryString(Double.doubleToRawLongBits(value))).replace(' ', '0');
    }

    /**
     * демонстрация особенностей вещестыенных чисел, правила сравнения
     */
    public static void doubleDemo() {
        // Вещественные числа представлены в памяти в двоичном виде:
        // часть битов определяет несколько значащих знаков, часть - порядок,
        // т.е. положение этих знаков относительно запятой.
        // Это означает, что никаких точных десятичных значений в double не бывает - только приближенные

        System.out.printf("%f%n", 9876543210.0123456789);  // 9876543210.012346
        System.out.println("--------------------------------");

        double a = 105.3256, b = 105.32, c = 0.0056;
        System.out.println(a - b == c);   // напечатает false
        // объяснение
        System.out.printf("%f - %f = %f%n", a, b, a - b);
        System.out.printf("%.20f:%n  %s%n", a, doubleToBinaryStr(a));
        System.out.printf("%.20f:%n  %s%n", b, doubleToBinaryStr(b));
        System.out.printf("%.20f:%n  %s%n", a - b, doubleToBinaryStr(a - b));
        System.out.printf("%.20f:%n  %s%n", c, doubleToBinaryStr(c));
        System.out.println("--------------------------------");

        System.out.println(Math.abs((a - b) - c) < 1e-10);  // напечатает true
        System.out.println("--------------------------------");

        double a2 = 100;
        double b2 = 0;
        double c2 = a2 / b2;
        System.out.println(c2);         // Infinity
        System.out.println(c2 == c2);   // true
        System.out.println(Double.isInfinite(c2));  // true
        System.out.println(c2 == Double.POSITIVE_INFINITY);  // true
        System.out.println(c2 == Double.NEGATIVE_INFINITY);  // false
        System.out.println("--------------------------------");

        double c3 = Math.sqrt(-1);
        System.out.println(c3);         // NaN
        System.out.println(c3 == c3);   // false
        System.out.println(Double.isNaN(c3));  // true
        System.out.println("--------------------------------");
    }

    /**
     * функция перевода int в бинарный вид, представленный в виде строки
     */
    public static String intToBinaryStr(int value) {
        return String.format("%32s", Integer.toBinaryString(value)).replace(' ', '0');
    }

    /*
     * Демонстрации битовых операций
     */
    static void bitOperationsDemo() {
        int a = 184;
        int b = 73;
        int c = 2;

        System.out.printf("%2$d %1$s %3$d == %4$d :%n", "&", a, b, a & b);
        System.out.printf("%2$s%n%1$s%n%3$s%n==%n%4$s%n", "&", intToBinaryStr(a), intToBinaryStr(b), intToBinaryStr(a & b));
        System.out.println("--------------------------------");

        System.out.printf("%2$d %1$s %3$d == %4$d :%n", "|", a, b, a | b);
        System.out.printf("%2$s%n%1$s%n%3$s%n==%n%4$s%n", "|", intToBinaryStr(a), intToBinaryStr(b), intToBinaryStr(a | b));
        System.out.println("--------------------------------");

        System.out.printf("%1$s%2$d == %3$d :%n", "~", a, ~a);
        System.out.printf("%1$s%n%2$s%n==%n%3$s%n", "~", intToBinaryStr(a), intToBinaryStr(~a));
        System.out.println("--------------------------------");

        System.out.printf("%2$d %1$s %3$d == %4$d :%n", "<<", b, c, b << c);
        System.out.printf("%2$s%n%1$s%n%3$s%n==%n%4$s%n", "<<", intToBinaryStr(b), c, intToBinaryStr(b << c));
        System.out.println("--------------------------------");

        System.out.printf("%2$d %1$s %3$d == %4$d :%n", ">>", b, c, b >> c);
        System.out.printf("%2$s%n%1$s%n%3$s%n==%n%4$s%n", ">>", intToBinaryStr(b), c, intToBinaryStr(b >> c));
        System.out.println("--------------------------------");
    }

    /*
     * демонстрация различных вариантов конвертации типов данных
     */
    static void typeConvertionDemo() {
        {
            int intVar = 9;
            double doubleVar = intVar;  // неявное преобразование int -> double
            System.out.println(doubleVar);  // 9
        }
        System.out.println("--------------------------------");
        {
            double doubleVar = 5.7;
            int intVar = (int) doubleVar;  // явное преобразование double -> int
            System.out.println(intVar);  // 5
        }
        System.out.println("--------------------------------");
        {
            String s = 5 + "";
            System.out.println(s);  // 5
        }
        System.out.println("--------------------------------");
        {
            String s = "789";
            int a = Integer.parseInt(s);
            System.out.println(a);  // 789
        }
        System.out.println("--------------------------------");
        {
            String s = "1.5";
            int a = 0;
            boolean ok = false;
            try {
                a = Integer.parseInt(s);
                ok = true;
            } catch (Exception ignored) {
            }
            System.out.printf("%1$b, %2$d%n", ok, a);  // false, 0
        }
        System.out.println("--------------------------------");
        {
            String s = "15";
            int a = 0;
            boolean ok = false;
            try {
                a = Integer.parseInt(s);
                ok = true;
            } catch (Exception ignored) {
            }
            System.out.printf("%1$b, %2$d%n", ok, a);  // true, 15
        }
        System.out.println("--------------------------------");
        {
            String s = "0.5";
            double a = Double.parseDouble(s);
            System.out.println(a);  // 0.5
        }
        System.out.println("--------------------------------");
        {
            double a = 12.89;
            int b1 = (int) a,
                    b2 = (int) Math.round(a);
            System.out.printf("%d, %d%n", b1, b2);  // 12, 13
        }
        System.out.println("--------------------------------");
    }

    /*
     * Демонстрация работы с java.util.Scanner
     */
    public static void scannerDemo() {
        Scanner scanner = new Scanner(System.in);
        // в качеств разделителя будет любое кол-во пробелов, запятых и точек с запятой
        scanner.useDelimiter("(\\s|[,;])+");

        System.out.print("Введите x, y: ");
        int x = scanner.nextInt(),
                y = scanner.nextInt();

        System.out.printf("%d, %d%n", x, y);
        System.out.println("--------------------------------");
    }
}
