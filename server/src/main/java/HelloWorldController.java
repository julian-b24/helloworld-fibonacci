import com.zeroc.Ice.Current;
import service.impl.FibonacciServiceImpl;
import service.FibonacciService;

public class HelloWorldController implements Deploy.HelloWorldCallback
{
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

    private void showFibonacciSequence(String hostname, int input) {
        while (input > 0){
            int number = calculateFibonacci(input);
            showMessageCmd(hostname, number+"");
            input--;
        }
    }

    private int calculateFibonacci(int input) {
        FibonacciService fibonacciService = new FibonacciServiceImpl();
        int result = fibonacciService.calculateFibonacci(input);
        return result;
    }

    private void showMessageCmd(String hostname, String input){
        String message = hostname + ":" + input;
        System.out.println(message);
    }

    private boolean validateInputIsNumber(String input){
        return input.matches("\\d+");
    }
}