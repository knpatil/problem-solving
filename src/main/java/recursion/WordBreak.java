package main.java.recursion;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class WordBreak {

    private static HashMap<String, Boolean> cache = new HashMap<>();

    public static void main(String[] args) {
        List<String> wordDict = Arrays.asList("go", "goal", "goals", "special");
        String word = "goalspecial";

        String word2 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
        List<String> wordDict2 = Arrays.asList("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa");

        System.out.println("Can be broken: " + wordBreak(word2, wordDict2));
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        if (cache.containsKey(s)) {
            System.out.println("Getting value from cache: " + s);
            cache.get(s);
        }
        if (wordDict.contains(s)) {
            System.out.println("Adding to cached: " + s);
            cache.put(s, true);
            return true;
        }
        for (int i = 1; i < s.length(); i++) {
            String prefix = s.substring(0, i);
            if (wordDict.contains(prefix)) {
                System.out.println("Prefix matched: " + prefix + ", checking suffix ...");
                boolean ans = wordBreak(s.substring(i), wordDict);
                if (ans) {
                    System.out.println("Adding to cache : " + s.substring(i));
                    cache.put(s.substring(i), true);
                    return true;
                }
            }
        }
        System.out.println("Adding to cache: " + s);
        cache.put(s, false);
        return false;
    }

}