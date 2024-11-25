package utils;

public class MathUtils {

    public static double standardDeviation(double[] numbers) {
        double sumOfSquares = 0.0;
        double mean = mean(numbers);

        for (double number : numbers) {
            sumOfSquares += Math.pow(number - mean, 2);
        }
        return Math.sqrt(sumOfSquares / numbers.length);
    }

    public static double mean(double[] numbers) {
        double sum = 0.0;
        for (double number: numbers) {
            sum += number;
        }
        return sum / numbers.length;
    }
}
