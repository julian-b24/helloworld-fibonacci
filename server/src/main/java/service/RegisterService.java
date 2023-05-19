package service;


import Deploy.HelloWorldCallbackReceiverPrx;

public interface RegisterService {

    String registerHost(String hostname, HelloWorldCallbackReceiverPrx proxy);
    HelloWorldCallbackReceiverPrx getProxy(String hostname);
    String listClients();
    void sendMessage(String destinyHostname, String message);
    void sendBroadcast(String srcHostname, String message);
}
