package code.network;
import java.net.Socket;

/**
Processus fils du programme qui gere la connection tcp/ip.
This class thread is independant from EDT,
Thread safe class*/
public abstract class SendReceive extends Thread {
    /** useful static field for debugging*/
    private static int _nbThread_;

    /** Used socket for a server or a client */
    private Socket socket;

    /** useful field for debugging by numbering threads*/
    private int noThread;


    private NetGroupFrame net;

    public SendReceive(Socket _socket) {
        setSocket(_socket);
        noThread = _nbThread_;
        _nbThread_++;

    }

    public NetGroupFrame getNet() {
        return net;
    }

    protected int getNoThread() {
        return noThread;
    }

    protected Socket getSocket() {
        return socket;
    }


    private void setSocket(Socket _socket) {
        socket = _socket;
    }
}
