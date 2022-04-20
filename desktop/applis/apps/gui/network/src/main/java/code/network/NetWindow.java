package code.network;
import code.gui.initialize.AbstractSocket;
import code.sml.Document;
import code.threads.AbstractBaseExecutorService;
import code.threads.AbstractLock;

public interface NetWindow {

    void gearClient(AbstractSocket _newSocket);

    void loop(Document _readObject, AbstractSocket _socket);

    void quitNetwork(Exiting _exit, AbstractSocket _socket);

    AbstractBaseExecutorService getLock();
}
