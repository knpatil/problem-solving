package main.java.dp;

public class MaxSumSubArray {
    public static void main(String[] args) {
        int nums[] = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(nums));
    }

    public static int maxSubArray(int[] nums) {
        int currentSum = nums[0];
        int globalSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            System.out.print("currentSum : " + currentSum);
            System.out.print(", num[i] : " + nums[i]);
            currentSum = Math.max(currentSum + nums[i], nums[i]); // max at this index can be current number or current number + max at previous index
            if (currentSum > globalSum) {
                globalSum = currentSum;
            }
            System.out.println(", New max sum : " + currentSum);
            System.out.println("globalSum : " + globalSum);
        }
        return globalSum;
    }
}
