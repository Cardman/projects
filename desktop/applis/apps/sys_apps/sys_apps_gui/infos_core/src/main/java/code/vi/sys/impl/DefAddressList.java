package code.vi.sys.impl;

import code.gui.initialize.AbstractAddressList;
import code.network.NetGroupFrame;

import java.net.InetAddress;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class DefAddressList implements AbstractAddressList {
    private List<InetAddress> addr;
    public DefAddressList(String _host) {
        try {
            //InetAddress.getAllByName throws UnknownHostException
            //if no IP address for the host could be found,
            //or if a scope_id was specified for a global IPv6 address.
            addr = Arrays.asList(InetAddress.getAllByName(_host));
        } catch (Exception e) {
            addr = Collections.emptyList();
        }
    }
    public DefAddressList(List<InetAddress> _add) {
        addr = _add;
    }
    @Override
    public int size() {
        return addr.size();
    }

    @Override
    public boolean isIpFour(int _index) {
        return NetGroupFrame.match(addr.get(_index).getAddress(),4);
    }

    @Override
    public boolean isIpSix(int _index) {
        return NetGroupFrame.match(addr.get(_index).getAddress(),16);
    }

    @Override
    public boolean isLoopbackAddress(int _index) {
        return addr.get(_index).isLoopbackAddress();
    }

    @Override
    public String getHost(int _index) {
        return addr.get(_index).getHostAddress();
    }
}
