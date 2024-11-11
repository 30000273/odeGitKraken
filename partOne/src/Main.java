import java.util.concurrent.atomic.AtomicLong;
public class Main {
    // Shared variable to store the total count
    private static final AtomicLong total = new AtomicLong(0);
    private static final int THREAD_COUNT = 1000;

    public static void main(String[] args) {
        // Array to store the threads
        Thread[] threads = new Thread[THREAD_COUNT];

        // Create 1000 threads
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(() -> {
                long count = countToOneMillion();
                total.addAndGet(count);
            });
            threads[i].start();
        }

        // Wait for all threads to finish
        for (int i = 0; i < THREAD_COUNT; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted: " + e.getMessage());
            }
        }

        // Print the total count
        System.out.println("Total count from all threads: " + total.get());
    }

    // Method to count numbers between 1 and 1 million
    private static long countToOneMillion() {
        long count = 0;
        for (int i = 1; i <= 1_000_000; i++) {
            count++;
        }
        return count;
    }
}