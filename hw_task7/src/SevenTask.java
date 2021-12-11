import java.io.PrintStream;
import java.util.Scanner;

import static ArrayUtilPPE.ArrayUtil.*;

public class SevenTask {

    private static Scanner in = new Scanner(System.in);
    private static PrintStream out = System.out;

    public static int solve(int[] arr) {
        int total = 1;
        int max_total = 0;
        if (arr == null
                || arr.length == 0) return 0;
        for(int i=1; i<arr.length; i++){
            if (arr[i] == arr[i-1] ) {
                total++;
                if ((total > 1) && (total > max_total)) {
                    max_total = total;
                }
            } else {
                total=1;
            }
        }
        return max_total;
    }

    public static void main(String[] args) {
        /*6.	Вводится массив целых чисел.
                Найти максимальное количество
                подряд идущих одинаковых элементов
         */
        out.println("Осуществляется проверка!");
//        Test.main();
        out.print("Введи целые числа через пробел:");
        String inputString = in.nextLine();
        printArray(convertStrToIntArr(inputString));
        out.print("\nМаксимальное количество, подряд "+
                "идущих элементов: " +
                +solve(convertStrToIntArr(inputString)));
    }

}
