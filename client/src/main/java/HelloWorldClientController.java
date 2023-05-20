import Deploy.HelloWorldCallbackReceiver;
import Deploy.HelloWorldCallbackReceiverPrx;
import Deploy.HelloWorldCallbackSenderPrx;
import com.zeroc.Ice.Current;
import com.zeroc.Ice.Communicator;

import com.zeroc.Ice.TimeoutException;
import constants.MenuOption;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class HelloWorldClientController implements HelloWorldCallbackReceiver {

    private final static BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    private final static String EXIT_STRING = "exit";

    private int FIBONACCI_TIMEOUT_ARRAY_SIZE = 100000;
    private int FIBONACCI_TIMEOUT_MAX_VALUE = 100;


    private HelloWorldCallbackSenderPrx senderPrx;
    private HelloWorldCallbackReceiverPrx receiverPrx;
    private Communicator communicator;
    private String hostname;

    public HelloWorldClientController(){}

    public HelloWorldClientController(HelloWorldCallbackSenderPrx senderPrx, HelloWorldCallbackReceiverPrx receiverPrx, Communicator communicator){
        this.senderPrx = senderPrx;
        this.receiverPrx = receiverPrx;
        this.communicator = communicator;
        this.hostname = defineHostname();
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
        String hostname = "";
        ArrayList<CompletableFuture<Integer>> calculatedNumbers = new ArrayList<>();

        try {
            int[] array = createFibonacciTimeOutArray();
            while(index < array.length - 1){
                int number = array[index];
                CompletableFuture<Integer> completableFuture = senderPrx.printFibonacciAsync(hostname, String.valueOf(number));
                calculatedNumbers.add(completableFuture);
                index++;
            }

            boolean pendingCalculations = true;
            while (pendingCalculations){
                pendingCalculations = getCompletedCalculations(calculatedNumbers) != calculatedNumbers.size();
            }

        } catch (TimeoutException timeoutException) {
            getCompletedCalculations(calculatedNumbers);
            System.out.println("TIMEOUT");
        }
    }

    private int getCompletedCalculations(ArrayList<CompletableFuture<Integer>> calculations){
        int completed = (int) calculations.stream().filter(CompletableFuture::isDone).count();
        System.out.print("Completed calculations: " + completed + "\r");
        return completed;
    }

    private String getMenu(){
        String menu = "";
        menu += MenuOption.REGISTER.getOption() + "\n" +
                MenuOption.WHO_AM_I.getOption() + "\n" +
                MenuOption.FIBONACCI.getOption() + "\n" +
                MenuOption.LIST_CLIENTS.getOption() + "\n" +
                MenuOption.SEND_MESSAGE.getOption() + "\n" +
                MenuOption.BROADCAST.getOption() + "\n" +
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
                System.out.println(hostname);;
                break;
            case 3:
                sendFibonacciRequest(senderPrx);
                break;
            case 4:
                sendListRequest(senderPrx);
                break;
            case 5:
                sendMessageRequest(senderPrx);
                break;
            case 6:
                sendBroadcastRequest(senderPrx);
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
        System.out.println(senderPrx.registerClient(receiverPrx, hostname));
    }


    private String defineHostname() {
        System.out.println("Who are you? Enter your current user:");
        String whoami = "";
        try {
            whoami = READER.readLine();
            String hostname = InetAddress.getLocalHost().getHostName();
            return whoami + "@" + hostname;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendListRequest(HelloWorldCallbackSenderPrx senderPrx) throws IOException {
        System.out.println(senderPrx.listClients());
    }

    private void sendMessageRequest(HelloWorldCallbackSenderPrx senderPrx) throws IOException {
        String input = "";
        do {
            System.out.println("Enter your message in the following format --> destinyHost:message");
            input = READER.readLine();
            if(!input.equals(EXIT_STRING)){
                senderPrx.sendMessage(hostname, input);
            }
        } while (!input.equals(EXIT_STRING));
    }

    private void sendBroadcastRequest(HelloWorldCallbackSenderPrx senderPrx) throws IOException {
        String input = "";
        do {
            System.out.println("Enter your broadcast message in the following format --> BC:message");
            input = READER.readLine();
            if(!input.equals(EXIT_STRING)){
                senderPrx.sendBroadcast(hostname, input);
            }
        } while (!input.equals(EXIT_STRING));
    }

    private int[] createFibonacciTimeOutArray(){
        return new Random().ints(FIBONACCI_TIMEOUT_ARRAY_SIZE, 50, FIBONACCI_TIMEOUT_MAX_VALUE + 1).toArray();
    }

}
