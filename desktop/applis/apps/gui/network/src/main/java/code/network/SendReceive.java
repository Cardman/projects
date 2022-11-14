package code.network;
import code.gui.initialize.AbstractSocket;

/**
Processus fils du programme qui gere la connection tcp/ip.
This class thread is independant from EDT,
Thread safe class*/
public abstract class SendReceive implements Runnable {

    /** Used socket for a server or a client */
    private AbstractSocket socket;

    private final NetGroupFrame net;

    protected SendReceive(AbstractSocket _socket, NetGroupFrame _net) {
        setSocket(_socket);
        net = _net;

    }

    public NetGroupFrame getNet() {
        return net;
    }

    public AbstractSocket getSocket() {
        return socket;
    }


    private void setSocket(AbstractSocket _socket) {
        socket = _socket;
    }
}
