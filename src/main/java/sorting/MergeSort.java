package main.java.sorting;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MergeSort {

    public static void mergeSort(int[] arr, int left, int right) {
        if (right <= left)
            return;

        int middle = (left + right) / 2;

        mergeSort(arr, left, middle);
        mergeSort(arr, middle + 1, right);

        merge(arr, left, middle, right);
    }

    private static void merge(int[] arr, int left, int middle, int right) {
        // get lengths for left and right
        int leftLength = middle - left + 1;
        int rightLength = right - middle;

        // create temporary sub arrays
        int[] leftArr = new int[leftLength];
        int[] rightArr = new int[rightLength];

        // copy sorted sub arrays into temp arrays
        for (int i = 0; i < leftLength; i++) {
            leftArr[i] = arr[left + i];
        }
        for (int i = 0; i < rightLength; i++) {
            rightArr[i] = arr[middle + i + 1];
        }

        // create two iterators: left and right, to copy from temp arrays to arr
        int leftIndex = 0;
        int rightIndex = 0;

        // copy elements back to original array, can be done in place
        for (int i = left; i <= right; i++) {
            // copy minimum of left or right, if there are still elements
            if (leftIndex < leftLength && rightIndex < rightLength) {
                if (leftArr[leftIndex] < rightArr[rightIndex]) {
                    arr[i] = leftArr[leftIndex];
                    leftIndex++;
                } else {
                    arr[i] = rightArr[rightIndex];
                    rightIndex++;
                }
            }
            // if there are still elements in leftArr
            else if (leftIndex < leftLength) {
                arr[i] = leftArr[leftIndex];
                leftIndex++;
            }
            // if there are still elements in rightArr
            else if (rightIndex < rightLength) {
                arr[i] = rightArr[rightIndex];
                rightIndex++;
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> intList =
                IntStream.range(0, 25).boxed().collect(Collectors.toList());

        // call merge sort 3 times
        for (int i = 0; i < 1; i++) {
            Collections.shuffle(intList);
            int[] array = intList.stream().mapToInt(Integer::intValue).toArray();
            print(array); // print shuffled array

            // measure running time in nana seconds
            long startTime = System.nanoTime();
            mergeSort(array, 0, array.length - 1);
            long endTime = System.nanoTime();

            print(array, endTime, startTime); // print sorted array
            intList = Arrays.stream(array).boxed().collect(Collectors.toList());
            validate(array);
        }

    }

    // validate sort
    private static void validate(int[] array) {
        for (int i = 0; i < array.length; i++) {
            assert array[i] == i;
        }
    }

    private static void print(int[] array, long endTime, long startTime) {
        /*
        1 microsecond = 1000 nanoseconds
        1 millisecond = 1000 microseconds
         */

        System.out.println();
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.format("*** Time taken = %.4f",
                (endTime - startTime) / 1000000.0); // in milli seconds
        System.out.println("\n--------------");
    }

    private static void print(int[] array) {
        System.out.println();
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

}
