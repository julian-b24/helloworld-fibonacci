import com.zeroc.Ice.Current;

public class PrinterI implements Deploy.FibonacciPrinter
{
    @Override
    public int printFibonacci(String hostname, String input, Current current) {
        return 0;
    }
}