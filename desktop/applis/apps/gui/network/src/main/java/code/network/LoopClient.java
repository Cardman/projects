package code.network;
import code.gui.initialize.AbstractSocket;
import code.sml.Document;

/**Thread safe class*/
public final class LoopClient implements Runnable {

    private NetGroupFrame window;

    private Document doc;

    /** Used socket for a client */
    private AbstractSocket socket;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public LoopClient(NetGroupFrame _window, Document _doc, AbstractSocket _socket) {
        window = _window;
        doc = _doc;
        socket = _socket;
    }

    @Override
    public void run() {
        window.loop(doc, socket);
        window.pack();
    }
}
