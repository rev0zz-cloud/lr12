package lr12;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Example6 {
    public static int sumArray(int[] array) {
        int numThreads = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        int chunkSize = array.length / numThreads;
        int[] partialSums = new int[numThreads];

        // Создаем и запускаем потоки для суммирования частей массива
        for (int i = 0; i < numThreads; i++) {
            int startIndex = i * chunkSize;
            int endIndex = (i == numThreads - 1) ? array.length : (i + 1) * chunkSize;

            final int start = startIndex;
            final int end = endIndex;

            executor.execute(() -> {
                int partialSum = 0;
                for (int j = start; j < end; j++) {
                    partialSum += array[j];
                }
                partialSums[start / chunkSize] = partialSum;
            });
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Складываем частичные суммы, чтобы получить общую сумму массива
        int totalSum = 0;
        for (int partialSum : partialSums) {
            totalSum += partialSum;
        }

        return totalSum;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("Array: " + Arrays.toString(array));
        int sum = sumArray(array);
        System.out.println("Sum of the array: " + sum);
    }
}