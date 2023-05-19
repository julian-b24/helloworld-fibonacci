import Deploy.HelloWorldCallbackReceiverPrx;
import Deploy.HelloWorldCallbackSenderPrx;
import com.zeroc.Ice.ObjectAdapter;

public class Client {

    public static void main(String[] args) {
        java.util.List<String> extraArgs = new java.util.ArrayList<>();

        try (com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args, "config.client", extraArgs)) {

            //Create of sender proxy
            HelloWorldCallbackSenderPrx twoway = HelloWorldCallbackSenderPrx.checkedCast(
                    communicator.propertyToProxy("Printer.Proxy")).ice_twoway().ice_secure(false);
            HelloWorldCallbackSenderPrx senderPrx = twoway.ice_twoway().ice_timeout(100000);

            if (senderPrx == null) {
                throw new Error("Invalid proxy");
            }

            ObjectAdapter adapter = communicator.createObjectAdapter("Callback.Client");
            com.zeroc.Ice.Object object = new HelloWorldClientController();
            adapter.add(object, com.zeroc.Ice.Util.stringToIdentity("HelloWorldCallbackReceiver"));
            adapter.activate();

            HelloWorldCallbackReceiverPrx receiver = HelloWorldCallbackReceiverPrx.uncheckedCast(
                    adapter.createProxy(com.zeroc.Ice.Util.stringToIdentity("HelloWorldCallbackReceiver"))
            );

            HelloWorldClientController controller = new HelloWorldClientController(senderPrx, receiver, communicator);
            //controller.executeClient();
            controller.executeFibonacciTimeout();

            communicator.waitForShutdown();

        }
    }

}