package main.java.leetcode.kotlin

import kotlin.test.assertEquals

/**
Given a string containing only three types of characters: '(', ')' and '*',
write a function to check whether this string is valid. We define the validity of a string by these rules:

Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.

An empty string is also valid.

Example 1:
Input: "()"
Output: True

Example 2:
Input: "(*)"
Output: True

Example 3:
Input: "(*))"
Output: True
 */

fun main() {
    var isValid = checkValidString("()")
    println("isValid = $isValid")
    assertEquals(isValid, true)

    isValid = checkValidString("(*)")
    println("isValid = $isValid")
    assertEquals(isValid, true)

    isValid = checkValidString("(*))")
    println("isValid = $isValid")
    assertEquals(isValid, true)

    isValid = checkValidString(")(")
    println("isValid = $isValid")
    assertEquals(isValid, false)

    isValid = checkValidString(")))))(((((")
    println("isValid = $isValid")
    assertEquals(isValid, false)
}

fun checkValidString(s: String): Boolean {
    var mustBePaired = 0    // count of all open parenthesis which MUST be paired
    var couldBePaired = 0   // count of all open parenthesis that COULD be paired
    for (c in s) {
        when (c) {
            '(' -> {
                couldBePaired++
                mustBePaired++
            }
            ')' -> {
                couldBePaired--
                mustBePaired = (mustBePaired - 1).coerceAtLeast(0)
            }
            '*' -> {
                couldBePaired += 1
                mustBePaired = (mustBePaired - 1).coerceAtLeast(0)
            }
        }
        // short circuit if ) appears before (
        if (couldBePaired < 0)   // can't be less than 0
            return false
    }
    return mustBePaired == 0
}


