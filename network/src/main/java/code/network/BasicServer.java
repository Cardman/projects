package code.network;
import java.io.BufferedReader;
import java.io.IOException;
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
        try {
            InputStreamReader isr_ = new InputStreamReader(getSocket().getInputStream());
            BufferedReader in_ = new BufferedReader(isr_);
            String input_;

            while (true) {
                input_ = in_.readLine();
                if (input_ == null) {
                    break;
                }
                Object readObject_ = null;
                try {
                    readObject_ = SerializeXmlObject.newObjectFromXmlStringOrNull(input_);
                } catch (RuntimeException _0) {
                    _0.printStackTrace();
                    continue;
                }
                loopServer(input_, readObject_);
            }
            in_.close();
            isr_.close();
            getSocket().close();

        } catch (IOException _0) {
            _0.printStackTrace();
        } catch (RuntimeException _0) {
            _0.printStackTrace();
        }
    }

    public abstract void loopServer(String _input, Object _object);
}
