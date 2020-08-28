package main;

/*

We have a collection of stones, each stone has a positive integer weight.
Each turn, we choose the two heaviest stones and smash them together.  Suppose the stones have weights x and y with x <= y.  The result of this smash is:
If x == y, both stones are totally destroyed;
If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x.
At the end, there is at most 1 stone left.  Return the weight of this stone (or 0 if there are no stones left.)

Example 1:
Input: [2,7,4,1,8,1]

Output: 1
Explanation:
We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,
we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,
we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
we combine 1 and 1 to get 0 so the array converts to [1] then that's the value of last stone.

Note:
1 <= stones.length <= 30
1 <= stones[i] <= 1000

 */

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class LastStoneWeight {
    public static void main(String[] args) {
        int[] stones = {2, 7, 4, 1, 8, 1};

        int lastStone = lastStoneWeight(stones);
        System.out.println("Last Stone Weight: " + lastStone);

        lastStone = lastStoneWeight2(stones);
        System.out.println("Last Stone Weight: " + lastStone);
    }

    public static int lastStoneWeight(int[] stones) {
        if (stones == null || stones.length == 0) {
            return 0;
        }

        // max heap with reverse order, default is min heap
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int n : stones) {
            maxHeap.offer(n);
        }

        // compare two largest elements
        while (maxHeap.size() > 1) {
            int firstNum = maxHeap.poll();
            int secondNum = maxHeap.poll();
            if (firstNum != secondNum) {
                int diff = Math.abs(firstNum - secondNum);
                maxHeap.offer(diff);
            }
        }

        // return last element or 0
        return maxHeap.size() == 1 ? maxHeap.poll() : 0;
    }

    // Using sorting in place
    public static int lastStoneWeight2(int[] stones) {
        if (stones == null || stones.length == 0) {
            return 0;
        }

        int i = stones.length - 1;
        if (i == 0) {
            return stones[0];
        }

        Arrays.sort(stones);
        while (stones[i - 1] != 0) {
            stones[i - 1] = Math.abs(stones[i] - stones[i - 1]);
            stones[i] = 0;
            Arrays.sort(stones);
        }

        return stones[i];
    }
}

