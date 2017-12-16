package code.network;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.InputStreamReader;
import java.net.Socket;

import code.serialize.SerializeXmlObject;

/**Thread safe class*/
public abstract class BasicServer extends SendReceive {

    public BasicServer(Socket _socket) {
        super(_socket);
    }

    @Override
    public void run() {
        InputStreamReader isr_ = null;
        BufferedReader in_ = null;
        try {
            isr_ = new InputStreamReader(getSocket().getInputStream());
            in_ = new BufferedReader(isr_);
            String input_;

            while (true) {
                input_ = in_.readLine();
                if (input_ == null) {
                    break;
                }
                Object readObject_ = SerializeXmlObject.newObjectFromXmlString(input_);
                if (readObject_ == null) {
                    continue;
                }
                loopServer(input_, readObject_);
            }
            close(in_);
            close(isr_);
            close(getSocket());
        } catch (Throwable _0) {
            close(in_);
            close(isr_);
            close(getSocket());
        }
    }

    private static void close(Closeable _close) {
        try {
            _close.close();
        } catch (Throwable _0) {
        }
    }

    private static void close(Socket _close) {
        try {
            _close.close();
        } catch (Throwable _0) {
        }
    }
    public abstract void loopServer(String _input, Object _object);
}
