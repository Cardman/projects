package code.sys.impl;

import code.gui.initialize.AbstractServerSocket;
import code.gui.initialize.AbstractSocket;

import java.io.Closeable;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

public class DefServerSocket implements AbstractServerSocket {
    private ServerSocket serverSocket;

    public DefServerSocket() {
        try {
            serverSocket = new ServerSocket();
        } catch (Exception e) {
            serverSocket = null;
        }
    }

    @Override
    public AbstractServerSocket bind(String _ip, int _port) {
        try {
            serverSocket.bind(new InetSocketAddress(_ip, _port));
            serverSocket.close();
            return this;
        } catch (Exception e) {
            return this;
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
    public Closeable getClos() {
        return serverSocket;
    }

    @Override
    public boolean isClosed() {
        return serverSocket.isClosed();
    }
}
