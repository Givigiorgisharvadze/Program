import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String FILE_PATH = "Program.txt";

    private Main() {
        // private constructor to prevent instantiation
    }

    public static void main(String[] args) {
        try {
            List<Integer> numbers = readIntegersFromFile();
            printIntArray(numbers);
            int sum = sumOfIntegers(numbers);
            System.out.println("The sum of the numbers is: " + sum);
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format: " + e.getMessage());
        } catch (InvalidNumberException e) {
            System.err.println("Invalid number: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.err.println("Arithmetic error: " + e.getMessage());
        }
    }

    private static List<Integer> readIntegersFromFile() throws InvalidNumberException, NumberFormatException {
        List<Integer> numbers = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(FILE_PATH));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("/");
                if (tokens.length == 2) {
                    int numerator = Integer.parseInt(tokens[0]);
                    int denominator = Integer.parseInt(tokens[1]);
                    if (denominator == 0 && numerator != 0) {
                        throw new ArithmeticException("Divide by zero");
                    }
                    int result = (int) Math.ceil((double) numerator / denominator);
                    numbers.add(result);
                } else {
                    int number = Integer.parseInt(line);
                    if (number < 0) {
                        throw new InvalidNumberException("Negative");
                    }
                    numbers.add(number);
                }
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid number format: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.err.println("Error closing file: " + e.getMessage());
                }
            }
        }
        return numbers;
    }

    private static int sumOfIntegers(List<Integer> numbers) throws ArithmeticException {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }

    private static void printIntArray(List<Integer> list) {
        System.out.print("Array of integers: [");
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                System.out.print(", ");
            }
            System.out.print(list.get(i));
        }
        System.out.println("]");
    }
}

class InvalidNumberException extends Exception {
    public InvalidNumberException(String message) {
        super(message);
    }
}
