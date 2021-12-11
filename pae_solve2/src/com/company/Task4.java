package com.company;

import java.util.Scanner;

public class Task4 {

    public static int factorial (int n) {
        // calculate factorial
        if (n<=1) {
            return n;
        }
        return n * factorial(n-1);
    }

    public static double calc1st(int n, double x) {
        //8.	Даны натуральное число n и действительное число x.
        // Вычислить 1ое выражение
        double result = 0;
        for (int i=1; i<=n;i++) {
            result = result + (1/(float)factorial(i) + Math.sqrt(Math.abs(x)));
        }
        return result;
    }

    public static double calc2nd (int n, double x) {
        //8.	Даны натуральное число n и действительное число x.
        // Вычислить 2ое выражение
        double result = 0;
        for (int i=1; i<=n;i++) {
            result = result + (1 + Math.sin(i*x)/(float) factorial(i));
        }
        return result;
    }

    public static void main (String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.print("Введите n: ");
        int n = in.nextInt();
        System.out.print("Введите x: ");
        double x =  in.nextDouble();

        System.out.println("Решение для 1го уравнения: " + calc1st(n, x));
        System.out.println("Решение для 2го уравнения: " + calc2nd(n, x));
    }
}
