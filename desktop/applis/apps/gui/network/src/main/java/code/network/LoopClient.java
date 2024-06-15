package code.network;
import cards.network.threads.Net;
import code.gui.initialize.AbstractSocket;
import code.sml.Document;

/**Thread safe class*/
public final class LoopClient implements Runnable {

    private final WindowNetWork window;

    private final String in;
    private final Document doc;

    /** Used socket for a client */
    private final AbstractSocket socket;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public LoopClient(WindowNetWork _window, String _input, Document _doc, AbstractSocket _socket) {
        window = _window;
        in = _input;
        doc = _doc;
        socket = _socket;
    }

    @Override
    public void run() {
        if (Net.QUICK) {
            Net.loopClient(window, in, socket);
        } else {
            window.loop(doc, socket);
        }
        window.pack();
    }
}
