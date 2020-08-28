package main.java.concurrency;

import java.util.Arrays;
import java.util.List;

public class SeqVsParallelStreams {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10
        , 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);

//        numbers.stream()
//                .map(SeqVsParallelStreams::transform)
//                .forEach(e -> {});

//        numbers.parallelStream()
//                .map(SeqVsParallelStreams::transform)
//                .forEach(e -> {});

        numbers.parallelStream()
                .map(SeqVsParallelStreams::transform)
                //.forEachOrdered(SeqVsParallelStreams::printIt);
                .forEach(e -> {});
    }

    private static void printIt(Integer number) {
        System.out.println("P: " + number + "-" + Thread.currentThread());
    }

    private static int transform(Integer number) {
        System.out.println("T: " + number + "-" + Thread.currentThread());
        sleep();
        return number * 2;
    }

    private static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }
}
