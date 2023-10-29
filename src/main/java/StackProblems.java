package patterns;

import java.util.*;

public class StackProblems {

    /*
    Buildings With an Ocean View

    There are n buildings in a line.
    You are given an integer array heights of size n that represents the heights of the
    buildings in the line.

    The ocean is to the right of the buildings. A building has an ocean view if the
    building can see the ocean without obstructions. Formally, a building has an ocean view
    if all the buildings to its right have a smaller height.

    Return a list of indices (0-indexed) of buildings that have an ocean view,
    sorted in increasing order.
     */
    /*
    Brute force:
        Exhaustive, check every index to the right of the current building
        TC: O(n^2)

     Decrease and conquer:
        From the left end:
            Delegate each index to different worker
            Each worker asymptotically takes O(n) time
            TC: O(n^2)
        From the right end:
            Delegate each index to diff worker
            Pass the height of the tallest building on the right side to each call
            T(n)  = C + T(n - 1)
                  = C + C + T(n - 2)
                  = O(n)
     */
    public int[] findBuildings(int[] heights) {
        List<Integer> result = new ArrayList<>();
        int maxHeight = Integer.MIN_VALUE;
        for (int i = heights.length - 1; i >= 0; i--) {
            if (heights[i] > maxHeight) {
                result.add(i);
                maxHeight = Math.max(maxHeight, heights[i]);
            }
        }
        int[] ans = new int[result.size()];
        int j = 0;
        for (int i = result.size() - 1; i >= 0; i--) {
            ans[j] = result.get(i);
            j++;
        }
        return ans;
    }

