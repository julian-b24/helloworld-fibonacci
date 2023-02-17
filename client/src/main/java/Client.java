import Deploy.FibonacciPrinterPrx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Client {

    private final static String EXIT_STRING = "exit";


    public static void main(String[] args) {
        java.util.List<String> extraArgs = new java.util.ArrayList<>();

        try (com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args, "config.client", extraArgs)) {
            //com.zeroc.Ice.ObjectPrx base = communicator.stringToProxy("SimplePrinter:default -p 10000");
            Deploy.FibonacciPrinterPrx twoway = Deploy.FibonacciPrinterPrx.checkedCast(
                    communicator.propertyToProxy("Printer.Proxy")).ice_twoway().ice_secure(false);
            //Demo.PrinterPrx printer = Demo.PrinterPrx.checkedCast(base);
            Deploy.FibonacciPrinterPrx printer = twoway.ice_oneway();

            if (printer == null) {
                throw new Error("Invalid proxy");
            }

            sendRequest(printer);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void sendRequest(FibonacciPrinterPrx printer) throws IOException {
        String hostname = getHostName();
        String input = "";
        do {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            input = reader.readLine();
            printer.printFibonacci(hostname, input);

        } while (!input.equals(EXIT_STRING));
    }

    private static String getHostName() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostName();
    }

}