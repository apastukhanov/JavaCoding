import java.io.PrintStream;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.UnaryOperator;

import static ArrayUtilPPE.ArrayUtil.printArray;


public class Test {

    private static PrintStream out = System.out;

    private static void test1() {
        int[] arrInt = {1, 2, 3};
        boolean isTestPassed= false;
        out.println("*****Test 1****");
        out.print("input array is ");
        printArray(arrInt);
        out.print("\nAnswer is "+SevenTask.solve(arrInt) + ".");
        if (SevenTask.solve(arrInt)==0) isTestPassed= true;
        if (isTestPassed= false){
            out.println("\nTest 1 is NOT PASSED!");
        } else {
            out.println("\nTest 1 is ok!");
        }
    }

    private static void test2() {
        int[] arrInt = {1, 2, 3, 3};
        boolean isTestPassed= false;
        out.println("*****Test 2****");
        out.print("input array is ");
        printArray(arrInt);
        out.print("\nAnswer is "+SevenTask.solve(arrInt) + ".");
        if (SevenTask.solve(arrInt)==2) isTestPassed= true;
        if (isTestPassed= false){
            out.println("\nTest 2 is NOT PASSED!");
        } else {
            out.println("\nTest 2 is ok!");
        }
    }

    private static void test3() {
        int[] arrInt = {1, 2, 3, 3, 3};
        boolean isTestPassed= false;
        out.println("*****Test 3****");
        out.print("input array is ");
        printArray(arrInt);
        out.print("\nAnswer is "+SevenTask.solve(arrInt) + ".");
        if (SevenTask.solve(arrInt)==3) isTestPassed= true;
        if (isTestPassed= false){
            out.println("\nTest 3 is NOT PASSED!");
        } else {
            out.println("\nTest 3 is ok!");
        }
    }

    private static void test4() {
        int[] arrInt = {1, 2, 3, 3, 3, 4, 4};
        boolean isTestPassed= false;
        out.println("*****Test 4****");
        out.print("input array is ");
        printArray(arrInt);
        out.print("\nAnswer is "+SevenTask.solve(arrInt) + ".");
        if (SevenTask.solve(arrInt)==3) isTestPassed= true;
        if (isTestPassed= false){
            out.println("\nTest 4 is NOT PASSED!");
        } else {
            out.println("\nTest 4 is ok!");
        }
    }

    private static void test5() {
        int[] arrInt = {1, 1, 1, 1, 1, 1, 1, 2, 3, 3, 3, 4, 4};
        boolean isTestPassed= false;
        out.println("*****Test 5****");
        out.print("input array is ");
        printArray(arrInt);
        out.print("\nAnswer is "+SevenTask.solve(arrInt) + ".");
        if (SevenTask.solve(arrInt)==7) isTestPassed= true;
        if (isTestPassed= false){
            out.println("\nTest 5 is NOT PASSED!");
        } else {
            out.println("\nTest 5 is ok!");
        }
    }

    private static void test6() {
        int[] arrInt = {1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1};
        boolean isTestPassed= false;
        out.println("*****Test 6****");
        out.print("input array is ");
        printArray(arrInt);
        out.print("\nAnswer is "+SevenTask.solve(arrInt) + ".");
        if (SevenTask.solve(arrInt)==0) isTestPassed= true;
        if (isTestPassed= false){
            out.println("\nTest 6 is NOT PASSED!");
        } else {
            out.println("\nTest 6 is ok!");
        }
    }

    private static void test7() {
        int[] arrInt = {1, 1, 1, 1, 1, 1};
        boolean isTestPassed= false;
        out.println("*****Test 7****");
        out.print("input array is ");
        printArray(arrInt);
        out.print("\nAnswer is "+SevenTask.solve(arrInt) + ".");
        if (SevenTask.solve(arrInt)==6) isTestPassed= true;
        if (isTestPassed= false){
            out.println("\nTest 7 is NOT PASSED!");
        } else {
            out.println("\nTest 7 is ok!");
        }
    }

    private static void test8() {
        int[] arrInt = new int[6];
        boolean isTestPassed= false;
        out.println("*****Test 8****");
        out.print("input array is ");
        printArray(arrInt);
        out.print("\nAnswer is "+SevenTask.solve(arrInt) + ".");
        if (SevenTask.solve(arrInt)==6) isTestPassed= true;
        if (isTestPassed= false){
            out.println("\nTest 8 is NOT PASSED!!!");
        } else {
            out.println("\nTest 8 is ok!");
        }
    }

    private static void test9() {
        int[] arrInt = null;
        boolean isTestPassed= false;
        out.println("*****Test 9****");
        out.print("input array is ");
        printArray(arrInt);
        out.print("\nAnswer is "+SevenTask.solve(arrInt) + ".");
        if (SevenTask.solve(arrInt)==0) isTestPassed= true;
        if (isTestPassed= false){
            out.println("\nTest 9 is NOT PASSED!!!");
        } else {
            out.println("\nTest 9 is ok!");
        }
    }

    private static void test10() {
        int[] arrInt = {0,0,0,0,0,0};
        boolean isTestPassed= false;
        out.println("*****Test 10****");
        out.print("input array is ");
        printArray(arrInt);
        out.print("\nAnswer is "+SevenTask.solve(arrInt) + ".");
        if (SevenTask.solve(arrInt)==6) isTestPassed= true;
        if (isTestPassed= false){
        out.println("\nTest 10 is NOT PASSED!!!");
        } else {
            out.println("\nTest 10 is ok!");
        }
    }

    public static void startAllTests() {
        test1();
        out.println();
        test2();
        out.println();
        test3();
        out.println();
        test4();
        out.println();
        test5();
        out.println();
        test6();
        out.println();
        test7();
        out.println();
        test8();
        out.println();
        test9();
        out.println();
        test10();
        out.println();
    }

    public static void main(String[] args) {
        
        MyTest test = (int[] arr, int testNumber, int correctAns) -> {
            boolean isTestPassed= false;
            out.println("*****Test " + testNumber + "****");
            out.print("input array is ");
            printArray(arr);
            int ans = SevenTask.solve(arr);
            out.print("\nAnswer is "+ans + ".");
            if (ans==correctAns) isTestPassed= true;
            if (isTestPassed= false){
                out.println("\nTest " + testNumber + " is NOT PASSED!!!");
            } else {
                out.println("\nTest " + testNumber + " is ok!");
            }
            return ans;
        };

        int[][][] arr3d = {
                {new int[]{1,2,2,2}, {1}, {3}},
                {new int[]{0,0,0,0}, {2}, {4}},
        };

        for (int i = 1; i <=2; i++) {
            test.test(arr3d[i-1][0], arr3d[i-1][1][0], arr3d[i-1][2][0]);
        }

        MyTest[] tests = {
                test
        };

        for(MyTest t : tests) {
            t.test(new int[]{1,2,2,2}, 1, 3);
        }

        BiFunction<Integer, Double, Integer> sum11 = (x, y) -> (int)(x+y+2);

        out.println(sum11.apply(10,29.62));

//        startAllTests();
    }
}
