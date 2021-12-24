package com.company;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Test8Task {

    public static PrintStream out = System.out;

    public static void test1() throws FileNotFoundException {
        //вводится массив 5 на 5
        out.println("Test 1 is started!");
        ConsoleAppT8.main(new String[] {"-i", "5x5.txt", "-o", "input01.txt"});
        out.println();
    }

    public static void test2() throws FileNotFoundException {
        //вводится массив 7 на 5
        out.println("Test 2 is started!");
        ConsoleAppT8.main(new String[] {"-i", "7x5.txt", "-o", "input02.txt"});
        out.println();
    }
    public static void test3() throws FileNotFoundException {
        //вводится массив 5 на 7
        out.println("Test 3 is started!");
        ConsoleAppT8.main(new String[] {"-i", "5x7.txt", "-o", "input03.txt"});
    }
    public static void test4() throws FileNotFoundException {
        //вводится массив 1 на 1
        out.println("Test 4 is started!");
        ConsoleAppT8.main(new String[] {"-i", "1x1.txt", "-o", "input04.txt"});
        out.println();
    }
    public static void test5() throws FileNotFoundException {
        //вводится несуществеующий файл
        out.println("Test 5 is started!");
        ConsoleAppT8.main(new String[] {"-i", "notexistedfile.txt", "-o", "input05.txt"});
        out.println();
    }

    public static void main(String[] args) throws FileNotFoundException {
        test1();
        test2();
        test3();
        test4();
        test5();
    }
}
