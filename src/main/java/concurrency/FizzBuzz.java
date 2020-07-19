package main.java.concurrency;

import java.util.function.IntConsumer;

class FizzBuzz {

    private int n;
    private int num = 1;

    public FizzBuzz(int n) {
        this.n = n;
    }

    public synchronized void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (num <= n) {
            if (num % 15 == 0) {
                printFizzBuzz.run();
                num++;
                notifyAll();
            } else {
                wait();
            }
        }
    }

    public synchronized void fizz(Runnable printFizz) throws InterruptedException {
        while (num <= n) {
            if (num % 3 == 0 && num % 5 != 0) {
                printFizz.run();
                num++;
                notifyAll();
            } else {
                wait();
            }
        }
    }

    public synchronized void buzz(Runnable printBuzz) throws InterruptedException {
        while (num <= n) {
            if (num % 3 != 0 && num % 5 == 0) {
                printBuzz.run();
                num++;
                notifyAll();
            } else {
                wait();
            }
        }
    }

    public synchronized void number(IntConsumer printNumber) throws InterruptedException {
        while (num <= n) {
            if (num % 3 != 0 && num % 5 != 0) {
                printNumber.accept(num);
                num++;
                notifyAll();
            } else {
                wait();
            }
        }
    }

    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new FizzBuzz(18);

        Runnable printFizz = () -> System.out.println("fizz");
        Runnable printBuzz = () -> System.out.println("buzz");
        Runnable printFizzBuzz = () -> System.out.println("fizzbuz");
        IntConsumer number = System.out::println;

        Thread A = new Thread(() -> {
            try {
                fizzBuzz.fizz(printFizz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread B = new Thread(() -> {
            try {
                fizzBuzz.buzz(printBuzz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread C = new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz(printFizzBuzz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread D = new Thread(() -> {
            try {
                fizzBuzz.number(number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        A.start();
        B.start();
        C.start();
        D.start();
    }
}