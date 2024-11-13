import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) {
        int numThreads = 1000;

        Callable<Long> callableTask = () ->
        {
            Long count = 0L;
            for (int i = 0; i < 1000000; i++) {
                count++;
            }
            return count;
        };

        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        List<Future<Long>> futures = new ArrayList<>();

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < numThreads; i++) {
            futures.add(executorService.submit(callableTask));
        }

        long totCount = 0;
        for (Future<Long> future : futures) {
            try {
                totCount += future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis();
        long totTime = endTime - startTime;

        System.out.println("Total " + totCount);
        System.out.println("Time: " + totTime);

        executorService.shutdown();
    }
}