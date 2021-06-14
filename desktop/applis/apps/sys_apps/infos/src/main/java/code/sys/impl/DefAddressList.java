package code.sys.impl;

import code.gui.initialize.AbstractAddressList;

import java.net.InetAddress;
import java.net.UnknownHostException;

public final class DefAddressList implements AbstractAddressList {
    private InetAddress[] addr;
    public DefAddressList(String _host) {
        try {
            //InetAddress.getAllByName throws UnknownHostException
            //if no IP address for the host could be found,
            //or if a scope_id was specified for a global IPv6 address.
            addr = InetAddress.getAllByName(_host);
        } catch (UnknownHostException e) {
            addr = new InetAddress[0];
        }
    }
    @Override
    public int size() {
        return addr.length;
    }

    @Override
    public String getHost(int _index) {
        return addr[_index].getHostAddress();
    }
}
