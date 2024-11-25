package multiplicators;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

public class ForkJoinMatrixMultiplication extends AbstractMatrixMultiplication {

    private static final int THRESHOLD = 64;

    private final int threadCount;

    public ForkJoinMatrixMultiplication(int threadCount) {
        this.threadCount = threadCount;
    }

    public double execute() {
        try(final ForkJoinPool pool = new ForkJoinPool(threadCount)) {
            pool.invoke(new MatrixMultiplicationTask(A, B, C, 0, SIZE));
            pool.shutdown();
        }
        return C[0][0];
    }


    static class MatrixMultiplicationTask extends RecursiveAction {
        private final double[][] A, B, C;
        private final int startRow, endRow;

        public MatrixMultiplicationTask(double[][] A, double[][] B, double[][] C, int startRow, int endRow) {
            this.A = A;
            this.B = B;
            this.C = C;
            this.startRow = startRow;
            this.endRow = endRow;
        }

        @Override
        protected void compute() {
            int rows = endRow - startRow;
            if (rows <= THRESHOLD) {
                for (int i = startRow; i < endRow; i++) {
                    for (int j = 0; j < SIZE; j++) {
                        for (int k = 0; k < SIZE; k++) {
                            C[i][j] += A[i][k] * B[k][j];
                        }
                    }
                }
            } else {
                int midRow = startRow + rows / 2;
                MatrixMultiplicationTask task1 = new MatrixMultiplicationTask(A, B, C, startRow, midRow);
                MatrixMultiplicationTask task2 = new MatrixMultiplicationTask(A, B, C, midRow, endRow);
                invokeAll(task1, task2);
            }
        }
    }
}
