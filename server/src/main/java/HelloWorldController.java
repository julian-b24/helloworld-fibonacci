import Deploy.HelloWorldCallbackReceiverPrx;
import com.zeroc.Ice.Current;
import service.RegisterService;
import service.impl.CommunicationServiceImpl;
import service.impl.FibonacciServiceImpl;
import service.CommunicationService;
import service.FibonacciService;
import service.impl.RegisterServiceImpl;

import java.util.ArrayList;

public class HelloWorldController implements Deploy.HelloWorldCallbackSender {

    private RegisterService registerService;
    private FibonacciService fibonacciService;
    private CommunicationService communicationService;

    public HelloWorldController(){
        registerService = new RegisterServiceImpl();
        fibonacciService = new FibonacciServiceImpl();
        communicationService = new CommunicationServiceImpl();
    }

    @Override
    public int printFibonacci(String hostname, String input, Current current) {
        showMessageCmd(hostname, input);
        int output = 0;
        if(validateInputIsNumber(input)){
            int inputNumber = Integer.parseInt(input);
            output = calculateFibonacci(inputNumber);
            showFibonacciSequence(hostname, inputNumber);
            return output;
        }else{
            //Change to string? Remeber slice2java, ice config file
            return 404;
        }
        
    }

    @Override
    public String registerClient(HelloWorldCallbackReceiverPrx proxy, String hostname, Current current) {
        return registerService.registerHost(hostname, proxy);
    }

    @Override
    public String communications(String hostname, String input, Current current) {
        showMessageCmd(hostname, input);

        switch(checkMessage(input)){
            case 1:
                registerService.listClients();
                break;
            case 2:
                //Message
                String[] parts = input.split(":");
                String recieverName = parts[0];
                String message = hostname+ ": "+parts[1];
                recieverName = recieverName.replaceFirst("^to ", "");
                registerService.sendMessage(recieverName, message);
                break;
            case 3:
                //Broadcast
                String[] bcparts = input.split("C");
                String bcmessage = hostname+ ": "+ bcparts[1];
                registerService.sendBroadcast(hostname, bcmessage);
                break;
        }

        return "";
        
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

    private int checkMessage(String input){
        if(input.equals("list clients")){
            return 1;
        }else if (input.startsWith("to")){
            return 2;
        }else if(input.startsWith("BC")){
            return 3;
        }
        return 0;
    }

}