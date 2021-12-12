package com.company;

//import com.company.ArrayUtils.ArrayUtil;
import com.company.CmdArgs.*;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import static com.company.ArrayUtils.ArrayUtil.*;
import static com.company.FileSysObjUtils.FileSysObj.*;


public class ConsoleAppT8 {

//    class Slicer {
//        public int[] rows;
//        public int[] columns;
//
//        Slicer (int[] row_, int[] columns_) {
//            rows = row_;
//            columns = columns_;
//        }
//    }

    private static Scanner in = new Scanner(System.in);
    private static PrintStream out = System.out;

    public static void main(String[] args) throws FileNotFoundException {
     /*   31.	Создать новый двумерный массив чисел,
                исключив из переданного двумерного массива все строки и столбцы,
                содержащие максимальные и минимальные элементы
                среди всех элементов переданного массива.

                1) в виде консольного приложения с разбором параметров командной строки,

      */

        int[][] arr = {
                { 1, 2, 3, 4 },
                { 0, 2, 2, 2 },
                { 4, 2, 2, 2 },
                { 1, 66, 3, 4 },
                { 1, 2, 100, 2 }
        };

//        ArrayUtil.print2dArray (arr);
//        out.println();
//        ArrayUtil.printArray(ArrayUtil.addArrayToEnd(new int[]{1,2,3,4},new int[]{3,4,5,6}));


        CmdParams params = CmdArgs.parseArgs(args);

        int[][] arr2  = toIntArray(readLinesFromFile(params.filename));
        int[][] slicer = findMaxMinElementLocations(arr2);
//        params= CmdArgs.parseArgs(new String[] {"-i", "/Desktop/input.txt", "-o", "/Desktop/output.txt"});
        out.println();
        print2dArray(delRowsAndColsInArray(arr2,slicer[0],slicer[1]));
        writeArrayToFile(delRowsAndColsInArray(arr2,slicer[0],slicer[1]), params.outfile);
        out.println();
        out.print("-i " + params.filename +" -o " + params.outfile);
    }
}
