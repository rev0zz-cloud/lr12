package lr12;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Example5 {
    public static int findMax(int[] array) {
        int numThreads = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        int chunkSize = array.length / numThreads;
        AtomicInteger max = new AtomicInteger(Integer.MIN_VALUE);

        CompletableFuture<?>[] futures = new CompletableFuture[numThreads];
        for (int i = 0; i < numThreads; i++) {
            int startIndex = i * chunkSize;
            int endIndex = (i == numThreads - 1) ? array.length : (i + 1) * chunkSize;

            final int start = startIndex;
            final int end = endIndex;

            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                int localMax = IntStream.range(start, end).map(idx -> array[idx]).max().orElse(Integer.MIN_VALUE);
                max.updateAndGet(curMax -> Math.max(curMax, localMax));
            }, executor);
            futures[i] = future;
        }

        CompletableFuture.allOf(futures).join();

        executor.shutdown();
        return max.get();
    }

    public static void main(String[] args) {
        int[] array = {1, 3, 7, 5, 9, 2, 4, 6, 8, 10};

        int max = findMax(array);
        System.out.println("Max element in the array: " + max);
    }
}