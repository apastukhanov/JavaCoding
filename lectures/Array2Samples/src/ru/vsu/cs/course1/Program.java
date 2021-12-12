package ru.vsu.cs.course1;

import ru.vsu.cs.util.ArrayUtils;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        //test1();
        //test2();
        //test3();
        test4();
    }

    public static void printArr2(int[][] arr2) {
        for (int r = 0; r < arr2.length; r++) {
            if (arr2[r] == null) {
                System.out.println("null");
            } else {
                for (int c = 0; c < arr2[r].length; c++) {
                    System.out.print((c > 0 ? ", " : "") + arr2[r][c]);
                }
                System.out.println();
            }
        }
    }

    public static void test1() {
        int[][] arr2 = new int[5][4];

        printArr2(arr2);
        System.out.println("---------------");

        arr2[1][2] = 12;

        printArr2(arr2);
        System.out.println("---------------");

        arr2[2] = null;
        arr2[3] = null;

        printArr2(arr2);
        System.out.println("---------------");

        arr2[3] = new int[] { 30, 31, 32, 33, 34, 35, 36, 37, 38, 39 };

        printArr2(arr2);
        System.out.println("---------------");

        arr2[0] = arr2[1];
        arr2[0][1] = 100;

        printArr2(arr2);
        System.out.println("---------------");
    }


    public static void test2() {
        int[][] arr2 = new int[7][6];
        for (int r = 0; r < arr2.length; r++) {
            for (int c = 0; c < arr2[r].length; c++) {
                arr2[r][c] = r * 10 + c;
            }
        }

        printArr2(arr2);
        System.out.println("---------------");

        reverseRows(arr2);

        printArr2(arr2);
        System.out.println("---------------");

        reverseColumns(arr2);

        printArr2(arr2);
        System.out.println("---------------");
    }

    public static void reverseRows(int[][] arr2) {
        for (int r = 0; r < arr2.length / 2; r++) {
            int[] temp = arr2[r];
            arr2[r] = arr2[arr2.length - r - 1];
            arr2[arr2.length - r - 1] = temp;
        }
    }

    public static void reverseColumns(int[][] arr2) {
        // arr2.length - кол-во строк
        // arr2[0].length - кол-во столцов
        int rowsCount = arr2.length;
        int colsCount = arr2[0].length;
        for (int r = 0; r < rowsCount; r++) {
            /*
            for (int c = 0; c < colsCount / 2; c++) {
                int temp = arr2[r][c];
                arr2[r][c] = arr2[r][colsCount - c - 1];
                arr2[r][colsCount - c - 1] = temp;
            }
            */
            int[] row = arr2[r];
            for (int c = 0; c < row.length / 2; c++) {
                int temp = row[c];
                row[c] = row[colsCount - c - 1];
                row[colsCount - c - 1] = temp;
            }
        }
    }



    /*
       Найти индекс строки с минимальной суммой элементов
     */
    public static void test3() {
        int[][] arr2 = ArrayUtils.createRandomIntMatrix(9, 3, 0, 100);

        System.out.println(ArrayUtils.toString(arr2));
        System.out.println("---------------");
        System.out.printf("indexOfMinSumRow(arr2) -> %d%n", indexOfMinSumRow(arr2));
        System.out.println("---------------");

        arr2 = ArrayUtils.createRandomIntMatrix(3, 7, 0, 100);
        System.out.println(ArrayUtils.toString(arr2));
        System.out.println("---------------");
        System.out.printf("indexOfMinSumCol(arr2) -> %d%n", indexOfMinSumCol(arr2));
        System.out.println("---------------");
    }

    public static int indexOfMinSumRow(int[][] arr2) {
        /*
        int minIndex = 0;
        int min = arr[minIndex];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
                minIndex = i;
            }
        }
        return minIndex;
         */
        int minIndex = 0;
        int min = sum(arr2[minIndex]);
        for (int r = 1; r < arr2.length; r++) {
            int s = sum(arr2[r]);
            if (s < min) {
                min = s;
                minIndex = r;
            }
        }
        return minIndex;
    }

    public static int sum(int[] arr) {
        int sum = 0;
        for (int v : arr) {
            sum += v;
        }
        return sum;
    }

    public static int indexOfMinSumCol(int[][] arr2) {
        /*
        int minIndex = 0;
        int min = arr[minIndex];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
                minIndex = i;
            }
        }
        return minIndex;
         */
        int minIndex = 0;
        int min = columnSum(arr2, minIndex);
        for (int c = 1; c < arr2[0].length; c++) {
            int s = columnSum(arr2, c);
            if (s < min) {
                min = s;
                minIndex = c;
            }
        }
        return minIndex;
    }

    public static int columnSum(int[][] arr, int colIndex) {
        int sum = 0;
        for (int[] row : arr) {
            sum += row[colIndex];
        }
        return sum;
    }


    public static class Student {
        String fio;
        String studNumber;
        String phone;

        public Student(String fio, String studNumber, String phone) {
            this.fio = fio;
            this.studNumber = studNumber;
            this.phone = phone;
        }
    }

    public static void test4() {
        // students[i][j][k], где
        // i - номер курса
        // j - номер группы
        // k = поряковый номер студента
        Student[][][] students = null;

        try {
            students = loadStudents("students.txt");
        } catch (Exception e) {
            System.err.println("Ошибка загруки студентов!");
            return;
        }

        printStudents(students);
    }

    public static Student[][][] loadStudents(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));

        Student[][][] students = new Student[6][][];
        int courseNum = 1, grNum = 1;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.length() == 0) {
                continue;
            }
            if (Character.isDigit(line.charAt(0))) {
                String[] parts = line.split(" ");
                if (parts[1].equals("курс")) {
                    courseNum = Integer.parseInt(parts[0]) - 1;
                }
                if (parts[1].equals("группа")) {
                    grNum = Integer.parseInt(parts[0]) - 1;
                }
                continue;
            }
            String[] parts = line.split("\\s*,\\s*");
            Student stud = new Student(parts[0], parts[1], parts[2]);
            if (students[courseNum] == null) {
                students[courseNum] = new Student[20][];
            }
            if (students[courseNum][grNum] == null) {
                students[courseNum][grNum] = new Student[40];
            }
            int index = 0;
            while (students[courseNum][grNum][index] != null) {
                index++;
            }
            students[courseNum][grNum][index] = stud;
        }

        return students;
    }

    public static void printStudents(Student[][][] students) {
        if (students == null) {
            return;
        }

        boolean first = true;
        for (int courseNum = 0; courseNum < students.length; courseNum++) {
            if (students[courseNum] == null) {
                continue;
            }

            if (first) {
                first = false;
            } else {
                System.out.printf("%n%n");
            }
            System.out.printf("%d курс%n", courseNum + 1);

            Student[][] course = students[courseNum];
            for (int groupNum = 0; groupNum < course.length; groupNum++) {
                if (course[groupNum] == null) {
                    continue;
                }

                System.out.printf("%n%d группа%n", groupNum + 1);
                for (Student stud: course[groupNum]) {
                    if (stud == null) {
                        break;
                    }

                    System.out.printf("%s, %s, %s%n", stud.fio, stud.studNumber, stud.phone);
                }
            }
        }
    }
}
