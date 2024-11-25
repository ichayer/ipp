import multiplicators.AbstractMatrixMultiplication;
import multiplicators.ExecutorParallelMatrixMultiplication;
import multiplicators.ForkJoinMatrixMultiplication;
import multiplicators.SequentialMatrixMultiplication;
import utils.JsonUtils;
import utils.MathUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

class Main {

    private static final int NUM_EXECUTIONS = 40;
    private static final int[] THREAD_COUNTS = {1, 2, 4, 8, 16, 32};

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        final Map<String, Object> results = new HashMap<>();
        results.put("Runs", NUM_EXECUTIONS);
        results.put("Sequential", runSequential());
        results.put("ExecutorService", runExecutorService());
        results.put("ForkJoin", runForkJoin());

        JsonUtils.writeResultsToJson(results, "results.json");
    }

    private static Map<Integer, Double> runSequential() {
        System.out.println("Running Sequential Implementation...");
        double[] times = new double[NUM_EXECUTIONS];

        for (int i = 0; i < NUM_EXECUTIONS; i++) {
            long startTime = System.currentTimeMillis();
            double result = new SequentialMatrixMultiplication().execute();
            long endTime = System.currentTimeMillis();
            times[i] = endTime - startTime;
            System.out.printf("Execution %d: Finished %f in %.2f ms%n", i + 1, result, times[i]);
        }
        double mean = MathUtils.mean(times);
        double stdDev = MathUtils.standardDeviation(times);
        System.out.printf("Mean Time: %.2f ms, Standard Deviation: %.2f ms%n%n", mean, stdDev);

        Map<Integer, Double> result = new HashMap<>();
        for (int threads : THREAD_COUNTS) {
            result.put(threads, mean); // The same average time for each thread
        }
        return result;
    }

    private static Map<Integer, Double> runImplementationWithThreads(Class<? extends AbstractMatrixMultiplication> implClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        final Map<Integer, Double> results = new HashMap<>();

        for (int threads : THREAD_COUNTS) {
            System.out.printf("Running %s Implementation with %d threads...%n", implClass.getSimpleName(), threads);
            double[] times = new double[NUM_EXECUTIONS];

            for (int i = 0; i < NUM_EXECUTIONS; i++) {
                long startTime = System.currentTimeMillis();
                final AbstractMatrixMultiplication instance = implClass.getConstructor(int.class).newInstance(threads);
                double result = instance.execute();
                long endTime = System.currentTimeMillis();
                times[i] = endTime - startTime;
                System.out.printf("Execution %d: Finished %f in %.2f ms%n", i + 1, result, times[i]);
            }
            double mean = MathUtils.mean(times);
            double stdDev = MathUtils.standardDeviation(times);
            System.out.printf("Results for %d threads:%n", threads);
            System.out.printf("Mean Time: %.2f ms, Standard Deviation: %.2f ms%n%n", mean, stdDev);
            results.put(threads, mean);
        }
        return results;
    }

    private static Map<Integer, Double> runExecutorService() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return runImplementationWithThreads(ExecutorParallelMatrixMultiplication.class);
    }

    private static Map<Integer, Double> runForkJoin() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return runImplementationWithThreads(ForkJoinMatrixMultiplication.class);
    }
}

