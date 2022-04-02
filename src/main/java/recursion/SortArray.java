package main.java.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortArray {

  public static void main(String[] args) {
    int[] arr = {4, 2, 5, -1, 3, 6, 0, -10, -9, 200, 4, 1};
//    int start = 0;
//    int end = arr.length - 1;
//    // mergeSort(arr, start, end); // Not WORKING
    List<Integer> list = new ArrayList<>(arr.length);
    for (int n: arr) {
      list.add(n);
    }
    sortArray(list);  // recursive function
    System.out.println(list);
  }

  private static void sortArray(List<Integer> list) {
    if (list.size() == 1)
      return;
    int last = list.remove(list.size() - 1); // break list in two parts, 0 to n - 1 and n
    sortArray(list);  // call sort recursively over n - 1 elements
    insert(list, last);  // insert last element into sorted n - 1 elements
  }

  private static void insert(List<Integer> list, int last) {
    int size = list.size();
    if (size == 0 || list.get(size - 1) <= last) {
      list.add(last);
    } else {
      int val = list.remove(size - 1);
      insert(list, last);
      list.add(val); // value is largest, so add it in the end
    }
  }

  private static void mergeSort(int[] arr, int start, int end) {
    if (start < end) {
      int mid = (start + end ) / 2;
      mergeSort(arr, start, mid);
      mergeSort(arr, mid + 1, end);
      merge(arr, start, mid, mid + 1, end); // pass start & end indexes of both halfs
    }
  }

  private static void merge(int[] arr, int i, int mid, int j, int end) {
    System.out.println("i = " + i + ", mid = " + mid + ", j = " + j +", end = " + end);
    int[] tmp = new int[end + 1];
    int k = 0; // index for tmp
    while (i <= mid && j <= end) {
      if (arr[i] <= arr[j]) {
        System.out.println("k = " + k + ", i = " + i);
        tmp[k] = arr[i];
        i++;
      }  else {
        tmp[k] = arr[j];
        j++;
      }
      k++;
    }

    if (i <= mid) {
      while (k <= end) {
        tmp[k] = arr[i];
        k++;
        i++;
      }
    }

    if (j <= end) {
      while (k <= end) {
        System.out.println("k = " + k +", j = " + j);
        tmp[k] = arr[j];
        k++;
        j++;
      }
    }

    // copy to arr
    if (end + 1 >= 0)
      System.arraycopy(tmp, 0, arr, 0, end + 1);
  }

}
