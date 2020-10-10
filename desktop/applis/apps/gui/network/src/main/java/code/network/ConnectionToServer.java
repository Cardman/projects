package code.network;
import code.stream.core.StreamCoreUtil;
import code.threads.Locking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**Thread safe class*/
public final class ConnectionToServer implements Runnable, Locking {

    private ServerSocket serverSocket;
    private NetGroupFrame serverWindow;
    private Socket socket;
    private boolean accept;
    private boolean errorSocket;
    private boolean error;
    /**This class thread is independant from EDT*/
    public ConnectionToServer(ServerSocket _serverSocket,NetGroupFrame _serverWindow,String _ipHost, int _port){
        serverSocket=_serverSocket;
        serverWindow = _serverWindow;
        serverWindow.createClient(_ipHost, null, true, _port);
    }
    public void fermer(Socket _socket) {
        if (serverSocket == null){
            return;
        }
        if (StreamCoreUtil.close(serverSocket)) {
            StreamCoreUtil.close(_socket);
        }
    }

    @Override
    public void run(){
        while(true){
            accept = true;
            Socket socket_ = acceptSocket();
            //If the server is started without client ==> pause.
            if (!error && !errorSocket) {
                serverWindow.gearClient(socket_);
                accept = false;
            } else if (errorSocket) {
                if (serverSocket.isClosed()) {
                    break;
                }
            }
            //server side
        }
    }

    private Socket acceptSocket() {
        errorSocket = false;
        error = false;
        if (accept) {
            try {
                socket = serverSocket.accept();
            } catch (SocketException e) {
                errorSocket = true;
                return null;
            } catch (IOException e) {
                error = true;
                return null;
            }
        }
        return socket;
    }

    @Override
    public Thread getCurrentThread() {
        return Thread.currentThread();
    }

    @Override
    public boolean isCurrentThreadEnded() {
        return false;
    }
}
