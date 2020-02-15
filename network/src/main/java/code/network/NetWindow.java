package code.network;
import code.stream.AbstractLock;

import java.net.Socket;

public interface NetWindow {

    void gearClient(Socket _newSocket, AbstractLock _lock);

    void loop(Object _readObject, Socket _socket);

    void quitNetwork(Exiting _exit, Socket _socket);
}
