package code.network;
import code.gui.initialize.AbstractSocket;
import code.threads.AbstractBaseExecutorService;

public interface NetWindow {

    void gearClient(AbstractSocket _newSocket);

    AbstractBaseExecutorService getLock();
}
