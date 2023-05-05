package service.impl;

import service.RegisterService;

import java.util.ArrayList;

public class RegisterServiceImpl implements RegisterService {

    private ArrayList<String> registerList;

    public RegisterServiceImpl(){
        registerList = new ArrayList<>();
    }

    @Override
    public String registerHost(String hostname) {
        validateHostIsNotInList(hostname);
        registerList.add(hostname);
        return "Successful register of " + hostname;
    }

    private void validateHostIsNotInList(String hostname) {
        if(registerList.contains(hostname)){
            throw new RuntimeException("Host is already registered");
        }
    }
}
