package com.company;

import java.util.Scanner;

public class Task5 {

    public static String[] fillArray(String[] arr,
                                     int startBoundary,
                                     String inputElement) {
        for (int k = 0 + startBoundary; k < arr.length - startBoundary; k++) {
            arr[k] = inputElement;
        }
        return arr;
    }

    public static void printFigure(int s) {
        //принимает не четное число
        String[] line = new String[s];

        boolean isHitCenter = false;
        int center = s / 2;

        for (int depth = 0; depth < s; depth++) {

            line = fillArray(line, 0, " ");

            if (depth == 0 || depth == s - 1) {
                line[center] = "*";
            } else if (depth == center) {
                line = fillArray(line, 0, "*");
                isHitCenter = true;
            } else if (isHitCenter == false) {
                line = fillArray(line, center - depth, "*");
            } else if (isHitCenter == true) {
                line = fillArray(line, depth-center, "*");
            }

        for (String l : line) {
            System.out.print(l);
        }
        System.out.print("\n");
    }

    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        boolean isInputCorrect = false;

        while (isInputCorrect == false) {

            System.out.print("Введите нечетное число: ");
            int s = in.nextInt();

            if (s%2==0) {
                System.out.println("Вы ввели четное число!");
            } else {
                isInputCorrect = true;
                printFigure(s);
            }
        }
    }
}
