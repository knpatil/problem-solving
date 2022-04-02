package main.java.problems.easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

public class TwoSum {

    public static int[] twoSumNLogN(int[] array, int targetSum) {

        Arrays.sort(array); // O(n log n)
        int start = 0;
        int end = array.length - 1;

        while ( start < end ) {   // O(n)
            int currentSum = array[start] + array[end];
            if ( currentSum == targetSum)	{
                return new int[]{array[start], array[end]};
            } else if ( currentSum < targetSum ) {
                start++;
            } else {
                end--;
            }
        }

        throw new NoSuchElementException("No such elements in the array!");
    }

    public static int[] twoSumO1TimeONSpace(int[] array, int targetSum) {

        Set<Integer> numbers = new HashSet<>(); // O(n) space

        for (int value : array) {
            int diff = targetSum - value;
            if (numbers.contains(diff)) {
                return new int[]{diff, value};
            } else {
                numbers.add(value);
            }
        }

        throw new NoSuchElementException("No such elements in the array!");
    }

    public static void main(String[] args) {
        int[] array = {-1, 34, 4, 1, 0, 13, 10};
        int targetSum = 17;

        System.out.println(" First solution: " + Arrays.toString(twoSumNLogN(array, targetSum)));
        System.out.println("Second solution: " + Arrays.toString(twoSumO1TimeONSpace(array, targetSum)));
    }

}
