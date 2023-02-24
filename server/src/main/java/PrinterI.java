import com.zeroc.Ice.Current;
import utils.FibonacciImpl;
import utils.FibonacciService;

public class PrinterI implements Deploy.FibonacciPrinter
{
    @Override
    public int printFibonacci(String hostname, String input, Current current) {
        showMessageCmd(hostname, input);
        int output = 0;
        if(validateInputIsNumber(input)){
            int inputNumber = new Integer(input);
            output = calculateFibonacci(inputNumber);
            showFibonacciSequence(inputNumber);
        }
        return output;
    }

    private void showFibonacciSequence(int input) {
        while (input > 0){
            System.out.println(calculateFibonacci(input));
            input--;
        }
    }

    private int calculateFibonacci(int input) {
        FibonacciService fibonacciService = new FibonacciImpl();
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