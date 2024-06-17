package aiki.network;

import code.gui.initialize.AbstractSocket;
import code.network.WindowNetWork;
import code.util.CustList;

public interface IntClientActLoopAiki {
    void loop(WindowNetWork _window, CustList<String> _parts, AbstractSocket _socket);
}
