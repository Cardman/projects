package code.network;
import code.gui.initialize.AbstractBufferedReader;
import code.gui.initialize.AbstractSocket;
import code.sml.Document;
import code.threads.AbstractThreadFactory;
import code.threads.Locking;

/**Thread safe class*/
public abstract class BasicServer extends SendReceive implements Locking {
    private final NetCommon sockets;
    private final AbstractThreadFactory threadFactory;
    private final NetGroupFrame net;

    protected BasicServer(AbstractSocket _socket, NetGroupFrame _net) {
        super(_socket);
        net = _net;
        sockets = _net.getSockets();
        threadFactory = _net.getThreadFactory();
    }

    @Override
    public void run() {

        AbstractBufferedReader inputSock_ = getSocket().getInput();
        while (true) {
            String input_ = inputSock_.readLine();
            if (input_ == null) {
                break;
            }
            loopServer(input_);
        }
        getSocket().close();
    }
    public void loopServer(String _input){
        Document readObject_ = net.getDoc(_input);
        if (readObject_ != null) {
            loopServer(_input, readObject_);
        }
    }

    public abstract void loopServer(String _input, Document _object);

    @Override
    public AbstractThreadFactory getCurrentThreadFactory() {
        return threadFactory;
    }

//    @Override
//    public AbstractThread getCurrentThread() {
//        return getNet().getThreadFactory().newThread();
//    }

    @Override
    public boolean isCurrentThreadEnded() {
        return false;
    }

    public NetCommon getSockets() {
        return sockets;
    }
}
