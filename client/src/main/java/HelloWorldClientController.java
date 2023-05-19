import Deploy.HelloWorldCallbackReceiver;
import Deploy.HelloWorldCallbackReceiverPrx;
import Deploy.HelloWorldCallbackSenderPrx;
import com.zeroc.Ice.Current;
import com.zeroc.Ice.Communicator;

import constants.MenuOption;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;

public class HelloWorldClientController implements HelloWorldCallbackReceiver {

    private final static BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    private final static String EXIT_STRING = "exit";

    private final static int[] FIBONACCI_TIMEOUT_ARRAY = {500, 400, 300, 200, 100, 95, 90};

    private HelloWorldCallbackSenderPrx senderPrx;
    private HelloWorldCallbackReceiverPrx receiverPrx;

    private Communicator communicator;

    public HelloWorldClientController(){}

    public HelloWorldClientController(HelloWorldCallbackSenderPrx senderPrx, HelloWorldCallbackReceiverPrx receiverPrx, Communicator communicator){
        this.senderPrx = senderPrx;
        this.receiverPrx = receiverPrx;
        this.communicator = communicator;
    }

    @Override
    public void receiveMessage(String msg, Current current) {
        System.out.println(msg);
    }

    public void executeClient(){
        try {
            String menu = getMenu();
            choseMenuOption(menu);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void executeFibonacciTimeout() {
        int index = 0;
        String hostname = null;
        try {
            hostname = getHostName();
            while(index < FIBONACCI_TIMEOUT_ARRAY.length){
                int number = FIBONACCI_TIMEOUT_ARRAY[index];
                System.out.println(senderPrx.printFibonacci(hostname, String.valueOf(number)));
                index++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getMenu(){
        String menu = "";
        menu += MenuOption.REGISTER.getOption() + "\n" +
                MenuOption.WHO_AM_I.getOption() + "\n" +
                MenuOption.FIBONACCI.getOption() + "\n" +
                MenuOption.COMMUNICATIONS.getOption() + "\n" +
                MenuOption.EXIT.getOption() + "\n";
        return menu;
    }

    private void choseMenuOption(String menu) throws IOException {
        String option;
        do {
            System.out.println(menu);
            System.out.println("Option:");
            option = READER.readLine();

            if(!option.equals(MenuOption.EXIT.getOption())){
                executeOption(Integer.parseInt(option));
            }

        } while (!option.equals(MenuOption.EXIT.getOption()));

        closeServerConnection();
    }

    private void executeOption(int option) throws IOException {
        switch (option){
            case 1:
                sendRegisterRequest(senderPrx, receiverPrx);
                break;
            case 2:
                System.out.println(getHostName());;
                break;
            case 3:
                sendFibonacciRequest(senderPrx);
                break;
            case 4:
                sendCommunicationsRequest(senderPrx);
                break;
            default:
                System.out.println("Unexpected value, choose again.");;
        }
    }

    private void closeServerConnection() {
        System.out.println("See you the next time!");
        communicator.close();
    }

    private void sendFibonacciRequest(HelloWorldCallbackSenderPrx senderPrx) throws IOException {
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

    private void sendRegisterRequest(HelloWorldCallbackSenderPrx senderPrx, HelloWorldCallbackReceiverPrx receiverPrx) throws IOException {
        String hostname = getHostName();
        System.out.println(senderPrx.registerClient(receiverPrx, hostname));
    }


    private static String getHostName() throws IOException {
        System.out.println("Who are you? :");
        String whoami = READER.readLine();
        String hostname = InetAddress.getLocalHost().getHostName();
        return whoami + "@" + hostname;
    }

    private void sendCommunicationsRequest(HelloWorldCallbackSenderPrx senderPrx) throws IOException {
        String hostname = getHostName();
        String input = "";
        do {
            System.out.println("Input:");
            input = READER.readLine();
            if(!input.equals(EXIT_STRING)){
                System.out.println(senderPrx.communications(hostname, input));
            }
        } while (!input.equals(EXIT_STRING));
    }

}
