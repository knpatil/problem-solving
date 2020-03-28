package main.java.recursion;

import java.util.*;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 * add spaces in s to construct a sentence where each word is a valid dictionary word.
 * Return all such possible sentences.
 * <p>
 * Note:
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * Output:
 * [
 * "cats and dog",
 * "cat sand dog"
 * ]
 * <p>
 * Example 2:
 * <p>
 * Input:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * Output:
 * [
 * "pine apple pen apple",
 * "pineapple pen apple",
 * "pine applepen apple"
 * ]
 */

public class WordBreakII {

    private static HashMap<Integer, List<String>> map;

    public static void main(String[] args) {
        String s = "pineapplepenapple";
        List<String> wordDict = Arrays.asList("apple", "pen", "applepen", "pine", "pineapple");

        String s2 = "aaaaaa";
        List<String> wordDict2 = Arrays.asList("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa");

        List<String> output = wordBreakII(s2, wordDict2);

        System.out.println("OUTPUT: " + output);
        for(Map.Entry<Integer, List<String>> e: map.entrySet()) {
            System.out.println(e.getKey() + " : " + e.getValue());
        }
    }

    public static List<String> wordBreakII(String s, List<String> wordDict) {
        map = new HashMap<>();
        return wordBreakHelper(s, s.length(), wordDict);
    }

    private static List<String> wordBreakHelper(String input, int end, List<String> wordDict) {
        ArrayList<String> res = new ArrayList<>();

        // We have traversed the input and are done with breaking up the word
        if (end == 0) return new ArrayList<>(Collections.singletonList(""));

        // Using dynamic programming for optimisation
        // where the same words will have to broken down again
        if (map.containsKey(end)) {
            return map.get(end);
        }

        // Keeping the end at the end of the input
        // start counter moves along the input letters
        for (int start = 0; start < end; start++) {
            String sub = input.substring(start, end);
            if (wordDict.contains(sub)) {
                // Once the last word in the input is found in the dictionary,
                // we repeat the wordbreak
                List<String> tmpList = wordBreakHelper(input, start, wordDict);
                // and append the 'sub' at the end of every phrase in tmpList
                for (String tmpStr : tmpList) {
                    res.add(tmpStr.length() == 0 ? sub : tmpStr + " " + sub);
                }
            }
        }
        map.put(end, res);
        return res;
    }


}
