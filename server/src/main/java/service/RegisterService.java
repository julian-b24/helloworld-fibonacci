package service;


import Deploy.HelloWorldCallbackReceiverPrx;

public interface RegisterService {

    String registerHost(String hostname, HelloWorldCallbackReceiverPrx proxy);

}
