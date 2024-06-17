package code.network;
import code.gui.initialize.AbstractSocket;

/**
Processus fils du programme qui gere la connection tcp/ip.
This class thread is independant from EDT,
Thread safe class*/
public abstract class SendReceive implements Runnable {

    /** Used socket for a server or a client */
    private AbstractSocket socket;

    protected SendReceive(AbstractSocket _socket) {
        setSocket(_socket);

    }

    public AbstractSocket getSocket() {
        return socket;
    }


    private void setSocket(AbstractSocket _socket) {
        socket = _socket;
    }
}
