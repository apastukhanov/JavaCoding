package ru.vsu.cs.course1;

import java.util.Locale;
import java.util.Scanner;
import java.util.Random;
import java.io.PrintStream;

public class Program {

    public static double EPS = 1E-9;

    /* Функция для чтение вещественного числа с консоли
     */
    public static double readDoubleValueFromConsole(String varName) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Введите %s = ", varName);
        // return scanner.nextDouble();
        double value = scanner.nextDouble();
        return value;
    }

    /* Функция для решения квадратного уравнения,
     * возвращает объект QuadrEquationSolution, содержащий n (кол-во корней), x1 и x2
     */
    public static QuadrEquationSolution solveQuadrEquation(double a, double b, double c) {
        double d = b * b - 4 * a * c;
        if (Math.abs(d) < EPS) {  // d == 0, одно решение (проверяем первым)
            double x = -b / (2 * a);
            return new QuadrEquationSolution(1, x, x);
        } else if (d < 0) {  // нет решений
            return new QuadrEquationSolution(0, 0, 0);
        } else {  // два решения (последний вариант)
            QuadrEquationSolution res = new QuadrEquationSolution(2, 0, 0);
            res.x1 = (-b - Math.sqrt(d)) / (2 * a);
            res.x2 = (-b + Math.sqrt(d)) / (2 * a);
            return res;
        }
    }

    /* Программа решения квадратного уравнения с вводом данных и печатью ответа
     */
    public static void quadrEquationDemo() {
        double a = readDoubleValueFromConsole("a"),
            b = readDoubleValueFromConsole("b"),
            c = readDoubleValueFromConsole("c");

        QuadrEquationSolution sol = solveQuadrEquation(a, b, c);

        if (sol.n == 0) {
            System.out.println("Нет решений");
        } else if (sol.n == 1) {
            System.out.printf("Одно решение: x1 = %.03f%n", sol.x1);
        } else {
            System.out.printf("Два решения: x1 = %1$.03f, x2 = %2$.03f%n",
                sol.x1, sol.x2);
        }
    }

    /* Функция для определения типа треугольника по 3-м сторонам, возвращает
     *   0 - треугольник не существует
     *   1 - остроугольный
     *   2 - прямоугольный
     *   3 - тупоугольный
     */
    public static int getTriangleTypeV0(double a, double b, double c) {
        if (a >= c + b || b >= a + c || c >= a + b) {
            return 0;
        }

        if (Math.abs(a * a + b * b - c * c) < EPS
                || Math.abs(a * a + c * c - b * b) < EPS
                || Math.abs(b * b + c * c - a * a) < EPS) {
            return 2;
        } else if (a * a + b * b >= c * c
                || a * a + c * c >= b * b
                || b * b + c * c >= a * a) {
            return 1;
        } else {
            return 3;
        }
    }

    public static TriangleType getTriangleType(double a, double b, double c) {
        // если бы мы знали массивы,
        // записали бы все в массив и отсортировали
        double a2 = Math.min(Math.min(a, b), c);
        double c2 = Math.max(Math.max(a, b), c);
        double b2 = a + b + c - a2 - c2;
        a = a2; b = b2; c = c2;

        if (c >= a + b) {
            return TriangleType.NOT_TRIANGLE;
        }

        if (Math.abs(a * a + b * b - c * c) < EPS) {
            return TriangleType.RIGHT;
        } else if (a * a + b * b >= c * c) {
            return TriangleType.ACUTE;
        } else {
            return TriangleType.OBTUSE;
        }
    }

    /* Программа определения вида треугольника с вводом данных и печатью ответа
     */
    public static void triangleTypeDemoWithCase() {
        double a = readDoubleValueFromConsole("a"),
            b = readDoubleValueFromConsole("b"),
            c = readDoubleValueFromConsole("c");

        TriangleType type = getTriangleType(a, b, c);

        switch (type) {
        case NOT_TRIANGLE:
            System.out.println("Не треугольник");
            break;
        case ACUTE:
            System.out.println("Остроугольный");
            break;
        case RIGHT:
            System.out.println("Прямоугольный");
            break;
        case OBTUSE:
            System.out.println("Тупоугольный");
            break;
        default:  // невозможная ситуация, но для демонстрации
            System.out.println("Ошибка программы");
            break;
        }
    }

    /* Демонстрация тернарного оператора
    */
    public static void ternaryOperatorDemo() {
        double a = readDoubleValueFromConsole("a"),
               b = readDoubleValueFromConsole("b");

        double max;
        if (a > b) {
            max = a;
        }
        else {
            max = b;
        }

        // условный оператор можно переписать в виде
        double max2 = (a > b) ? a : b;

        System.out.printf("max = %1$.03d, max2 = %2$.03d%n", max, max2);
    }

    public static void simpleLoopDemo() {
        int a = 0;
        // напечатает числа от 0 до 9
        while (a < 10) {
            System.out.println(a);
            a++;  // a = a + 1
        }

        // напечатает числа от 10 до 1
        while (a > 0) {
            System.out.println(a--);
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }

        int x, y;
        for (x = 2, y = 100000; y > x; x *= 2, y /= 10) {
            System.out.printf("x = %1$d, y = %2$d%n", x, y);
        }
    }

    /* Программа, предлагающая пользователю угадать число от 1 до 100
    */
    public static void guessNumberDemo() {
        Scanner input = new Scanner(System.in);
        Random rnd = new Random();
        int rightNum = rnd.nextInt(100) + 1;

        System.out.println("Угадайте число от 1 до 100:");
        int stepsCount = 0;
        int num;
        do {
            stepsCount++;
            System.out.print(": ");
            num = input.nextInt();
            if (rightNum > num) {
                System.out.println("    Загаданное число больше");
            }
            else if (rightNum < num) {
                System.out.println("    Загаданное число меньше");
            }
            else {
                System.out.printf("    Угадали за %d попыток!%n", stepsCount);
            }
        }
        while (num != rightNum);
    }

    /* Нахождение наибольшего общего делителя
    */
    public static int gcd(int a, int b) {
        for ( ; a != 0 && b != 0; ) {
            if (a > b) {
                a %= b;
            } else {
                b %= a;
            }
        }
        return a + b;
    }

    public static void breakContinueDemo() {
        System.out.println("before loop");
        for (int i = 0; ; i++) {
            if (i > 10) {
                break;
            }
            if (i % 2 == 0) {
                continue;
            }
            System.out.println(i);
        }
        System.out.println("after loop");
    }

    /* Печать трегольника из "звездочек" высоты h
    */
    public static void printStarTriangle1(int h) {
        PrintStream out = System.out;  // надо подключить java.io.PrintStream
        for (int i = 0; i < h; i++) {
            for (int j = 0; j <= i; j++) {
                out.print("*");
            }
            out.println();
        }
    }

    /* Печать перевернутого трегольника из "звездочек" высоты h
    */
    public static void printStarTriangle2(int h) {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < h - i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    /* Собственная реализация представления int в бинарном виде в виде строки
    */
    public static String intToBinV0(int n) {
        String result = "";
        for (int i = Integer.SIZE - 1; i >= 0; i--) {
            result += (n >> i) & 1;
        }
        return result;
    }

    /* Cобственная реализация представления int в бинарном виде в виде строки
     * (при множественной конкатенации строк эффективнее использовать StringBuilder)
     */
    public static String intToBin(int n) {
        StringBuilder result = new StringBuilder();
        for (int i = Integer.SIZE - 1; i >= 0; i--) {
            result.append((n >> i) & 1);
        }
        return result.toString();
    }

    /* Cобственная реализация sqrt
    */
    public static double sqrt(double x) {
        final double EPS = 1E-9;

        if (x < 0) {
            return Double.NaN;
        }

        // double a = (x < 1) ? 0 : 1,
        //        b = (x < 1) ? 1 : x;
        double a = 1, b = x;
        if (x < 1) {
            a = 0;
            b = 1;
        }

        while (b - a > EPS) {
            double c = (a + b) / 2;
            if (c * c > x) {
                b = c;
            } else {
                a = c;
            }
        }

        return (a + b) / 2;
    }

    /* Возвращает часть строки, разделенной запятыми, по номеру, например:
     *  getStringPart(" 1,  , abc , ", 0) -> "1"
     *  getStringPart(" 1,  , abc , ", 1) -> ""
     *  getStringPart(" 1,  , abc , ", 2) -> "abc"
     *  getStringPart(" 1,  , abc , ", 3) -> ""
     *  getStringPart(" 1,  , abc , ", 4) -> null
     *  getStringPart(" 1,  , abc , ", -1) -> null
     */
    public static String getStringPart(String str, int partIndex) {
        int from = 0;
        for (int i = 0; i <= partIndex; i++) {
            int commaPos = str.indexOf(',', from);
            if (commaPos >= 0) {
                if (i == partIndex) {
                    return str.substring(from, commaPos - from).trim();
                } else {
                    from = commaPos + 1;
                }
            } else {
                if (i == partIndex) {
                    return str.substring(from).trim();
                } else {
                    return null;
                }
            }
        }
        // чтобы просто не ругался компилятор (или если i < 0)
        return null;
    }

    /* Основная функция программы
    */
    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);

        //quadrEquationDemo();        // вызов подпрограммы решения квадратного уравнения
        //triangleTypeDemoWithCase(); // вызов подпрограммы определения типа треугольника
        //guessNumberDemo();          // угадывание числа
        //{ int a = 150, b = 90; System.out.printf("НОД(%d, %d) => %d%n", a, b, gcd(a, b)); }
        printStarTriangle1(10);
        //printStarTriangle2(10);
        //System.out.println(intToBin(255));
        //breakContinueDemo();
    }
}

/* Класс, описывающий решение квадратного уравнения
 */
class QuadrEquationSolution {

    public int n;
    public double x1;
    public double x2;

    public QuadrEquationSolution() {
    }

    public QuadrEquationSolution(int n, double x1, double x2) {
        this.n = n;
        this.x1 = x1;
        this.x2 = x2;
    }
}

/* Перечисление видов треугольника
 */
enum TriangleType {
    NOT_TRIANGLE,  // не треугольник
    ACUTE,         // остроугольный
    RIGHT,         // прямоугольный
    OBTUSE         // тупоугольный
}
