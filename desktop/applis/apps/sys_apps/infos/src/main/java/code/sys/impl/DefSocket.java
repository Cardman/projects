package code.sys.impl;

import code.gui.initialize.AbstractBufferedReader;
import code.gui.initialize.AbstractSocket;

import java.io.*;
import java.net.Socket;

public class DefSocket implements AbstractSocket {
    private Socket socket;

    public DefSocket() {
    }
    public DefSocket(Socket _socket) {
        socket = _socket;
    }


    public Socket getSocket() {
        return socket;
    }

    @Override
    public AbstractBufferedReader getInput() {
        try {
            return new DefBufferedReader(new BufferedReader(new InputStreamReader(socket.getInputStream(),StreamCoreUtil.utf())));
        } catch (Exception e) {
            return new DefBufferedReader();
        }
    }

    @Override
    public void println(String _string) {
        new PrintWriter(getOutputStream(), true).println(_string);
    }

    private OutputStream getOutputStream() {
        try {
            return socket.getOutputStream();
        } catch (Exception e) {
            return null;
        }
    }

    public Closeable getClos() {
        return getSocket();
    }
}
