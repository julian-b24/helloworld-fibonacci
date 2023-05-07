import Deploy.HelloWorldCallbackReceiver;
import Deploy.HelloWorldCallbackReceiverPrx;
import Deploy.HelloWorldCallbackSenderPrx;
import com.zeroc.Ice.Current;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;

public class HelloWorldClientController implements HelloWorldCallbackReceiver {

    private final static BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    private final static String EXIT_STRING = "exit";

    private HelloWorldCallbackSenderPrx senderPrx;
    private HelloWorldCallbackReceiverPrx receiverPrx;

    public HelloWorldClientController(){}

    public HelloWorldClientController(HelloWorldCallbackSenderPrx senderPrx, HelloWorldCallbackReceiverPrx receiverPrx){
        this.senderPrx = senderPrx;
        this.receiverPrx = receiverPrx;
    }

    @Override
    public void receiveMessage(Current current) {
        System.out.println("msg");
    }

    public void executeClient(){
        try {
            sendRequest(senderPrx, receiverPrx);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendRequest(HelloWorldCallbackSenderPrx senderPrx) throws IOException {
        String hostname = getHostName();
        String input = "";
        do {
            System.out.println("Input:");
            input = READER.readLine();
            if(!input.equals(EXIT_STRING)){
                System.out.println(senderPrx.printFibonacci(hostname, input));
            }
        } while (!input.equals(EXIT_STRING));
    }

    private void sendRequest(HelloWorldCallbackSenderPrx senderPrx, HelloWorldCallbackReceiverPrx receiverPrx) throws IOException {
        String hostname = getHostName();
        System.out.println(senderPrx.registerClient(receiverPrx, hostname));
    }


    private static String getHostName() throws IOException {
        System.out.println("Who are you? :");
        String whoami = READER.readLine();
        String hostname = InetAddress.getLocalHost().getHostName();
        return whoami + "@" + hostname;
    }

}
