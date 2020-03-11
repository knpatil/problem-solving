package main.java.recursion;

import java.io.File;
import java.util.Objects;
import java.util.Scanner;

public class DirectoryCrawler {

  public static void main(String[] args) {
    Scanner console = new Scanner(System.in);
    System.out.println("Enter directory or file: ");
    String name = console.nextLine();
    File file = new File(name);
    if (file.exists()) {
      print(file, 0);
    } else {
      System.err.println("File or directory not found: " + file.getAbsolutePath());
    }
  }

  private static void print(File file, int level) {
    // for indentation
    for (int i = 0; i < level; i++) {
      System.out.print("  ");
    }
    System.out.println(file.getName()); // print name of file or directory
    if (file.isDirectory()) {
      for (File f : Objects.requireNonNull(file.listFiles())) {
        print(f, level + 1);
      }
    }
  }
}
