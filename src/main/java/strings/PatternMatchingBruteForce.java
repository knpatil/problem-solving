package main.java.strings;

public class PatternMatchingBruteForce {

  public static void main(String[] args) {
    String text = "the man of the month is the man of the month.";
    String pattern = "the";

    int count = findOccurrences(text, pattern);
    System.out.println("No of occurrences: " + count);
  }

  private static int findOccurrences(String text, String pattern) {
    int count = 0;
    for (int i = 0; i < text.length() + 1 - pattern.length(); i++) {
      boolean found = true;
      int k = 0;
      for (int j = i; j < i + pattern.length(); j++) {
        if (text.charAt(j) != pattern.charAt(k)) {
          found = false;
          break;
        }
        k++;
      }
      if (found) {
        count++;
      }
    }
    return count;
  }

}
