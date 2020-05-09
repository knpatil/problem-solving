package main.java.leetcode.kotlin

/**
Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]
Constraint: It's guaranteed that the product of the elements of any prefix or suffix of the array (including the whole array) fits in a 32 bit integer.

Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
 */

fun main() {
    val nums = intArrayOf(1, 2, 3, 4)
    val prod = ProductExceptSelf()
    val result = prod.productExceptSelf(nums)
    result.forEach { print("$it ") }
}

class ProductExceptSelf {

    fun productExceptSelf(nums: IntArray): IntArray {
        // size of the input array must be greater than 1, in order to get product of
        // all numbers except itself
        if (nums.size < 2) {
            throw IllegalArgumentException("Size of the array is less than 2")
        }

        // create a result array of the same size as input array
        val productOfAllNumsExceptAtIndex = IntArray(nums.size)

        // start from first number
        // for each number, find the product of all other integers before it and store it at that index
        var productSoFar = 1;
        for (i in nums.indices) {
            productOfAllNumsExceptAtIndex[i] = productSoFar // store productSoFar at this index
            productSoFar *= nums[i]  // calculate new product including this number
        }

        // now, for each number, let us find the product of all numbers after it
        // since, at each index, we already have the product of all numbers before it
        // now, we wll be storing total product of all numbers except itself, in this loop

        productSoFar = 1
        // start from last number
        for (i in nums.size - 1 downTo 0) {
            productOfAllNumsExceptAtIndex[i] *= productSoFar
            productSoFar *= nums[i]
        }

        return productOfAllNumsExceptAtIndex
    }
}