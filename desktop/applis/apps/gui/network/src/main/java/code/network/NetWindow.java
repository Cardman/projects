package code.network;
import code.stream.AbstractLock;

import java.net.Socket;

public interface NetWindow {

    void gearClient(Socket _newSocket);

    void loop(Object _readObject, Socket _socket);

    void quitNetwork(Exiting _exit, Socket _socket);

    AbstractLock getLock();
}
