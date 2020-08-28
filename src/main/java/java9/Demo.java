package main.java.java9;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Demo {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
        }

        IntStream.range(0, 5)
                .forEach(System.out::println);

        IntStream.rangeClosed(0, 5)
                .forEach(System.out::println);

        IntStream.iterate(0, i -> i <= 10, i -> i + 3)
                .forEach(System.out::println);

        IntStream.iterate(0, i -> i + 3)
                .takeWhile(i -> i < 20)
                .forEach(System.out::println);

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Optional<Integer> result = numbers.stream()
                .filter(e -> e > 50)
                .findFirst();

        result.ifPresent(System.out::println);
        AtomicInteger n = new AtomicInteger();
        result.ifPresent(n::set);

        int n3 = result.orElse(0);
        System.out.println("n3: " + n3);

//        int n2 = result.orElseThrow();
//        System.out.println(n2);

        // type inference
        var max = 100;
        System.out.println(max);



    }
}
