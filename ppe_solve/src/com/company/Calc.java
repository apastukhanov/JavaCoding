package com.company;
import  java.util.Scanner;

public class Calc {
    public static int myMethod(int x, int y){
        return x+y;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введи x: ");
        int x = Integer.parseInt(in.nextLine());
        System.out.print("Введи y: ");
        int y = Integer.parseInt(in.nextLine());
        System.out.print("Answer is: " + myMethod(x, y));
    }
}