package main.java.concurrency;

public class FooBar {

  private int n;

  public FooBar(int n) {
    this.n = n;
  }

  public void foo(Runnable printFoo) throws InterruptedException {

    for (int i = 0; i < n; i++) {

      // printFoo.run() outputs "foo". Do not change or remove this line.
      printFoo.run();
    }
  }

  public void bar(Runnable printBar) throws InterruptedException {

    for (int i = 0; i < n; i++) {

      // printBar.run() outputs "bar". Do not change or remove this line.
      printBar.run();
    }
  }

  private static class Foo implements Runnable {

    @Override
    public void run() {
      System.out.print("foo");
    }
  }

  private static class Bar implements Runnable {

    @Override
    public void run() {
      System.out.print("bar");
    }
  }

  public static void main(String[] args) throws InterruptedException {
    FooBar fooBar = new FooBar(2);

    fooBar.foo(new Foo());
    fooBar.bar(new Bar());

  }


}