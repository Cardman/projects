package code.network;
import java.net.Socket;

/**
Processus fils du programme qui gere la connection tcp/ip.
This class thread is independant from EDT,
Thread safe class*/
public abstract class SendReceive extends Thread {

    /** Used socket for a server or a client */
    private Socket socket;

    private NetGroupFrame net;

    public SendReceive(Socket _socket) {
        setSocket(_socket);

    }

    public NetGroupFrame getNet() {
        return net;
    }

    protected Socket getSocket() {
        return socket;
    }


    private void setSocket(Socket _socket) {
        socket = _socket;
    }
}
