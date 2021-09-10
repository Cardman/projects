package code.vi.sys.impl;

import code.gui.initialize.*;

import java.io.IOException;
import java.net.Socket;

public final class DefSocketFactory implements AbstractSocketFactory {

    @Override
    public AbstractSocket newSocket(int _port, String _address) {
        try {
            return new DefSocket(new Socket(_address, _port));
        } catch (IOException e) {
            return new DefSocket();
        }
    }

    @Override
    public AbstractServerSocket newServerSocket(String _ip, int _port) {
        return new DefServerSocket(_ip, _port);
    }

    @Override
    public AbstractNetworkInterfaceList newList() {
        return new DefNetworkInterfaceList();
    }

    @Override
    public AbstractAddressList newAddr(String _host) {
        return new DefAddressList(_host);
    }
}
