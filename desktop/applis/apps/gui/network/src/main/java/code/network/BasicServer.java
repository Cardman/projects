package code.network;
import code.gui.initialize.AbstractBufferedReader;
import code.gui.initialize.AbstractSocket;
import code.threads.AbstractThread;
import code.threads.AbstractThreadFactory;
import code.threads.Locking;

/**Thread safe class*/
public abstract class BasicServer extends SendReceive implements Locking {


    private final AbstractBufferedReader input;

    public BasicServer(AbstractSocket _socket, NetGroupFrame _net) {
        super(_socket,_net);
        input = _socket.getInput();
    }

    @Override
    public void run() {

        while (true) {
            String input_ = input.readLine();
            if (input_ == null) {
                break;
            }
            Object readObject_ = getNet().getObject(input_);
            if (readObject_ == null) {
                continue;
            }
            loopServer(input_, readObject_);
        }
        getNet().getFrames().close(getClosSocket());
    }

    public abstract void loopServer(String _input, Object _object);

    @Override
    public AbstractThreadFactory getCurrentThreadFactory() {
        return getNet().getThreadFactory();
    }

    @Override
    public AbstractThread getCurrentThread() {
        return getNet().getThreadFactory().newThread();
    }

    @Override
    public boolean isCurrentThreadEnded() {
        return false;
    }
}
