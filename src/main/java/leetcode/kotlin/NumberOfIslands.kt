package main.java.leetcode.kotlin

/*

Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
You may assume all four edges of the grid are all surrounded by water.

--------

Example 1:

Input:
11110
11010
11000
00000

Output: 1

--------

Example 2:

Input:
11000
11000
00100
00011

Output: 3

--------

 */

fun main() {
    val grid: Array<CharArray> = Array(4) { CharArray(5) }
    grid[0] = charArrayOf('1', '1', '1', '1', '0')
    grid[1] = charArrayOf('1', '1', '0', '1', '0')
    grid[2] = charArrayOf('1', '1', '0', '0', '0')
    grid[3] = charArrayOf('0', '0', '0', '0', '0')

    val count = NumberOfIslands().numIslands(grid)
    println("Number of islands = $count")

    // [["1","1","0","0","0"],["1","1","0","0","0"],["0","0","1","0","0"],["0","0","0","1","1"]]
    val grid2: Array<CharArray> = Array(4) { CharArray(5) }
    grid2[0] = charArrayOf('1', '1', '0', '0', '0')
    grid2[1] = charArrayOf('1', '1', '0', '0', '0')
    grid2[2] = charArrayOf('0', '0', '1', '0', '0')
    grid2[3] = charArrayOf('0', '0', '0', '1', '1')

    val count2 = NumberOfIslands().numIslands(grid2)
    println("Number of islands = $count2")
}

class NumberOfIslands {
    fun numIslands(grid: Array<CharArray>): Int {
        var noOfIslands = 0

        val m = grid.size     // no of rows
        if ( m == 0)   // empty grid
            return noOfIslands

        val n = grid[0].size  // no of cols

        // println("M=$m, N=$n")

        val visited = Array(m) { BooleanArray(n) { false } }

        for (i in 0 until m) {
            for (j in 0 until n) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    checkNeighbours(grid, visited, i, j)
                    noOfIslands++
                }
            }
        }

        return noOfIslands
    }

    private fun checkNeighbours(grid: Array<CharArray>, visited: Array<BooleanArray>, i: Int, j: Int) {
        val m = grid.size
        val n = grid[0].size

        if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j] || grid[i][j] != '1')
            return

        visited[i][j] = true // mark this entry as visited

        // try all possibilities
        checkNeighbours(grid, visited, i + 1, j)
        checkNeighbours(grid, visited, i - 1, j)
        checkNeighbours(grid, visited, i, j + 1)
        checkNeighbours(grid, visited, i, j - 1)
    }
}

















