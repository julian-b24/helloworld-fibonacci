package service.impl;

import service.FibonacciService;

public class FibonacciServiceImplRecursive implements FibonacciService {
    @Override
    public int calculateFibonacci(int number) {
        if (number <= 0) {
            return number;
        } else if (number == 1) {
            return 1;
        } else {
            return calculateFibonacci(number - 1) + calculateFibonacci(number - 2);
        }
    }
}
