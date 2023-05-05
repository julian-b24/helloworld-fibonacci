package service;

public interface CommunicationService {

    String listClients();
    void sendMessage(String destinyHostname, String message);
    void sendBroadcast(String srcHostname, String message);

}
