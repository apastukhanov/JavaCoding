package com.company;

import java.io.PrintStream;
import java.util.Scanner;

public class Task6 {

    private static PrintStream out = System.out;

    public static double sumNNumbers (int n,
                                      double x) {
        //задача a)
        //сумму n слагаемых заданного вида
        // нужно реализовать следующую послед-ть x^1/1-x^2/2+x^3/3 ... + x^n/n
        double result = 0;
        for (int i=1; i<=n; i++){
            //x^i/i
            double increment = Math.pow(x,i)/i;
            if(i%2==0) {
                result=result-increment;
            } else {
                result =result+increment;
            }
        }
        return result;
    }

    public static double sumToLim (double x, double e) {
        //задачи б) - в)
        //сумму тех слагаемых, которые по модулю больше e
        // нужно реализовать следующую послед-ть x^1/1-x^2/2+x^3/3 ... + x^n/n
        double result = 0;
        for (int i=1; i<=1_000_000; i++){
            //x^i/i
            double increment = Math.pow(x,i)/i;
            if(Math.abs(increment)>e) {
                if(i%2==0) {
                    result=result-increment;
                } else {
                    result=result+increment;
                }
            }
        }
        return result;
    }

    public static double solveWTMath ( double x) {
        //г) считаем с помощью Math натуральный логарифм
        //значение функции с помощью методов Math
        return (double) Math.log(1+x);
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        //по условию область опредления x -> (R=1)
        System.out.print("Введите x: ");
        double x = sc.nextDouble();

        System.out.print("Введите e: ");
        double e = sc.nextDouble();

        System.out.print("Введите N: ");
        int n = sc.nextInt();

        System.out.println("\n*******Результаты*******");
        System.out.printf("1) Сумма n=" + n
                +" слагаемых: %f%n", sumNNumbers(n, x));
        System.out.printf("2) Сумма слагаемых >"+e+": %f%n",
                sumToLim(x,e));
        System.out.printf("3) Сумма слагаемых >"+e+"/10: %f%n",
                sumToLim(x, e/10));
        System.out.printf("4) Сумма слагаемых c " +
                "помощью методов Math: %f%n", solveWTMath(x));
    }
}
