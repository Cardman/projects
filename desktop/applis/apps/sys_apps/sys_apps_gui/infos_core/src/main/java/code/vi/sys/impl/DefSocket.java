package code.vi.sys.impl;

import code.gui.initialize.AbstractBufferedReader;
import code.gui.initialize.AbstractSocket;

import java.io.*;
import java.net.Socket;

public class DefSocket implements AbstractSocket {
    private Socket socket;
    private boolean ko;

    public DefSocket() {
        ko = true;
    }
    public DefSocket(Socket _socket) {
        socket = _socket;
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
    public String println(String _string) {
        try {
            new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),StreamCoreUtil.utf()), true).println(_string);
            return _string+"\n";
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    public void close() {
        StreamCoreUtil.close(socket);
    }

    @Override
    public boolean isKo() {
        return ko;
    }
}
