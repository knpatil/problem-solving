package main.java.leetcode.kotlin

/*
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right
which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example:
Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7

Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 */
fun main() {
    val grid: Array<IntArray> = Array(3) { IntArray(3) }
    // [[1,3,1],[1,5,1],[4,2,1]]
    grid[0] = intArrayOf(1, 3, 1)
    grid[1] = intArrayOf(1, 5, 1)
    grid[2] = intArrayOf(4, 2, 1)

    val minSum = MinimumPathSum().minPathSum(grid)
    println("Minimum path sum is $minSum")

    val minSum2 = MinimumPathSum().minPathSumDP(grid)
    println("Minimum path sum is $minSum2")
}

class MinimumPathSum {
    fun minPathSum(grid: Array<IntArray>): Int {
        return minimumPathSum(grid, grid.size - 1, grid[0].size - 1)
    }

    /* recursive solution */

    private fun minimumPathSum(grid: Array<IntArray>, row: Int, col: Int): Int {
        if (row == 0 && col == 0)
            return grid[row][col]      // base case of recursion

        if (row == 0)  // if first row, we can only move vertically
            return grid[row][col] + minimumPathSum(grid, row, col - 1)

        if (col == 0) // first column, only move horizontally
            return grid[row][col] + minimumPathSum(grid, row - 1, col)

        return grid[row][col] +
                minOf(minimumPathSum(grid, row - 1, col), (minimumPathSum(grid, row, col - 1)))
    }

    /*
    Simply store the results of every single calculation, so that we do not calculate same thing again and again.
    Note: If you can not alter the same grid, use another 2D array to hold the result.
     */

    fun minPathSumDP(grid: Array<IntArray>): Int {
        val height = grid.size
        val width = grid[0].size

        for (row in 0 until height) {
            for (col in 0 until width) {
                if (row == 0 && col == 0)
                    grid[row][col] = grid[row][col]
                else if (row == 0 && col != 0)
                    grid[row][col] = grid[row][col] + grid[row][col - 1]
                else if (col == 0 && row != 0)
                    grid[row][col] = grid[row][col] + grid[row - 1][col]
                else
                    grid[row][col] = grid[row][col] + minOf(grid[row - 1][col], (grid[row][col - 1]))
            }
        }

        return grid[height - 1][width - 1]
    }

}