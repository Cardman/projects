package code.network;
import code.threads.AbstractThread;
import code.threads.AbstractThreadFactory;
import code.threads.Locking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**Thread safe class*/
public abstract class BasicServer extends SendReceive implements Locking {


    public BasicServer(Socket _socket, NetGroupFrame _net) {
        super(_socket,_net);
    }

    @Override
    public void run() {
        try {
            InputStreamReader isr_ = new InputStreamReader(getSocket().getInputStream());
            BufferedReader in_ = new BufferedReader(isr_);
            String input_;

            while (true) {
                input_ = in_.readLine();
                if (input_ == null) {
                    break;
                }
                Object readObject_ = getNet().getObject(input_);
                if (readObject_ == null) {
                    continue;
                }
                loopServer(input_, readObject_);
            }
            getNet().getFrames().close(getSocket());
        } catch (IOException e) {
            getNet().getFrames().close(getSocket());
        }
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
