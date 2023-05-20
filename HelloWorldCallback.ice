module Deploy
{
    interface HelloWorldCallbackReceiver
    {
        void receiveMessage(string msg);
    }

    interface HelloWorldCallbackSender
    {
        string printFibonacci(string hostname, string input);
        string registerClient(HelloWorldCallbackReceiver* proxy, string hostname);
        string sendMessage(string hostname, string input);
        void sendBroadcast(string hostname, string input);
        string listClients();
    }
}