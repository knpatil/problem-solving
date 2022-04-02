package main.java;

public class StringPermutations {

  public static void main(String[] args) {
    permutations("XYZ".toCharArray(), 0);
  }

  private static void permutations(char[] chArray, int currentIndex) {

    System.out.println("Permutations called for " + String.valueOf(chArray) + " at index " + currentIndex);

    // base case
    if ( currentIndex == chArray.length - 1) {
      System.out.println(String.valueOf(chArray));
    } else {
      // recursive step, swap index and index + 1
      for (int i = currentIndex; i < chArray.length; i++) {
        swap(chArray, currentIndex, i);
        permutations(chArray, currentIndex + 1);
        swap(chArray, currentIndex, i);
      }
    }
  }

  private static void swap(char[] chArray, int currentIndex, int i) {
    char temp = chArray[currentIndex];
    chArray[currentIndex] = chArray[i];
    chArray[i] = temp;
  }

}
