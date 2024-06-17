package cards.network.threads;

import code.gui.initialize.AbstractSocket;
import code.network.WindowNetWork;
import code.util.CustList;

public interface IntClientActLoopCards {
    void loop(WindowNetWork _window, CustList<String> _parts, AbstractSocket _socket);
}
