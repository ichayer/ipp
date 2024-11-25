package multiplicators;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorParallelMatrixMultiplication extends AbstractMatrixMultiplication {

    private final int threadCount;

    public ExecutorParallelMatrixMultiplication(int threadCount) {
        this.threadCount = threadCount;
    }

    public double execute() {

        try (final ExecutorService executor = Executors.newFixedThreadPool(threadCount)) {
            for (int i = 0; i < SIZE; i++) {
                final int row = i;
                executor.execute(() -> {
                        for (int j = 0; j < SIZE; j++) {
                            for (int k = 0; k < SIZE; k++) {
                                C[row][j] += A[row][k] * B[k][j];
                            }
                        }
                    }
                );
            }
            executor.shutdown();
            if(!executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS)) {
                System.err.println("Task did not finish in time");
            }
        } catch (InterruptedException e) {
            System.err.println("Execution interrupted: " + e.getMessage());
        }

        return C[0][0];
    }
}
