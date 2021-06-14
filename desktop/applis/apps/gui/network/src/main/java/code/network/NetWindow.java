package code.network;
import code.gui.initialize.AbstractSocket;
import code.threads.AbstractLock;

import java.io.Closeable;

public interface NetWindow {

    void gearClient(AbstractSocket _newSocket);

    void loop(Object _readObject, AbstractSocket _socket);

    void quitNetwork(Exiting _exit, Closeable _socket);

    AbstractLock getLock();
}
