package code.network;
import code.stream.Locking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**Thread safe class*/
public abstract class BasicServer extends SendReceive implements Locking {

    private NetGroupFrame net;

    public BasicServer(Socket _socket, NetGroupFrame _net) {
        super(_socket);
        net = _net;
    }

    @Override
    public void run() {
        InputStreamReader isr_;
        BufferedReader in_;
        try {
            isr_ = new InputStreamReader(getSocket().getInputStream());
            in_ = new BufferedReader(isr_);
            String input_;

            while (true) {
                input_ = in_.readLine();
                if (input_ == null) {
                    break;
                }
                Object readObject_ = net.getObject(input_);
                if (readObject_ == null) {
                    continue;
                }
                loopServer(input_, readObject_);
            }
            close(getSocket());
        } catch (IOException _0) {
            close(getSocket());
        }
    }

    private static void close(Socket _close) {
        if (_close == null) {
            return;
        }
        try {
            _close.close();
        } catch (IOException _0) {
            //
        }
    }

    public abstract void loopServer(String _input, Object _object);

    @Override
    public Thread getCurrentThread() {
        return Thread.currentThread();
    }

    @Override
    public boolean isCurrentThreadEnded() {
        return false;
    }
}
