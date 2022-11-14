package code.vi.sys.impl;

import code.gui.initialize.AbstractServerSocket;
import code.gui.initialize.AbstractSocket;
import code.vi.prot.impl.StreamCoreUtil;

import java.net.InetSocketAddress;
import java.net.ServerSocket;

public class DefServerSocket implements AbstractServerSocket {
    private ServerSocket serverSocket;
    private boolean ok;

    public DefServerSocket(String _ip, int _port) {
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(_ip, _port));
            setOk(true);
        } catch (Exception e) {
            StreamCoreUtil.close(serverSocket);
            serverSocket = null;
        }
    }

    @Override
    public AbstractSocket accept() {
        try {
            return new DefSocket(serverSocket.accept());
        } catch (Exception e) {
            return new DefSocket();
        }
    }

    @Override
    public boolean isOk() {
        return ok;
    }

    @Override
    public void setOk(boolean _o) {
        this.ok = _o;
    }

    @Override
    public boolean close() {
        return StreamCoreUtil.close(serverSocket);
    }

    @Override
    public boolean isClosed() {
        return serverSocket.isClosed();
    }
}
