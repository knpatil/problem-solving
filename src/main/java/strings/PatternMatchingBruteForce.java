package main.java.strings;

public class PatternMatchingBruteForce {

  public static void main(String[] args) {
    String text = "  Aran is best Arya";
    String pattern = "Aryan";

    int count = findOccurrences(text, pattern);
    System.out.println("Found " + pattern + " " + count + " times.");
  }

  private static int findOccurrences(String text, String pattern) {
    int n = text.length();
    int m = pattern.length();
    int count = 0;
    for (int i = 0; i <= n - m; i++) {
      boolean found = true;
      int j;
      for (j = 0; j < m; j++) {
        if (text.charAt(i + j) != pattern.charAt(j)) {
          found = false;
          break;
        }
      }
      if (found) {
        count++;
      }
    }
    return count;
  }

}
