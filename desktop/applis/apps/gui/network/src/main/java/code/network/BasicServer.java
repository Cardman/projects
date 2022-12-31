package code.network;
import code.gui.initialize.AbstractBufferedReader;
import code.gui.initialize.AbstractSocket;
import code.sml.Document;
import code.threads.AbstractThreadFactory;
import code.threads.Locking;

/**Thread safe class*/
public abstract class BasicServer extends SendReceive implements Locking {
    private final NetCommon sockets;
    protected BasicServer(AbstractSocket _socket, NetGroupFrame _net) {
        super(_socket,_net);
        sockets = _net.getSockets();
    }

    @Override
    public void run() {

        AbstractBufferedReader inputSock_ = getSocket().getInput();
        while (true) {
            String input_ = inputSock_.readLine();
            if (input_ == null) {
                break;
            }
            Document readObject_ = getNet().getDoc(input_);
            if (readObject_ != null) {
                loopServer(input_, readObject_);
            }
        }
        getSocket().close();
    }

    public abstract void loopServer(String _input, Document _object);

    @Override
    public AbstractThreadFactory getCurrentThreadFactory() {
        return getNet().getThreadFactory();
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
