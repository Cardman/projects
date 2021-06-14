package code.sys.impl;

import code.gui.initialize.AbstractNetworkInterfaceList;

import java.net.*;
import java.util.Collections;
import java.util.List;

public class DefNetworkInterfaceList implements AbstractNetworkInterfaceList {
    private List<NetworkInterface> list;
    public DefNetworkInterfaceList() {
        try {
            list = Collections.list(NetworkInterface.getNetworkInterfaces());
        } catch (SocketException e) {
            list = Collections.emptyList();
        }
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public int size(int _index) {
        return Collections.list(list.get(_index).getInetAddresses()).size();
    }

    @Override
    public boolean isIpFour(int _first, int _index) {
        return Collections.list(list.get(_index).getInetAddresses()).get(_index) instanceof Inet4Address;
    }

    @Override
    public boolean isIpSix(int _first, int _index) {
        return Collections.list(list.get(_index).getInetAddresses()).get(_index) instanceof Inet6Address;
    }

    @Override
    public boolean isLoopbackAddress(int _first, int _index) {
        return Collections.list(list.get(_index).getInetAddresses()).get(_index).isLoopbackAddress();
    }

    @Override
    public String getHost(int _first, int _index) {
        return Collections.list(list.get(_index).getInetAddresses()).get(_index).getHostAddress();
    }

    @Override
    public String getName(int _index) {
        return list.get(_index).getName();
    }

    @Override
    public boolean isVirtual(int _index) {
        return list.get(_index).isVirtual();
    }
}