    /*
    Observation: The buildings with ocean view are finally in strictly decreasing order of height.
    i.e. monotonically decreasing, always decreasing

    "Streaming context"
    - buildings are constructed in Left to Right order
    - Output the buildings with an ocean view after every new construction

    With above approach the time complexity will be O(n^2) across all updates!
    Can you bring this down to linear time?

    When the nth building is constructed can we update the results for the first n - 1 buildings
    faster, instead of computing again?
    In this context, it optimal to use Stack as follows.
    Stack will hold the buildings with ocean view all the time.
     */
    public int[] findBuildings2(int[] heights) {
        Stack<Integer> stack = new Stack<>(); // monotonically decreasing stack
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] <= heights[i]) {
                stack.pop();
            }
            stack.push(i);
        }
        int[] ans = new int[stack.size()];
        int index = stack.size() - 1;
        while (!stack.isEmpty()) {
            ans[index--] = stack.pop();
        }
        return ans;
    }

    /*
    Online Stock Span

    Design an algorithm that collects daily price quotes for some stock and returns the span of that
    stock's price for the current day.

    The span of the stock's price in one day is the maximum number of consecutive days (starting
    from that day and going backward) for which the stock price was less than or equal to the price
     of that day.

    For example, if the prices of the stock in the last four days is [7,2,1,2] and the price of the
    stock today is 2, then the span of today is 4 because starting from today, the price of the stock
    was less than or equal 2 for 4 consecutive days.
    Also, if the prices of the stock in the last four days is [7,34,1,2] and the price of the stock
    today is 8, then the span of today is 3 because starting from today, the price of the stock was
    less than or equal 8 for 3 consecutive days.

    Implement the StockSpanner class:

    StockSpanner() Initializes the object of the class.
    int next(int price) Returns the span of the stock's price given that today's price is price.
     */
    static class StockSpanner {
        // Stack to store pairs of {price, span}
        Stack<int[]> stack;

        // Constructor initializes the stack
        public StockSpanner() {
            stack = new Stack<>();
        }

        /**
         * This method calculates the span of the stock's price for the current day.
         *
         * @param price: the stock's price for the current day
         * @return span of the stock's price for the current day
         */
        // TC: O(n)
        // SC: O(n)
        public int next(int price) {
            // Start the span count with 1 (for the current day itself)
            int span = 1;

            // Check if the stack is not empty and if the price at the top of the stack
            // is less than or equal to the current price
            while (!stack.isEmpty() && stack.peek()[0] <= price) {
                // If so, then add the span of the top price to the current span
                // This means the previous prices (days) are included in the current day's span
                span += stack.pop()[1];
            }

            // Push the current price and its span to the stack
            stack.push(new int[]{price, span});

            // Return the calculated span
            return span;
        }
    }

    /*
    Next Greater Element I

    The next greater element of some element x in an array is the first greater element that is
    to the right of x in the same array.

    You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset
    of nums2.

    For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and determine
    the next greater element of nums2[j] in nums2. If there is no next greater element, then the
    answer for this query is -1.

    Return an array ans of length nums1.length such that ans[i] is the next greater element as
    described above.
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        /*
        First compute:
        Given an array of int (here it is nums2), compute the next larger (to the right) value
        for every element in it in O(n) time.
         */
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> numberToNextGreaterMapping = new HashMap<>();
        for (int i = nums2.length - 1; i >= 0; i--) {
            // remove all smaller elements, as those will never be greater for next elements
            while (!stack.isEmpty() && stack.peek() <= nums2[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                numberToNextGreaterMapping.put(nums2[i], stack.peek()); // greater element is at the top
            } else {
                numberToNextGreaterMapping.put(nums2[i], -1); // -1 otherwise
            }
            stack.push(nums2[i]); // push current element in stack
        }

        // as nums1 is subset of nums2, fetch all next greater elements from map and return result
        int[] result = new int[nums1.length];
        int i = 0;
        for (int num : nums1) {
            result[i++] = numberToNextGreaterMapping.get(num);
        }
        return result;
    }

    /*
    Daily Temperatures

    Given an array of integers temperatures represents the daily temperatures,
    return an array answer such that answer[i] is the number of days you have to
    wait after the ith day to get a warmer temperature.

    If there is no future day for which this is possible, keep answer[i] == 0 instead.

    Example 1:
    Input: temperatures = [73,74,75,71,69,72,76,73]
    Output: [1,1,4,2,1,1,0,0]
     */
    // TIP: Go right to left, if you want to get NEXT larger value for current value
    // TC: O(n)
    // SC: O(n)
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<int[]> stack = new Stack<>(); // pair: [num, index]
        List<Integer> result = new ArrayList<>();
        for (int i = temperatures.length - 1; i >= 0; i--) {  // template to find next greater
            while (!stack.isEmpty() && stack.peek()[0] <= temperatures[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                result.add(stack.peek()[1] - i); // diff of indices = number of days
            } else {
                result.add(0);
            }
            // push number and it's index on stack
            stack.push(new int[]{temperatures[i], i});
        }

        // reverse the result, as we have to return values from left to right
        int[] ans = new int[result.size()];
        int k = result.size() - 1;
        for (int i = result.size() - 1; i >= 0; i--) {
            ans[k--] = result.get(i);
        }
        return ans;
    }

    /*
    Sum of Subarray Minimums

    Given an array of integers arr, find the sum of min(b), where b ranges over
    every (contiguous) subarray of arr. Since the answer may be large,
    return the answer modulo 10^9 + 7.

    Example 1:
        Input: arr = [3,1,2,4]
        Output: 17
        Explanation:
            Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
            Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
            Sum is 17.

     Brute force:
        Get the min value for each subarray
        Overall this approach will take quadratic time

     Goal: O(n)
     Decrease and conquer: (from right to left traversal)
     Question: How the current number contribute to the answer?
     */
    public int sumSubarrayMins(int[] arr) {
        int globalSum = 0;
        Stack<int[]> stack = new Stack<>(); // (value, index)
        int[] localAns = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            // computer the previous smaller value for arr[i]
            while (!stack.isEmpty() && stack.peek()[0] >= arr[i]) { // drop greater values
                stack.pop();
            }
            // process previous smaller value, find the span
            int span;
            if (!stack.isEmpty()) {
                span = i - stack.peek()[1];
                localAns[i] = localAns[stack.peek()[1]];
            } else {
                span = i + 1;
            }
            localAns[i] += span * arr[i];
            globalSum = (globalSum + localAns[i]) % 1000000007; // for overflow modulo 10^9 + 7
            stack.push(new int[]{arr[i], i});
        }
        return globalSum;
    }

    /*
    Monotonic Stack Pattern:
    When you have to find following for each element in the array ...

    Next greater element      -- scan from right to left
    Previous greater element  -- scan from left right
    Next smaller element      -- scan from right to left
    Previous smaller element  -- scan from left to right

     */

    /*
    Next Greater Element II

    Given a circular integer array nums
    (i.e., the next element of nums[nums.length - 1] is nums[0]),
    return the next greater number for every element in nums.

    The next greater number of a number x is the first greater number to
    its traversing-order next in the array, which means you could search circularly
    to find its next greater number. If it doesn't exist, return -1 for this number.

    Example 1:
        Input: nums = [1,2,1]
        Output: [2,-1,2]
        Explanation:
            The first 1's next greater number is 2;
            The number 2 can't find next greater number.
            The second 1's next greater number needs to search circularly, which is also 2.
    */
    public int[] nextGreaterElements(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                stack.pop();
            }
            stack.push(nums[i]);
        }
        // next greater element -- so scan right to left
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                stack.pop();
            }
            // process the current element
            if (!stack.isEmpty()) {
                result.add(stack.peek());
            } else {
                result.add(-1);
            }
            stack.push(nums[i]);
        }
        int[] ans = new int[result.size()];
        int k = 0;
        for (int i = result.size() - 1; i >= 0; i--) {
            ans[k++] = result.get(i);
        }
        return ans;
    }

    public int[] nextGreaterElements2(int[] nums) {
        int[] res = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 2 * nums.length - 1; i >= 0; --i) {
            while (!stack.empty() && nums[stack.peek()] <= nums[i % nums.length]) {
                stack.pop();
            }
            res[i % nums.length] = stack.empty() ? -1 : nums[stack.peek()];
            stack.push(i % nums.length);
        }
        return res;
    }

    /*
    Largest Rectangle in Histogram

    Given an array of integers heights representing the histogram's bar height
    where the width of each bar is 1,

    return the area of the largest rectangle in the histogram.
     */
    public int largestRectangleArea(int[] heights) {
        // pre-compute left span

        // pre-compute right span

        return 0;
    }

    /*
    Maximum Score of a Good Subarray

    You are given an array of integers nums (0-indexed) and an integer k.

    The score of a subarray (i, j) is defined as min(nums[i], nums[i+1], ..., nums[j]) * (j - i + 1).
    A good subarray is a subarray where i <= k <= j.

    Return the maximum possible score of a good subarray.
     */
    public int maximumScore(int[] nums, int k) {
        // pre-compute left span

        // pre-compute right span

        return 0;
    }

    /*
    Maximal Rectangle

    Given a rows x cols binary matrix filled with 0's and 1's,
    find the largest rectangle containing only 1's and return its area.
     */
    public int maximalRectangle(char[][] matrix) {
        return 0;
    }

}
