import com.zeroc.Ice.Current;

public class PrinterI implements Deploy.FibonacciPrinter
{
    @Override
    public void printFibonacci(String s, Current current) {
        System.out.println(s);
    }
}