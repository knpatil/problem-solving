package main.java.problems.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeNumberSum {

    // Optimal Time and Space complexity : O(n^2) time, O(n) space
    public static List<Integer[]> threeNumberSum(int[] array, int targetSum) {
        Arrays.sort(array); // n log n

        List<Integer[]> result = new ArrayList<>();

        for (int i = 0; i < array.length - 2; i++) {
            int start = i + 1;
            int end = array.length - 1;
            while (start < end) {
                int currentSum = array[i] + array[start] + array[end];
                if (currentSum == targetSum) {
                    result.add(new Integer[]{array[i], array[start], array[end]});
                    start++;
                    end--;
                } else if (currentSum < targetSum) {
                    start++;
                } else {
                    end--;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] array = {3, 1, 6, 15, -7, 5, -6, 2, -14};
        int target = 0;
        List<Integer[]> result = threeNumberSum(array, target);
        System.out.println("Result: ");
        for (Integer[] entry : result) {
            System.out.println(Arrays.toString(entry));
        }
    }
}
