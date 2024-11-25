package multiplicators;

public class SequentialMatrixMultiplication extends AbstractMatrixMultiplication {

    @Override
    public double execute() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                for (int k = 0; k < SIZE; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C[0][0];
    }
}