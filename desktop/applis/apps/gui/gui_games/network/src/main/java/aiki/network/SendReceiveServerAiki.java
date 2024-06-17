package aiki.network;

import code.gui.initialize.*;
import code.network.*;
import code.threads.*;

/**This class thread is independant from EDT,
Thread safe class*/
public final class SendReceiveServerAiki extends BasicServer {

    private final AbstractBaseExecutorService lock;

    private final NetAiki instance;

    /**This class thread is independant from EDT*/
    public SendReceiveServerAiki(AbstractSocket _socket, NetGroupFrame _net, NetAiki _instance) {
        super(_socket, _net);
        lock = _net.getLock();
        instance = _instance;
    }

    @Override
    public void loopServer(String _input) {
        lock.execute(new ServerIterationPk(instance, _input, getSockets()));
    }

}
