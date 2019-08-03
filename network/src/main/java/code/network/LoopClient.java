package code.network;
import java.net.Socket;

/**Thread safe class*/
public final class LoopClient implements Runnable {

    private NetGroupFrame window;

    private Object readObject;

    /** Used socket for a client */
    private Socket socket;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public LoopClient(NetGroupFrame _window, Object _readObject, Socket _socket) {
        window = _window;
        readObject = _readObject;
        socket = _socket;
    }

    @Override
    public void run() {
        window.loop(readObject, socket);
        window.pack();
    }
}
