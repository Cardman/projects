package code.network;
import code.gui.initialize.AbstractServerSocket;
import code.gui.initialize.AbstractSocket;

/**Thread safe class*/
public final class ConnectionToServer implements Runnable {

    private final AbstractServerSocket serverSocket;
    private final NetGroupFrame serverWindow;

    /**This class thread is independant from EDT*/
    public ConnectionToServer(AbstractServerSocket _serverSocket, NetGroupFrame _serverWindow, int _port){
        serverSocket=_serverSocket;
        serverWindow = _serverWindow;
        serverWindow.getFirstSocketResults(_port);
    }
    public void fermer() {
        serverSocket.close();
    }

    @Override
    public void run(){
        AbstractSocket socket_ = serverSocket.accept();
        //If the server is started without client ==> pause.
        if (!socket_.isKo()) {
            serverWindow.gearClient(socket_);
        }
        //server side
    }

//    @Override
//    public AbstractThreadFactory getCurrentThreadFactory() {
//        return serverWindow.getThreadFactory();
//    }
//    @Override
//    public AbstractThread getCurrentThread() {
//        return serverWindow.getThreadFactory().newThread();
//    }

//    @Override
//    public boolean isCurrentThreadEnded() {
//        return false;
//    }
}
