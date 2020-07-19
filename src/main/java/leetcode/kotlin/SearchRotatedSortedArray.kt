package main.java.leetcode.kotlin

/*

Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4

Example 2:
Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1

 */

class SearchRotatedSortedArray {
    fun iterative(arr: IntArray, key: Int): Int {
        var low = 0
        var high = arr.size - 1
        while (low <= high) {
            val mid = low + (high - low / 2)
            if (arr[mid] == key) {    // target found
                return mid
            }
            // if left of midpoint is sorted
            if (arr[low] <= arr[mid]) {
                // check if key is present in left half
                if (arr[low] <= key && arr[mid] >= key)
                    high = mid - 1
                else
                    low = mid + 1  // otherwise, key is in right half
            } else {   // if right half after midpoint is sorted array
                // check if key is in the right half
                if (arr[mid] <= key && arr[high] >= key)
                    low = mid + 1
                else
                    high = mid - 1  // otherwise, check left half
            }
        }
        return -1
    }

    fun recursive(arr: IntArray, key: Int, low: Int, high: Int): Int {
        if (low > high)
            return -1 // not found

        // find a mid point as pivot
        val mid = low + (high - low) / 2
        if (arr[mid] == key)
            return mid // key found

        // if left of midpoint is sorted
        if (arr[low] <= arr[mid]) {

            // check if key is present in left half
            if (arr[low] <= key && arr[mid] >= key)
                return recursive(arr, key, low, mid - 1)
            else
                return recursive(arr, key, mid + 1, high)

        } else { // if right half after midpoint is sorted array
            // check if key is in the right half
            if (arr[mid] <= key && arr[high] >= key)
                return recursive(arr, key, mid + 1, high)
            else
                return recursive(arr, key, low, mid - 1)
        }
    }
}


fun main() {
    val nums = intArrayOf(4, 5, 6, 7, 0, 1, 2)
    val low = 0
    val high = nums.size - 1

    val search = SearchRotatedSortedArray()

    var index = search.iterative(nums, 5)
    println("Index of number 5 is $index")
    index = search.recursive(nums, 5, low, high)
    println("Index of number 5 is $index")

    index = search.iterative(nums, 4)
    println("Index of number 4 is $index")
    index = search.recursive(nums, 4, low, high)
    println("Index of number 4 is $index")

    index = search.iterative(nums, 2)
    println("Index of number 2 is $index")
    index = search.recursive(nums, 2, low, high)
    println("Index of number 2 is $index")

    index = search.iterative(nums, 0)
    println("Index of number 0 is $index")
    index = search.recursive(nums, 0, low, high)
    println("Index of number 0 is $index")
}