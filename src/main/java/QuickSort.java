package main.java;

import java.util.Arrays;

public class QuickSort {
  private int[] arr;

  public int[] sort(int[] arr) {
    this.arr = arr;
    quicksort(0, arr.length - 1);
    return arr;
  }

  // from to to is region to sort
  private void quicksort(int from, int to) {
    if (from >= to)
      return;

    // pivot
    int value = arr[to]; // last element as pivot
    int counter = from;

    // partition, if smaller than pivot move to beginning of list
    for (int i = from; i < to; i++) {
      if (arr[i] <= value) {
        swap(i, counter);
        counter++;
      }
    }
    // now counter holds the first thing larger than pivot value
    swap(counter, to); // to is where the pivot is
    // quicksort left and right
    quicksort(from, counter - 1);
    quicksort(counter + 1, to);
  }

  private void swap(int from, int to) {
    int tmp = arr[from];
    arr[from] = arr[to];
    arr[to] = tmp;
  }

  public static void main(String[] args) {
    QuickSort q = new QuickSort();
    int[] nums = {8, -1, 0, 23, 7, 3, 19, 4};
    System.out.println(Arrays.toString(nums));
    int[] sorted = q.sort(nums);
    System.out.println(Arrays.toString(sorted));
  }
}

