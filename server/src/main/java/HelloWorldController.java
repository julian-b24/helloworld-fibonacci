import com.zeroc.Ice.Current;
import service.RegisterService;
import service.impl.FibonacciServiceImpl;
import service.FibonacciService;
import service.impl.RegisterServiceImpl;

import java.util.ArrayList;

public class HelloWorldController implements Deploy.HelloWorldCallback {

    private RegisterService registerService;
    private FibonacciService fibonacciService;

    public HelloWorldController(){
        registerService = new RegisterServiceImpl();
        fibonacciService = new FibonacciServiceImpl();
    }

    @Override
    public int printFibonacci(String hostname, String input, Current current) {
        showMessageCmd(hostname, input);
        int output = 0;
        if(validateInputIsNumber(input)){
            int inputNumber = new Integer(input);
            output = calculateFibonacci(inputNumber);
            showFibonacciSequence(hostname, inputNumber);
        }
        return output;
    }

    @Override
    public String registerClient(String hostname, Current current) {
        return registerService.registerHost(hostname);
    }

    private void showFibonacciSequence(String hostname, int input) {
        while (input > 0){
            int number = calculateFibonacci(input);
            showMessageCmd(hostname, number+"");
            input--;
        }
    }

    private int calculateFibonacci(int input) {
        return fibonacciService.calculateFibonacci(input);
    }

    private void showMessageCmd(String hostname, String input){
        String message = hostname + ":" + input;
        System.out.println(message);
    }

    private boolean validateInputIsNumber(String input){
        return input.matches("\\d+");
    }
}