package code.mock;

import code.gui.initialize.*;

public final class MockSocketFactory implements AbstractSocketFactory {

    private boolean okServer;
    private boolean koSocket;

    @Override
    public AbstractSocket newSocket(int _port, String _address) {
        return newSocket(_port);
    }

    @Override
    public AbstractSocket newSocket(int _port) {
        return new MockSocket(koSocket);
    }

    @Override
    public AbstractServerSocket newServerSocket(String _ip, int _port) {
        return newServerSocket(_port);
    }

    @Override
    public AbstractServerSocket newServerSocket(int _port) {
        return new MockServerSocket(okServer);
    }

    @Override
    public AbstractNetworkInterfaceList newList() {
        return new MockNetworkInterfaceList();
    }

    @Override
    public AbstractAddressList newAddr(String _host) {
        return new MockAddressList();
    }

    @Override
    public boolean setOkServer(boolean _ok) {
        this.okServer = _ok;
        return _ok;
    }

    @Override
    public boolean setKoSocket(boolean _ko) {
        this.koSocket = _ko;
        return _ko;
    }
}
