package ArrayUtilPPE;

import java.io.PrintStream;
import java.util.Scanner;

public class ArrayUtil {

    private static Scanner in = new Scanner(System.in);
    private static PrintStream out = System.out;

    public static int[] convertStrToIntArr(String input) {
        String[] arrStrings = input.split(" ");
        int[] arrInt = new int[arrStrings.length];
        int i = 0;
        for (String el : arrStrings) {
            if (el != " ") {
                try {
                    arrInt[i]= Integer.parseInt(el);
                } catch (Exception e) {
                    out.println("Некорректно задан массив!");
                    out.print("В массив передан не Integer: '" + el + "'");
                    int[] ints = new int[0];
                    return ints;
                    }
                }
            i++;
            }

        return arrInt;
    }

    public static boolean isIntArrEmpty(int[] arr) {
        int intCountOfZeros = 0;
        for (int i=0; i<arr.length; i++) {
            if (arr[i] == 0) intCountOfZeros++;
        }
        if (intCountOfZeros==arr.length) return true;
        return false;
    }

    public static void printArray (int[] inputArray) {
        if (inputArray == null
                || inputArray.length == 0) {
            out.print("[]");
            return;
        }
        out.print("[");
        for (int el =0; el<inputArray.length; el++) {
            if (el < inputArray.length - 1) {
                out.print(inputArray[el]);
                out.print(", ");
            } else {
                out.print(inputArray[el]);
            }
        }
        out.print("]");
    }
}
