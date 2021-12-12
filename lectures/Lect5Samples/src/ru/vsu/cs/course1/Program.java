package ru.vsu.cs.course1;

import java.io.PrintStream;
import java.util.Locale;

public class Program {

    /**
     * Точка входа в программу.
     * <p>Вызывает все примеры.
     */
    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);

        System.out.println("----- printStarTriangle -----");
        printStarTriangle(5);
        System.out.printf("------------------------------%n%n");

        System.out.println("---------- Sample 1 ----------");
        System.out.printf("intToBin(%1$10d) -> %2$s%n", 11, intToBin_v0(11));
        System.out.printf("intToBin(%1$10d) -> %2$s%n", 5, intToBin(5));
        System.out.printf("intToBin(%1$10d) -> %2$s%n", 255, intToBin(255));
        System.out.printf("intToBin(%1$10d) -> %2$s%n", -1, intToBin(-1));
        System.out.printf("------------------------------%n%n");

        System.out.println("---------- Sample 2 ----------");
        System.out.printf("Double.isNaN(Math.sqrt(-1)) -> %b%n", Double.isNaN(Math.sqrt(-1)));
        System.out.printf("Double.isNaN(sqrt(-1)) ->      %b%n", Double.isNaN(sqrt(-1)));
        System.out.printf("Math.sqrt(5) -> %.10f%n", Math.sqrt(5));
        System.out.printf("sqrt(5)      -> %.10f%n", sqrt(5));
        System.out.printf("Math.sqrt(0.04376) -> %.10f%n", Math.sqrt(0.04376));
        System.out.printf("sqrt(0.04376)      -> %.10f%n", sqrt(0.04376));
        System.out.printf("------------------------------%n%n");

        System.out.println("---------- Sample 3 ----------");
        System.out.printf("Math.PI = %.10f%n", Math.PI);
        System.out.printf("pi(5)   = %.10f  // произведение N множителей%n", pi(5));
        System.out.printf("pi(25)  = %.10f  // произведение N множителей%n", pi(25));
        System.out.printf("pi()    = %.10f  // по EPS%n", pi());
        System.out.printf("------------------------------%n%n");

        System.out.println("---------- getStringPart ----------");
        String str = "a, bb, ccc, , e, ";
        for (int i = 0; i < 10; i++) {
            String part = getStringPart(str, i);
            System.out.printf("getStringPart(\"%s\", %d) -> %s%n", str, i, part == null ? null : "\"" + part + "\"");
        }
        System.out.printf("------------------------------%n%n");

        System.out.println("---------- Sample 5 ----------");
        System.out.println(sample5(10, 3));
        System.out.printf("------------------------------%n%n");

        System.out.println("---------- Sample 6.1 --------");
        sample6_1_v0(20);
        sample6_1(20);
        System.out.printf("------------------------------%n%n");

        System.out.println("---------- Sample 6.2 --------");
        sample6_2(20);
        System.out.printf("------------------------------%n%n");

        System.out.println("---------- Sample 6.3 --------");
        sample6_3_v0(20);
        sample6_3(20);
        System.out.printf("Код символа 'a' - %d%n", (int) 'a');
        System.out.printf("------------------------------%n%n");

        System.out.println("---------- Sample 7 ----------");
        sample7(18, 5);
        System.out.printf("------------------------------%n");
        sample7(30, 8);
        System.out.printf("------------------------------%n%n");

        System.out.println("---------- Sample 8 ----------");
        sample8(5);
        System.out.printf("------------------------------%n");
        sample8_v2(16);
        System.out.printf("------------------------------%n%n");
    }


    /**
     * Печать треугольника из звездочек:
     * <pre>
     * *
     * **
     * ***
     * ****
     * </pre>
     * @param h высота треугольника
     */
    public static void printStarTriangle(int h) {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print('*');
            }
            System.out.println();
        }
    }


    /**
     * Представление целого числа в двоичном виде в виде строки - неэффективна реализация
     * @see #intToBin(int)
     */
    public static String intToBin_v0(int n) {
        String result = "";
        for (int i = Integer.SIZE - 1; i >= 0; i--) {
            result += (n >> i) & 1;
        }
        return result;
    }

    /**
     * Представление целого числа в двоичном виде в виде строки:
     * <ul>
     * <li>   5 ->
     *   <br> 00000000000000000000000000000101
     * <li> 255 ->
     *   <br> 00000000000000000000000011111111
     * </ul>
     * <p>Для эффективности используется StringBuilder для конкатенации строк.
     * @param n число
     * @return строка из символов 0 и 1
     */
    public static String intToBin(int n) {
        StringBuilder result = new StringBuilder();
        for (int i = Integer.SIZE - 1; i >= 0; i--) {
            result.append((n >> i) & 1);
        }
        return result.toString();
    }


    /**
     * Cобственная реализация sqrt.
     * @param x параметр
     * @return посчитанный результат
     */
    public static double sqrt(double x) {
        final double EPS = 1E-9;

        if (x < 0) {
            return Double.NaN;
        }

        // double a = (x < 1) ? 0 : 1,
        //        b = (x < 1) ? 1 : x;
        double a = 1, b = x;
        if (x < 1) {  // sqrt(x) лежит от 0 до 1
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


    /**
     * Вычисление числа Пи по формуле Виета.
     * @param n кол-во итераций при расчете произведения
     * @return посчитанный результат
     */
    public static double pi(int n) {
        double mult = 1;
        double num = Math.sqrt(2);
        for (int i = 0; i < n; i++) {
            mult = mult * num / 2;
            num = Math.sqrt(2 + num);
        }

        return 2 / mult;
    }

    /**
     * Вычисление числа Пи с заданной точностью по формуле Виета.
     * <br>Цикл прекращается, когда разница между "соседними" произведениями становится < EPS
     * @see #pi(int)
     */
    public static double pi() {
        final double EPS = 1E-9;

        double mult = 1;
        double num = Math.sqrt(2);
        for (;;) {
            double multPrev = mult;
            mult = mult * num / 2;
            if (Math.abs(mult - multPrev) < EPS) {
                break;
            }
            num = Math.sqrt(2 + num);
        }

        return 2 / mult;
    }


    /**
     * Выделение N-ой части строки, разделенной запятыми:
     * <ul>
     * <li>(" 1, , abc , ", 0) -> "1"
     * <li>(" 1, , abc , ", 1) -> ""
     * <li>(" 1, , abc , ", 2) -> "abc"
     * <li>(" 1, , abc , ", 3) -> ""
     * <li>(" 1, , abc , ", 4) -> null
     * <li>(" 1, , abc , ", -1) -> null
     * </ul>
     * @param str строка
     * @param partIndex N (нумерация с 0)
     * @return выделенную часть строки или null, если partIndex недопустимый
     */
    public static String getStringPart(String str, int partIndex) {
        int index = -1;
        for (int i = 0; i < partIndex; i++) {
            index = str.indexOf(",", index + 1);
            if (index < 0) {
                return null;
            }
        }
        int nextIndex = str.indexOf(",", index + 1);
        if (nextIndex < 0) {
            return str.substring(index + 1).trim();
        } else {
            return str.substring(index + 1, nextIndex).trim();
        }
    }

    /**
     * Пример 5
     * <p>Найти N-ое по счету число, такое сумма всех цифр не превышает S.
     * @param n N
     * @param s S
     * @return найденное число
     */
    public static int sample5(int n, int s) {
        int index = 0;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            if (digitsSum(i) <= s) {
                index++;
                if (index == n) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Сумма цифр числа.
     * @param n число
     * @return посчитанная сумма
     */
    public static int digitsSum(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }


    /**
     * Пример 6.1 - первая версия реализации.
     * @see #sample6_1(int)
     * @param n N
     */
    public static void sample6_1_v0(int n) {
        int printed = 0;
        int repeat = 1;
        while (true) {
            for (char ch = 'a'; ch <= 'd'; ch++) {
                for (int k = 0; k < repeat; k++) {
                    if (printed == n) {
                        System.out.println();
                        return;
                    }
                    System.out.print(ch);
                    printed++;
                }
            }
            repeat = (repeat == 1) ? 2 : 1;
            // repeat = 3 - repeat;
        }
    }

    /**
     * Пример 6.1
     * <p>Печать N cимволов последовательности
     * <ul><li>abcdaabbccddabcdaabbccddabcdaabbc...</ul>
     * @param n N
     */
    public static void sample6_1(int n) {
        int printed = 0;
        for (int repeat = 1; ; repeat = 3 - repeat) {
            for (char ch = 'a'; ch <= 'd'; ch++) {
                for (int k = 0; k < repeat; k++) {
                    if (printed == n) {
                        System.out.println();
                        return;
                    }
                    System.out.print(ch);
                    printed++;
                }
            }
        }
    }

    /**
     * Пример 6.2
     * <p>Печать N cимволов последовательности
     * <ul><li>abcdaabbccddaaabbbcccdddaaaabbbbccccd...</ul>
     * @param n N
     */
    public static void sample6_2(int n) {
        int printed = 0;
        for (int repeat = 1; ; repeat++) {
            for (char ch = 'a'; ch <= 'd'; ch++) {
                for (int k = 0; k < repeat; k++) {
                    if (printed == n) {
                        System.out.println();
                        return;
                    }
                    System.out.print(ch);
                    printed++;
                }
            }
        }
    }

    /**
     * Пример 6.3
     * <p>Печать N cимволов последовательности - первая версия реализации с дополнительной переменной.
     * @see #sample6_3(int)
     */
    public static void sample6_3_v0(int n) {
        int printed = 0;
        int repeat = 1;
        for (char ch = 'a'; ; ch++) {
            for (int k = 0; k < repeat; k++) {
                if (printed == n) {
                    System.out.println();
                    return;
                }
                System.out.print(ch);
                printed++;
            }
            repeat++;
        }
    }

    /**
     * Пример 6.3
     * <p>Печать N cимволов последовательности
     * <ul><li>abcdaabbccddaaabbbcccdddaaaabbbbccccd...</ul>
     * @param n N
     */
    public static void sample6_3(int n) {
        int printed = 0;
        for (char ch = 'a'; ; ch++) {
            for (int k = 0; k < ch - 'a' + 1; k++) {
                if (printed == n) {
                    System.out.println();
                    return;
                }
                System.out.print(ch);
                printed++;
            }
        }
    }

    /**
     * Пример 7
     * <p>"Нарисовать" (для примера ниже w = 18, h = 5):
     * <pre>
     * *----------------*
     * |  /\    /\    /\|
     * | /  \  /  \  /  |
     * |/    \/    \/   |
     * *----------------*
     * </pre>
     * @param w ширина "картинки", >= 3
     * @param h высота "картинки", >= 3
     */
    public static void sample7(int w, int h) {
        PrintStream out = System.out;
        int between = 2 * h - 4;  // расстояние между слешами или бэкслешами
        int firstSlash = h - 2;   // позиция (номер символа в строке, нумерация с 0) первого слеша в первой строке
        int firstBackSlash = firstSlash + 1;  // позиция первого бэкслеша в первой строке
        for (int r = 0; r < h; r++) {  // r - row
            if (r == 0 || r == h - 1) {
                // первая или последняя строка
                out.print("*");
                for (int c = 1; c < w - 1; c++) {  // c - column
                    out.print("-");
                }
                out.println("*");
            } else {
                // "средние" строки
                out.print("|");
                int nextSlash = firstSlash;          // позиция очередного слеша в строке
                int nextBackSlash = firstBackSlash;  // позиция очередного бекслеша в строке
                for (int c = 1; c < w - 1; c++) {  // c - column
                    char ch = ' ';
                    if (c == nextSlash) {
                        ch = '/';
                        nextSlash += between;
                    } else if (c == nextBackSlash) {
                        ch = '\\';
                        nextBackSlash += between;
                    }
                    out.print(ch);
                }
                out.println("|");
                firstSlash--;
                firstBackSlash++;
            }
        }
    }


    /**
     * Пример 8
     * <p>Печать треугольника из символов:
     * <pre>
     * abcaabbccaaabbbc
     * ccaaaabbbbcccca
     * aaaabbbbbccccc
     * aaaaaabbbbbbc
     * cccccaaaaaaa
     * bbbbbbbcccc
     * cccaaaaaaa
     * abbbbbbbb
     * cccccccc
     * aaaaaaa
     * aabbbb
     * bbbbb
     * cccc
     * ccc
     * cc
     * a
     * </pre>
     * <p>Реализация выполнена модификацией примера 6.2
     * @param h высота треуголника
     */
    public static void sample8(int h) {
        int printedInRow = 0;
        int rowLen = h;
        for (int repeat = 1; ; repeat++) {
            for (char ch = 'a'; ch <= 'c'; ch++) {
                for (int k = 0; k < repeat; k++) {
                    if (printedInRow == rowLen) {
                        System.out.println();
                        rowLen--;
                        printedInRow = 0;
                        if (rowLen == 0) {
                            return;
                        }
                    }
                    System.out.print(ch);
                    printedInRow++;
                }
            }
        }
    }

    /**
     * Пример 8 - второй вариант реализации (принципиально отличный)
     * <p>Итерация идет по строкам и столбцам (символам в строке).
     * <p>Вариант более сложный для понимания и реализации.
     * @see #sample8(int)
     */
    public static void sample8_v2(int h) {
        char ch = 'a';
        int repeatCh = 1;   // сколько раз надо повторять каждый символ
        int printedCh = 0;  // сколько напечатали текущий символ
        for (int r = 0; r < h; r++) {
            for (int c = 0; c < h - r; c++) {
                if (printedCh == repeatCh) {  // если текущий символ повторили необходимое кол-во раз
                    if (ch == 'c') {            // если до этого повторяли символ 'с'
                        ch = 'a';    // следующий символ - 'a'
                        repeatCh++;  // увеличиваем кол-во повторений каждого символа
                    } else {
                        ch++;
                    }
                    printedCh = 0;   // сбрасывам кол-во напечатанных символов (след. символ пока ниразу не напечатан)
                }
                System.out.print(ch);
                printedCh++;
            }
            System.out.println();
        }
    }
}
