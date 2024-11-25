package multiplicators;

import java.util.Random;

public abstract class AbstractMatrixMultiplication {

    protected static final int SIZE = 1024;
    private static final long SEED = 6834723L;

    protected final double[][] A = new double[SIZE][SIZE];
    protected final double[][] B = new double[SIZE][SIZE];
    protected final double[][] C = new double[SIZE][SIZE];

    private final Random random = new Random(SEED);

    public AbstractMatrixMultiplication() {
        initializeMatrices();
    }

    private void initializeMatrices() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                A[i][j] = random.nextDouble();
                B[i][j] = random.nextDouble();
                C[i][j] = 0.0;
            }
        }
    }

    public abstract double execute();
}
