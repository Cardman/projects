package code.vi.sys.impl;

import code.gui.initialize.AbstractAddressList;
import code.gui.initialize.AbstractNetworkInterfaceList;

import java.net.*;
import java.util.Collections;
import java.util.List;

public class DefNetworkInterfaceList implements AbstractNetworkInterfaceList {
    private List<NetworkInterface> list;
    public DefNetworkInterfaceList() {
        try {
            list = Collections.list(NetworkInterface.getNetworkInterfaces());
        } catch (Exception e) {
            list = Collections.emptyList();
        }
    }

    @Override
    public AbstractAddressList list(int _index) {
        return new DefAddressList(Collections.list(list.get(_index).getInetAddresses()));
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
    public String getName(int _index) {
        return list.get(_index).getName();
    }

    @Override
    public boolean isVirtual(int _index) {
        return list.get(_index).isVirtual();
    }
}
