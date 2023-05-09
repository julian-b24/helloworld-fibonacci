module Deploy
{
    interface HelloWorldCallbackReceiver
    {
        void receiveMessage(string msg);
    }

    interface HelloWorldCallbackSender
    {
        int printFibonacci(string hostname, string input);
        string registerClient(HelloWorldCallbackReceiver* proxy, string hostname);
        string communications(string hostname, string input);
    }
}