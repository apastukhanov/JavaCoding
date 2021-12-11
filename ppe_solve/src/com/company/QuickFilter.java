
package com.company;

import java.util.stream.Stream;
import java.util.Optional;
import java.io.PrintStream;


public class QuickFilter {

    public static void main (String[] args) {

        PrintStream out =  System.out;

        Stream<Integer> numbersStream = Stream.of(9, 2, 3, 4, 5, 4 ,3, 2, 9);
        Optional<Integer> result = numbersStream.reduce((x, y)->x^y);
        out.println(result);
    }
}
