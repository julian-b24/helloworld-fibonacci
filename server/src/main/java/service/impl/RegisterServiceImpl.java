package service.impl;

import Deploy.HelloWorldCallbackReceiverPrx;
import constants.RegisterResponseMessage;
import service.RegisterService;

import java.util.HashMap;
import java.util.Map;

public class RegisterServiceImpl implements RegisterService {

    private Map<String, HelloWorldCallbackReceiverPrx> registerMap;

    public RegisterServiceImpl(){
        registerMap = new HashMap<>();
    }

    @Override
    public String registerHost(String hostname, HelloWorldCallbackReceiverPrx proxy) {
        validateHostIsNotInMap(hostname);
        registerMap.put(hostname, proxy);
        return RegisterResponseMessage.SUCCESSFUL.getMessage() + hostname;
    }

    @Override
    public HelloWorldCallbackReceiverPrx getProxy(String hostname) {
        return registerMap.get(hostname);
    }


    @Override
    public String listClients() {
        String clients="";
        for (String name : registerMap.keySet()) 
            clients+="Hostname: " + name + "\n";
        return clients;
    }

    @Override
    public void sendMessage(String destinyHostname, String message) {
        HelloWorldCallbackReceiverPrx proxy = registerMap.get(destinyHostname);
        proxy.receiveMessage(message);
    }

    @Override
    public void sendBroadcast(String srcHostname, String message) {
        for (String name : registerMap.keySet()){
            if(!name.equals(srcHostname)){
                HelloWorldCallbackReceiverPrx proxy = registerMap.get(name);
                proxy.receiveMessage(message);
            }
        }
    }

    private void validateHostIsNotInMap(String hostname) {
        if(registerMap.containsKey(hostname)){
            System.out.println("Host is already registered");
        }
    }
}
