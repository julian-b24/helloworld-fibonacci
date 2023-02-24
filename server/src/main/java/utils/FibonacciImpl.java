package utils;

import java.util.HashMap;

public class FibonacciImpl implements FibonacciService{

    private static HashMap<Integer, Integer> memory = new HashMap<>();

    @Override
    public int calculateFibonacci(int number) {
        if (number <= 1) {
            return 0;
        } else if (memory.containsKey(number)) {
            return memory.get(number);
        } else {
            int result = calculateFibonacci(number - 1) + calculateFibonacci(number - 2);
            memory.put(number, result);
            return result;
        }
    }
}
