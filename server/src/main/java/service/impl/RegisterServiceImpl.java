package service.impl;

import Deploy.HelloWorldCallbackReceiverPrx;
import constants.RegisterResponseMessage;
import service.RegisterService;

import java.util.HashMap;
import java.util.Map;

public class RegisterServiceImpl implements RegisterService {

    private Map<String, Object> registerMap;

    public RegisterServiceImpl(){
        registerMap = new HashMap<>();
    }

    @Override
    public String registerHost(String hostname, HelloWorldCallbackReceiverPrx proxy) {
        validateHostIsNotInMap(hostname);
        registerMap.put(hostname, proxy);
        System.out.println(registerMap);
        proxy.receiveMessage();
        return RegisterResponseMessage.SUCCESSFUL.getMessage() + hostname;
    }

    private void validateHostIsNotInMap(String hostname) {
        if(registerMap.containsKey(hostname)){
            System.out.println("Host is already registered");
        }
    }
}
