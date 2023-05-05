package service.impl;

import constants.RegisterResponseMessage;
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
        return RegisterResponseMessage.SUCCESSFUL.getMessage() + hostname;
    }

    private void validateHostIsNotInList(String hostname) {
        if(registerList.contains(hostname)){
            throw new RuntimeException(RegisterResponseMessage.FAIL_HOST_EXISTS.getMessage());
        }
    }
}
