package main.java.graph;

import java.util.*;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list,
 * find the length of shortest transformation sequence from beginWord to endWord, such that:
 * <p>
 * Only one letter can be changed at a time.
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * Note:
 * <p>
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 * Example 1:
 * <p>
 * Input:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * <p>
 * Output: 5
 * <p>
 * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * Example 2:
 * <p>
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * <p>
 * Output: 0
 * <p>
 * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */
public class WordLadder {
    public static void main(String[] args) {
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        int len = ladderLength("hit", "cog", wordList);
        System.out.println("\nLength of the chain: " + len);
    }

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {

        Set<String> wordSet = new HashSet<>(wordList); // convert to set for efficiency
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);

        int cnt = 1;
        System.out.print(beginWord);
        while (!queue.isEmpty()) {
            int size = queue.size(); // size of the queue is changing inside loop
            // for all words of this level in queue
            for (int i = 0; i < size; i++) {
                char[] current = queue.poll().toCharArray();
                for (int j = 0; j < current.length; j++) {
                    char tmp = current[j];
                    // modify one letter at a time
                    for (char c = 'a'; c <= 'z'; c++) {
                        current[j] = c;
                        String next = new String(current);
                        if (wordSet.contains(next)) {
                            System.out.print(" " + next);
                            if (next.equals(endWord)) {
                                return cnt + 1;
                            }
                            queue.add(next);
                            wordSet.remove(next);
                        }
                    }
                    // undo for next letter change
                    current[j] = tmp;
                }
            }
            // round ends with one letter changed
            cnt++;
        }
        return 0;
    }

}
