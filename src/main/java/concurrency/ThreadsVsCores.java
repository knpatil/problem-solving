package main.java.concurrency;

import java.util.concurrent.ForkJoinPool;

public class ThreadsVsCores {

    public static void main(String[] args) {
        System.out.println("Available Processors: " + Runtime.getRuntime().availableProcessors());

        // Get current size of heap in bytes
        System.out.println("Total Heap Size (Memory) in MB: " + (Runtime.getRuntime().totalMemory() / 1024) / 1024 );

        // Get maximum size of heap in bytes. The heap cannot grow beyond this size.
        // Any attempt will result in an OutOfMemoryException.
        System.out.println("Max Heap (Memory): " + (Runtime.getRuntime().maxMemory() / 1024) / 1024);

        // Get amount of free memory within the heap in bytes. This size will increase
        // after garbage collection and decrease as new objects are created.
        System.out.println("Free Heap (Memory): " + (Runtime.getRuntime().freeMemory() / 1024 ) / 1024);

        System.out.println("\nThreads Common Pool: " + ForkJoinPool.commonPool());
    }

}
