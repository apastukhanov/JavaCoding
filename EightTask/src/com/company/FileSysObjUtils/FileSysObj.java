package com.company.FileSysObjUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.company.ArrayUtils.ArrayUtil.toIntArray;
import static com.company.ArrayUtils.ArrayUtil.toStringCustom;

public class FileSysObj {

    public static void writeArrayToFile(int[][] arr, String filename) throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter(filename)) {
            out.println(toStringCustom(arr));
        }
    }

    public static void writeArrayToFile(int[] arr, String filename) throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter(filename)) {
            out.println(toStringCustom(arr));
        }
    }

    public static void writeArrayToFile(String arrString, String filename) throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter(filename)) {
            out.println(arrString);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
//        System.out.println(toString(new int[][] {{1,2,3},{4,5},{4,5,6}}));
//        writeArrayToFile(new int[][] {{1,2,3},{4,5},{4,5,6}}, "out2D.text");
//        writeArrayToFile(new int[] {1,2,3}, "out1D.text");
        System.out.println(toStringCustom(toIntArray(readLinesFromFile("input.txt"))));
    }

    public static String[] readLinesFromFile(String fileName) throws FileNotFoundException {
        List<String> lines;
        try (Scanner scanner = new Scanner(new File(fileName), "UTF-8")) {
            lines = new ArrayList<>();
            while (scanner.hasNext()) {
                lines.add(scanner.nextLine());
            }
            // обязательно, чтобы закрыть открытый файл
        }
        return lines.toArray(new String[0]);
    }
}
