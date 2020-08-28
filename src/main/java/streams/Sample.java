package main.java.streams;

import java.util.Arrays;
import java.util.List;

public class Sample {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Collection Pipeline Pattern
        System.out.println(
                numbers.stream()
                        .filter(e -> e % 2 == 0)
                        //.reduce(0, Integer::sum)
                        .mapToInt(e -> e * 2)
                        .sum()
        );

        // 
    }

}
