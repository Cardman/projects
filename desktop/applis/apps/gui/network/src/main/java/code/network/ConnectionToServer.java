package code.network;
import code.gui.initialize.AbstractProgramInfos;
import code.gui.initialize.AbstractServerSocket;
import code.gui.initialize.AbstractSocket;
import code.threads.AbstractThread;
import code.threads.AbstractThreadFactory;
import code.threads.Locking;

import java.io.Closeable;

/**Thread safe class*/
public final class ConnectionToServer implements Runnable, Locking {

    private final AbstractServerSocket serverSocket;
    private final NetGroupFrame serverWindow;

    /**This class thread is independant from EDT*/
    public ConnectionToServer(AbstractServerSocket _serverSocket,NetGroupFrame _serverWindow,String _ipHost, int _port){
        serverSocket=_serverSocket;
        serverWindow = _serverWindow;
        serverWindow.createClient(_ipHost, null, true, _port);
    }
    public void fermer(Closeable _socket) {
        if (serverSocket == null){
            return;
        }
        AbstractProgramInfos frames_ = serverWindow.getFrames();
        if (frames_.close(serverSocket.getClos())) {
            frames_.close(_socket);
        }
    }

    @Override
    public void run(){
        while(true){
            AbstractSocket socket_ = acceptedSocket();
            //If the server is started without client ==> pause.
            if (socket_.getClos() != null) {
                serverWindow.gearClient(socket_);
            } else if (serverSocket.isClosed()) {
                break;
            }
            //server side
        }
    }

    private AbstractSocket acceptedSocket() {
        return serverSocket.accept();
    }

    @Override
    public AbstractThreadFactory getCurrentThreadFactory() {
        return serverWindow.getThreadFactory();
    }
    @Override
    public AbstractThread getCurrentThread() {
        return serverWindow.getThreadFactory().newThread();
    }

    @Override
    public boolean isCurrentThreadEnded() {
        return false;
    }
}
