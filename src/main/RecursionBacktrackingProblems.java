import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecursionBacktrackingProblems {

    public static void main(String[] args) {
        /*
        List<String> result = generateBinaryStringsRecursive(3);
        System.out.println("Size = " + result.size() + ",List = " + result);

        List<String> result2 = generateBinaryStringsIterative(3);
        System.out.println("Size = " + result2.size() + ",List = " + result2);

        generalStrategyToEnumerateBinaryStrings();

        List<String> result3 = genDecimalStrings();
        System.out.println("Size = " + result3.size() + ",List = " + result3);

        List<String> result4 = permutationsWithoutRepetition();
        System.out.println("Size = " + result4.size() + ",List = " + result4);

        List<List<Integer>> result5 = genAllSubsets();
        System.out.println("Size = " + result5.size() + ",List = " + result5);

        List<String> result6 = letterCasePermutation("a1b2");
        System.out.println("Size = " + result6.size() + ",List = " + result6);
        */

        int[] nums = {8, 3, 10, 4, 12, 5, 2, 9};
        int targetSum = 12;
        List<List<Integer>> result7 = subsetSum(nums, targetSum);
        System.out.println("Size = " + result7.size() + ", List = " + result7);
    }

    private static List<String> generateBinaryStringsIterative(int n) {
        List<String> result = Arrays.asList("0", "1"); // bottom up, when n = 1
        for (int i = 2; i <= n; i++) { // compute for i = 2 to n
            List<String> newResult = new ArrayList<>();
            for (String str: result) {
                newResult.add(str + "0");
                newResult.add(str + "1");
            }
            result = newResult;
        }
        return result;
    }

    // generate all possible binary strings of length n
    private static List<String> generateBinaryStringsRecursive(int n) {
        // base case
        if (n == 1) {
            return Arrays.asList("0", "1");
        }
        // Decrease and conquer: reduce the problem size by 1
        List<String> partialResult = generateBinaryStringsRecursive(n - 1);
        List<String> result = new ArrayList<>();
        for (String str: partialResult) {
            result.add(str + "0");
            result.add(str + "1");
        }
        return result;
    }

    // General strategy (template) for combinatorial enumeration or exhaustive search using DFS
    // Time Complexity : 2^n
    // Space Complexity: O(n) (height of recursion tree or call stack)
    private static void generalStrategyToEnumerateBinaryStrings() {
        List<String> result = genBinaryStrings(3);
        System.out.println("Size = " + result.size() + ",List = " + result);
    }

    private static List<String> genBinaryStrings(int n) {
        List<String> result = new ArrayList<>();
        genBSHelper(n, "", result);
        return result;
    }

    // exhaustive search template
    private static void genBSHelper(int n, String partialResult, List<String> result) {
        // base case
        if (n == 0) {
            result.add(partialResult);
        } else {
            // recursive case
            genBSHelper(n - 1, partialResult + "0", result); // new string 1
            genBSHelper(n - 1, partialResult + "1", result); // new string 2
        }
    }

    // print all decimal strings of length n
    // 0 ... 9
    // 10 x 10 x 10  <--- 10 choices at each position -- 10 ^ 3 for n = 3
    // time complexity 10^n
    private static List<String> genDecimalStrings() {
        List<String> result = new ArrayList<>();
        genDSHelper(3, "", result);
        return result;
    }

    private static void genDSHelper(int n, String partialResult, List<String> result) {
        // base case
        if (n == 0) {
            result.add(partialResult);
        } else {  // recursive case
            for (int i = 0; i <= 9; i++) {
                genDSHelper(n - 1, partialResult + i, result);
            }
        }
    }

    /*
    Given a set (or String) with n distinct characters,
        print all permutations (of size n, no repetitions allowed)

    Total size = n !
    n, n-1, n-2, ..... 1 choices at each position
    Time Complexity = n! x n
    Space Complexity = n (height of recursion tree or call stack)
     */

    public static List<String> permutationsWithoutRepetition() {
        List<String> result = new ArrayList<>();
        permutationHelper("", "abc", result);
        return result;
    }

    private static void permutationHelper(String partialResult, String charsRemaining, List<String> result) {
        // base case
        if (charsRemaining.length() == 0) {
            result.add(partialResult);
        } else {
            // recursive case
            for (int i = 0; i < charsRemaining.length(); i++) {
                String charsRemainingAfterI = charsRemaining.substring(0, i) + charsRemaining.substring(i + 1);
                permutationHelper(partialResult + charsRemaining.charAt(i), charsRemainingAfterI, result);
            }
        }
    }

    /*
    Given a set of n distinct numbers, enumerate all its subsets.
    e.g.
    if S = {1, 2, 3}, then
    output = {}, {1}, {2}, {3}, {1, 2}, {2, 3}, {3, 1}, {1, 2, 3}


     */
    public static List<List<Integer>> genAllSubsets() {
        List<List<Integer>> result = new ArrayList<>();
        int[] nums = {1, 2, 3};
        // subsetHelper(new ArrayList<>(), nums, 0, result);
        subsetHelperMutable(new ArrayList<>(), nums, 0, result);
        return result;
    }

    private static void subsetHelper(List<Integer> partialResult, int[] nums,
                                     int startIndex, List<List<Integer>> result) {
        // base case
        if (startIndex == nums.length) {
            result.add(new ArrayList<>(partialResult));
        } else {
            // recursive case
            // exclude
            subsetHelper(new ArrayList<>(partialResult), nums, startIndex + 1, result);

            // include
            partialResult.add(nums[startIndex]);
            subsetHelper(new ArrayList<>(partialResult), nums, startIndex + 1, result);
        }
    }

    private static void subsetHelperMutable(List<Integer> partialResult, int[] nums,
                                     int startIndex, List<List<Integer>> result) {
        // base case
        if (startIndex == nums.length) {
            result.add(new ArrayList<>(partialResult));
        } else {
            // recursive case
            // exclude
            subsetHelperMutable(partialResult, nums, startIndex + 1, result);

            // include
            partialResult.add(nums[startIndex]);
            subsetHelperMutable(partialResult, nums, startIndex + 1, result);
            partialResult.remove(partialResult.size() - 1);
        }
    }


    /*
    Letter Case Permutation

    Given a string s, you can transform every letter individually to be lowercase or uppercase to create another string.
    Return a list of all possible strings we could create. Return the output in any order.

    Example 1:
    Input: s = "a1b2"
    Output: ["a1b2","a1B2","A1b2","A1B2"]
     */
    // Time Complexity: 2^n . n
    // Space Complexity: O(n^2) <-- immutable strings in sub-problems, we are creating new string for every call
    public static List<String> letterCasePermutation(String s) {
        List<String> result = new ArrayList<>();
        String partialResult = "";
        // pass: partial solution, sub-problem definition
        // sub problem def can be combination of multiple variables: e.g. s and startIndex
        letterCasePermutationHelper(partialResult, s, 0, result);
        return result;
    }

    private static void letterCasePermutationHelper(String partialResult, String s, int index, List<String> result) {
        // base case: sub-problem size is 0
        if (index == s.length()) {
            result.add(partialResult); // add this result to global result
        } else {
            // recursive case: reduce the problem size by computing partialResult at this stage
            if (Character.isDigit(s.charAt(index))) {
                letterCasePermutationHelper(partialResult + s.charAt(index), s, index + 1, result);
            } else {
                letterCasePermutationHelper(partialResult + Character.toLowerCase(s.charAt(index)), s,
                        index + 1, result);
                letterCasePermutationHelper(partialResult + Character.toUpperCase(s.charAt(index)), s,
                        index + 1, result);
            }
        }
    }

    // use StringBuilder for partialResult instead of String
    // space complexity is O(n) now
    public static List<String> letterCasePermutationMutableParameter(String s) {
        List<String> result = new ArrayList<>();
        StringBuilder partialResult = new StringBuilder();
        // pass: partial solution, sub-problem definition
        // sub problem def can be combination of multiple variables: e.g. s and startIndex
        // follow the format: sub-problem def, partial result, result etc.
        letterCasePermutationHelper2(s, 0, partialResult, result);
        return result;
    }

    private static void letterCasePermutationHelper2(String s, int index, StringBuilder partialResult,
                                                     List<String> result) {
        // base case: sub-problem size is 0
        if (index == s.length()) {
            result.add(partialResult.toString());
        } else {
            if (Character.isDigit(s.charAt(index))) {
                partialResult.append(s.charAt(index));
                letterCasePermutationHelper2(s, index + 1, partialResult, result);
                partialResult.deleteCharAt(partialResult.length() - 1); // any modification should be reversed
            } else {
                // add lowercase character to partial result
                partialResult.append(Character.toLowerCase(s.charAt(index)));
                letterCasePermutationHelper2(s, index + 1, partialResult, result);
                partialResult.deleteCharAt(partialResult.length() - 1); // remove this for backtracking

                // add uppercase character to partial result
                partialResult.append(Character.toUpperCase(s.charAt(index)));
                letterCasePermutationHelper2(s, index + 1, partialResult, result);
                partialResult.deleteCharAt(partialResult.length() - 1); // remove this for backtracking
            }
        }
    }

    // Permutations I

    // Permutations II

    // Subsets I

    // Subsets II

    // Letter combination of a phone number

    // subset sum
    /*
    Given a set of non-negative integers and a value sum,
    the task is to check if there is a subset of the given
    set whose sum is equal to the given sum.

    Count the number of such subsets
     */
    public static List<List<Integer>> subsetSum(int[] nums, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        subsetSumHelper(nums, 0, targetSum, 0, new ArrayList<>(), result);
        return result;
    }

    private static void subsetSumHelper(int[] nums, int index, int targetSum, int currSum,
                                 ArrayList<Integer> currList, List<List<Integer>> result) {
        // backtracking cases
        if (currSum == targetSum) {
            result.add(new ArrayList<>(currList));
            return;
        } else if (currSum > targetSum) {
            return;
        }

        // base case: run out of numbers
        if (index == nums.length) {
            return;
        }

        // recursive case
        // exclude curr number
        subsetSumHelper(nums, index + 1, targetSum, currSum, currList, result);

        // include curr number
        currList.add(nums[index]);
        subsetSumHelper(nums, index + 1, targetSum, currSum + nums[index], currList, result);
        currList.remove(currList.size() - 1); // backtrack
    }


    // Combination Sum II


    /*
    Generate Parentheses

    Given n pairs of parentheses,
    write a function to generate all combinations of well-formed parentheses.

    Amount of parenthesis are equal to catalan number, exponential in nature

    Constraint here is -> well-formed

    e.g. If n = 5, and if we have done ()), then left = 4, and right = 3
    So the constraint violation is left > open, then do not proceed

    Time Complexity: O(nth catalan number), O(2^2n), O(n.4^n)
    Space Complexity: Explicit (results) Height of the tree n
            implicit stack space: O(n)

    b is the branching factor, h is the height
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        StringBuilder partialResult = new StringBuilder();
        genParenthesisHelper(n, n, partialResult, result);
        return result;
    }

    // numLeft = number of left parenthesis remaining
    // numRight = number of right parenthesis remaining
    private void genParenthesisHelper(int numLeft, int numRight, StringBuilder partialResult, List<String> result) {
        // backtracking case: stop proceeding because of constraint violation
        if (numLeft > numRight) {
            return;
        }

        // base case
        if (numLeft == 0 && numRight == 0) {
            result.add(partialResult.toString()); // add well-formed combination
        } else {
            // recursive case
            // Add (
            if (numLeft > 0) {
                partialResult.append("(");
                genParenthesisHelper(numLeft - 1, numRight, partialResult, result);
                partialResult.deleteCharAt(partialResult.length() - 1);
            }
            // Add )
            if (numRight > 0) {
                partialResult.append(')');
                genParenthesisHelper(numLeft, numRight - 1, partialResult, result);
                partialResult.deleteCharAt(partialResult.length() - 1);
            }
        }
    }

    /*
    N-Queens

    The n-queens puzzle is the problem of placing n queens on
    an n x n chessboard such that no two queens attack each other.

    Given an integer n, return all distinct solutions to the n-queens puzzle.
    You may return the answer in any order.

    Each solution contains a distinct board configuration of the n-queens' placement,
    where 'Q' and '.' both indicate a queen and an empty space, respectively.

    Is this a fill in the blank problem?

    Strategy:
        At a top level, I will place one queen in the first row, then ask another worker to take care
        of remaining queens.

    Constraints: rules of the chess

    Strategy:
        Every queen will go in a separate row. Hence, each worker focuses on placing queen on one row.
     */
    public List<List<String>> solveNQueens(int n) {
        /*
        q0, q1 .... qn-1

        1. PS -- partial solution

        2. SD -- sub-problem definition
            - How many queens remains, which position?
            - Start placing ith queen

            cols = cols for queens 0 .. i-1
         */
        List<List<String>> result = new ArrayList<>();
        List<String> partialResult = new ArrayList<>();
        solveNQueensHelper(0, n, partialResult, result);
        return result;
    }

    private void solveNQueensHelper(int i, int n, List<String> partialResult, List<List<String>> result) {
        // Backtracking case: backtrack if there is a conflict on partialResult
        // We just have to detect conflict between Qi-1 & earlier queens
        // check row conflict, columns, diagonal conflict


        // base case
        if (i == n) {
            result.add(new ArrayList<>(partialResult));
        } else {
            for (int col = 0; col < n; col++) {
                // queen i to be placed in column = col
                partialResult.add(String.valueOf(col));
                solveNQueensHelper(i + 1, n, partialResult, result);
                partialResult.remove(partialResult.size() - 1);
            }

        }

    }


}
