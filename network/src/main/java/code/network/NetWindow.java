package code.network;
import java.net.Socket;

public interface NetWindow {

    void gearClient(Socket _newSocket);

    void loop(Object _readObject, Socket _socket);

    void quitNetwork(Exiting _exit, Socket _socket);
}
