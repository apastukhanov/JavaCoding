package com.company.ArrayUtils;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class ArrayUtil {

    private static Scanner in = new Scanner(System.in);
    private static PrintStream out = System.out;

    public static String toStringCustom(int[] arr) {
        String outputStringArray = "";
        if (arr == null
                || arr.length == 0) {
            return "[]" ;
        }
        for (int el =0; el<arr.length; el++) {
            if (el < arr.length - 1) {
                outputStringArray += arr[el];
                outputStringArray += ", ";
            } else {
                outputStringArray += arr[el];
            }
        }
        return outputStringArray;
    }

    public static String toStringCustom(int[][] arr) {
        String outputStringArray = "";
        if (arr == null
                || arr.length == 0) {
            return "[]" ;
        }
        for (int[] row : arr) {
            outputStringArray += toStringCustom(row);
            if (row != arr[arr.length - 1]) outputStringArray += "\n";
        }
        return outputStringArray;
    }

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

    public static int[] addElementToEnd( int[] arr, int elementToAdd ) {
        int[] outputArr = new int[arr.length+1];
        for (int i = 0; i< arr.length; i++ ) {
            outputArr[i] = arr[i];
        }
        outputArr[arr.length] = elementToAdd;
        return outputArr;
    }

    public static int[] addArrayToEnd( int[] arr1, int[] arr2 ) {
        int[] outputArr = new int[arr1.length];
        for (int i = 0; i< arr1.length+arr2.length; i++ ) {
            if (i < arr1.length) {
                outputArr[i] = arr1[i];
            } else {
                if (isElementInArray(outputArr, arr2[i - arr1.length]) == false) {
                    outputArr = addElementToEnd(outputArr, arr2[i - arr1.length]);
                }
            }
        }
        return outputArr;
    }

    public static boolean isElementInArray( int[] arr, int elementToCheck ) {
        for(int el : arr) {
            if (el == elementToCheck) return true;
        }
        return false;
    }

    public static int[][] findMaxMinElementLocations(int[][] arr) {

        int[] rowsToDelMax = new int[0];
        int[] colsToDelMax = new int[0];

        int[] rowsToDelMin = new int[0];
        int[] colsToDelMin = new int[0];

        int RowIndex = 0;
        int ColIndex;

        boolean flagMax = false;
        boolean flagMin = false;


        int MaxValue = Integer.MIN_VALUE;
        int MinValue = Integer.MAX_VALUE;

        for (int[] row : arr) {
            ColIndex = 0;
            for (int col : row) {

                if (MaxValue < col) {
                    MaxValue = col;
                    flagMax = true;
                } else if (MaxValue == col ) {
                    if (isElementInArray(colsToDelMax, ColIndex) == false) {
                        colsToDelMax = addElementToEnd(colsToDelMax, ColIndex);
                    }
                    if (isElementInArray(rowsToDelMax, RowIndex) == false) {
                        rowsToDelMax = addElementToEnd(rowsToDelMax, RowIndex);
                    }
                }
                if (MinValue > col) {
                    MinValue = col;
                    flagMin = true;
                } else if (MinValue == col ) {
                    if (isElementInArray(colsToDelMin, ColIndex) == false) {
                        colsToDelMin = addElementToEnd(colsToDelMin, ColIndex);
                    }
                    if (isElementInArray(rowsToDelMin, RowIndex) == false) {
                        rowsToDelMin = addElementToEnd(rowsToDelMin, RowIndex);
                    }
                }
                if (flagMax == true) {
                    colsToDelMax = new int[]{ColIndex};
                    rowsToDelMax = new int[]{RowIndex};
                    flagMax = false;
                }
                if (flagMin == true) {
                    colsToDelMin = new int[]{ColIndex};
                    rowsToDelMin = new int[]{RowIndex};
                    flagMin = false;
                }

                ColIndex++;
            }
                RowIndex++;
            }

        int[] rowsToDel = addArrayToEnd(rowsToDelMax, rowsToDelMin);
        int[] colsToDel = addArrayToEnd(colsToDelMax, colsToDelMin);

        return new int[][]{rowsToDel, colsToDel};


    }

    public static int[][] delRowsAndColsInArray ( int[][] inputArr,
                                                  int[] RowsToDel,
                                                  int[] ColsToDel) {

        int countOfRows = inputArr.length - RowsToDel.length;
        int countOfCols = inputArr[0].length - ColsToDel.length;

        if (countOfCols == 0 || countOfRows == 0) return new int[0][0];

        int[][] outputArr = new int[countOfRows][countOfCols];

        int RowIndex = 0;
        int ColIndex;

        for (int i=0; i <  inputArr.length; i++) {
            if (isElementInArray(RowsToDel, i)==false) {
                ColIndex = 0;
                for (int j=0; j < inputArr[0].length; j++) {
                    if (isElementInArray(ColsToDel, j) == false) {
                        outputArr[RowIndex][ColIndex] = inputArr[i][j];
                        ColIndex++;
                    }
                }
                RowIndex++;
            }

        }
        return outputArr;
    }


    public static boolean isIntArrEmpty(int[] arr) {
        int intCountOfZeros = 0;
        for (int i=0; i<arr.length; i++) {
            if (arr[i] == 0) intCountOfZeros++;
        }
        if (intCountOfZeros==arr.length) return true;
        return false;
    }

    public static void print2dArray(int[][] inputArray) {
        out.println("[");
        for (int[] row : inputArray) {
            printArray(row);
            out.println();
        }
        out.print("]");
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

    public static int[] toIntArray(String str) {
        Scanner scanner = new Scanner(str);
        scanner.useLocale(Locale.ROOT);
        scanner.useDelimiter("(\\s|[,;])+");
        List<Integer> list = new ArrayList<>();
        while (scanner.hasNext()) {
            list.add(scanner.nextInt());
        }

        // из List<Integer> можно получить Integer[]
        Integer[] arr = list.toArray(new Integer[0]);
        // Integer[] -> int[]
        return toPrimitive(arr);
    }

    public static int[][] toIntArray(String[] str) {
        int[][] arr2d = new int[str.length][];
        int counter = 0;
        for (String row : str) {
            arr2d[counter] = toIntArray(row);
            counter++;
        }
        return arr2d;
    }

    private static int[] toPrimitive(Integer[] arr) {
        if (arr == null) {
            return null;
        }
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            // автоматическая распаковка из объекта
            result[i] = arr[i];
        }
        return result;
    }


}
