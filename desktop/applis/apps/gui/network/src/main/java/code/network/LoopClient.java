package code.network;
import aiki.network.NetAiki;
import aiki.network.NetAikiRetrievedInfos;
import cards.network.threads.Net;
import cards.network.threads.NetRetrievedInfos;
import code.gui.initialize.AbstractSocket;
import code.sml.Document;

/**Thread safe class*/
public final class LoopClient implements Runnable {

    private final WindowNetWork window;

    private final Document doc;

    /** Used socket for a client */
    private final AbstractSocket socket;
    private final NetRetrievedInfos netRetrievedInfos;
    private final NetAikiRetrievedInfos netAikiRetrievedInfos;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public LoopClient(WindowNetWork _window, Document _doc, AbstractSocket _socket, NetRetrievedInfos _netInfos, NetAikiRetrievedInfos _netInfosAikie) {
        window = _window;
        netRetrievedInfos = _netInfos;
        netAikiRetrievedInfos = _netInfosAikie;
        doc = _doc;
        socket = _socket;
    }

    @Override
    public void run() {
        if (Net.QUICK) {
            if (window.isCards()) {
                Net.loopClient(window, netRetrievedInfos, socket);
            } else {
                NetAiki.loopClient(window, netAikiRetrievedInfos, socket);
            }
        } else {
            window.loop(doc, socket);
        }
        window.pack();
    }
}
