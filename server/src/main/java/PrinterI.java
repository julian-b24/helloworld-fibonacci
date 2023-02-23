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
            output = calculateFibonacci(input);
        }
        return output;
    }

    private int calculateFibonacci(String input) {
        int inputNumber = new Integer(input);
        FibonacciService fibonacciService = new FibonacciImpl();
        int result = fibonacciService.calculateFibonacci(inputNumber);
        return result;
    }

    private void showMessageCmd(String hostname, String input){
        String message = hostname + ": " + input;
        System.out.println(message);
    }

    private boolean validateInputIsNumber(String input){
        return input.matches("\\d+");
    }
}