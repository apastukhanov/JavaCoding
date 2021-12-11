package com.company;
import java.util.Scanner;


public class TimeDifference {
    public static void main(String[] args) {
	// write your code here
        Scanner in = new Scanner(System.in);

        System.out.println("Entered start time in format - hour,min,sec");
        String s = in.nextLine();
        String[] strArray1 = s.split(",");
        System.out.println("You entered 1st time: " + strArray1[0]+":"
                +strArray1[1]+":"+strArray1[2]);


        int[] start_time = new int[strArray1.length];
        for(int i = 0; i < strArray1.length; i++) {
            start_time[i] = Integer.parseInt(strArray1[i]);
        }

        System.out.println("Entered stop time in format - hour,min,sec");
        String s2 = in.nextLine();
        String[] strArray2 = s2.split(",");
        System.out.println("You entered 2nd time: " + strArray2[0]+":"
                +strArray2[1]+":"+strArray2[2]);


        int[] stop_time = new int[strArray2.length];
        for(int i = 0; i < strArray2.length; i++) {
            stop_time[i] = Integer.parseInt(strArray2[i]);
        }


        int start_time_seconds = start_time[0]*60*60 + start_time[1]*60 +start_time[2];
        int stop_time_seconds = (stop_time[0]+24)*60*60 + stop_time[1]*60 +stop_time[2];

        //        int[] start_time = {8,12,15};
        //        int[] stop_time = {13,34,55};

        System.out.print("Difference in seconds: " +
                (stop_time_seconds-start_time_seconds) % (24*60*60));
    }
}

