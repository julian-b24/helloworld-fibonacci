//
// Copyright (c) ZeroC, Inc. All rights reserved.
//
//
// Ice version 3.7.8
//
// <auto-generated>
//
// Generated from file `HelloWorldCallback.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package Deploy;

public interface HelloWorldCallbackSender extends com.zeroc.Ice.Object
{
    String printFibonacci(String hostname, String input, com.zeroc.Ice.Current current);

    String registerClient(HelloWorldCallbackReceiverPrx proxy, String hostname, com.zeroc.Ice.Current current);

    String sendMessage(String hostname, String input, com.zeroc.Ice.Current current);

    void sendBroadcast(String hostname, String input, com.zeroc.Ice.Current current);

    String listClients(com.zeroc.Ice.Current current);

    /** @hidden */
    static final String[] _iceIds =
    {
        "::Deploy::HelloWorldCallbackSender",
        "::Ice::Object"
    };

    @Override
    default String[] ice_ids(com.zeroc.Ice.Current current)
    {
        return _iceIds;
    }

    @Override
    default String ice_id(com.zeroc.Ice.Current current)
    {
        return ice_staticId();
    }

    static String ice_staticId()
    {
        return "::Deploy::HelloWorldCallbackSender";
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_printFibonacci(HelloWorldCallbackSender obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        String iceP_hostname;
        String iceP_input;
        iceP_hostname = istr.readString();
        iceP_input = istr.readString();
        inS.endReadParams();
        String ret = obj.printFibonacci(iceP_hostname, iceP_input, current);
        com.zeroc.Ice.OutputStream ostr = inS.startWriteParams();
        ostr.writeString(ret);
        inS.endWriteParams(ostr);
        return inS.setResult(ostr);
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_registerClient(HelloWorldCallbackSender obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        HelloWorldCallbackReceiverPrx iceP_proxy;
        String iceP_hostname;
        iceP_proxy = HelloWorldCallbackReceiverPrx.uncheckedCast(istr.readProxy());
        iceP_hostname = istr.readString();
        inS.endReadParams();
        String ret = obj.registerClient(iceP_proxy, iceP_hostname, current);
        com.zeroc.Ice.OutputStream ostr = inS.startWriteParams();
        ostr.writeString(ret);
        inS.endWriteParams(ostr);
        return inS.setResult(ostr);
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_sendMessage(HelloWorldCallbackSender obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        String iceP_hostname;
        String iceP_input;
        iceP_hostname = istr.readString();
        iceP_input = istr.readString();
        inS.endReadParams();
        String ret = obj.sendMessage(iceP_hostname, iceP_input, current);
        com.zeroc.Ice.OutputStream ostr = inS.startWriteParams();
        ostr.writeString(ret);
        inS.endWriteParams(ostr);
        return inS.setResult(ostr);
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_sendBroadcast(HelloWorldCallbackSender obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        String iceP_hostname;
        String iceP_input;
        iceP_hostname = istr.readString();
        iceP_input = istr.readString();
        inS.endReadParams();
        obj.sendBroadcast(iceP_hostname, iceP_input, current);
        return inS.setResult(inS.writeEmptyParams());
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_listClients(HelloWorldCallbackSender obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        inS.readEmptyParams();
        String ret = obj.listClients(current);
        com.zeroc.Ice.OutputStream ostr = inS.startWriteParams();
        ostr.writeString(ret);
        inS.endWriteParams(ostr);
        return inS.setResult(ostr);
    }

    /** @hidden */
    final static String[] _iceOps =
    {
        "ice_id",
        "ice_ids",
        "ice_isA",
        "ice_ping",
        "listClients",
        "printFibonacci",
        "registerClient",
        "sendBroadcast",
        "sendMessage"
    };

    /** @hidden */
    @Override
    default java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceDispatch(com.zeroc.IceInternal.Incoming in, com.zeroc.Ice.Current current)
        throws com.zeroc.Ice.UserException
    {
        int pos = java.util.Arrays.binarySearch(_iceOps, current.operation);
        if(pos < 0)
        {
            throw new com.zeroc.Ice.OperationNotExistException(current.id, current.facet, current.operation);
        }

        switch(pos)
        {
            case 0:
            {
                return com.zeroc.Ice.Object._iceD_ice_id(this, in, current);
            }
            case 1:
            {
                return com.zeroc.Ice.Object._iceD_ice_ids(this, in, current);
            }
            case 2:
            {
                return com.zeroc.Ice.Object._iceD_ice_isA(this, in, current);
            }
            case 3:
            {
                return com.zeroc.Ice.Object._iceD_ice_ping(this, in, current);
            }
            case 4:
            {
                return _iceD_listClients(this, in, current);
            }
            case 5:
            {
                return _iceD_printFibonacci(this, in, current);
            }
            case 6:
            {
                return _iceD_registerClient(this, in, current);
            }
            case 7:
            {
                return _iceD_sendBroadcast(this, in, current);
            }
            case 8:
            {
                return _iceD_sendMessage(this, in, current);
            }
        }

        assert(false);
        throw new com.zeroc.Ice.OperationNotExistException(current.id, current.facet, current.operation);
    }
}
