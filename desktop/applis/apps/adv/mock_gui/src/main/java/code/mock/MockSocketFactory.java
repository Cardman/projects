package code.mock;

import code.gui.initialize.*;

public final class MockSocketFactory implements AbstractSocketFactory {
    @Override
    public AbstractSocket newSocket(int _port, String _address) {
        return new MockSocket(false);
    }

    @Override
    public AbstractServerSocket newServerSocket(String _ip, int _port) {
        return new MockServerSocket();
    }

    @Override
    public AbstractNetworkInterfaceList newList() {
        return new MockNetworkInterfaceList();
    }

    @Override
    public AbstractAddressList newAddr(String _host) {
        return new MockAddressList();
    }
}
