module Deploy
{
    interface HelloWorldCallback
    {
        int printFibonacci(string hostname, string input);
        string registerClient(string hostname);
    }
}