module Deploy
{
    interface HelloWorldCallbackReceiver
    {
        void receiveMessage();
    }

    interface HelloWorldCallbackSender
    {
        int printFibonacci(string hostname, string input);
        string registerClient(HelloWorldCallbackReceiver* proxy, string hostname);
    }
}