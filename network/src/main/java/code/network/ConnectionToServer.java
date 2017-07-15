package code.network;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import code.stream.exceptions.RuntimeIOException;

/**Thread safe class*/
public final class ConnectionToServer extends Thread {

    private ServerSocket serverSocket;
    private NetGroupFrame serverWindow;
    private Socket socket;
    private boolean accept;
    /**This class thread is independant from EDT*/
    public ConnectionToServer(ServerSocket _serverSocket,NetGroupFrame _serverWindow,String _ipHost, int _port){
        serverSocket=_serverSocket;
        serverWindow = _serverWindow;
        serverWindow.createClient(_ipHost, null, true, _port);
    }
    public void fermer() {
        try {
            serverSocket.close();
        } catch (IOException _0) {
            throw new RuntimeIOException(_0);
        }
    }

    @Override
    public void run(){
        while(true){
            try {

                accept = true;
                Socket socket_ = acceptSocket();
                //If the server is started without client ==> pause.
                serverWindow.gearClient(socket_);
                accept = false;
                //server side
            }catch (RuntimeSocketException _0) {
                if (serverSocket.isClosed()) {
                    break;
                }
                _0.printStackTrace();
            }catch (RuntimeIOException _0) {
                _0.printStackTrace();
            }
        }
    }

    private Socket acceptSocket() {
        if (accept) {
            try {
                socket = serverSocket.accept();
            } catch (SocketException _0) {
                throw new RuntimeSocketException(_0);
            } catch (IOException _0) {
                throw new RuntimeIOException(_0);
            }
        }
        return socket;
    }

//    @Override
//    public void run(){
//        while(true){
//            try {
//
//                accept = true;
//                Socket socket_ = acceptSocket();
//                //If the server is started without client ==> pause.
//                serverWindow.gearClient(socket_);
//                accept = false;
//                //server side
//            }catch (SocketException _0) {
//                if (serverSocket.isClosed()) {
//                    break;
//                }
//                _0.printStackTrace();
//            }catch (IOException _0) {
//                _0.printStackTrace();
//            }
//        }
//    }
//
//    private Socket acceptSocket() throws IOException {
//        if (accept) {
//            socket = serverSocket.accept();
//        }
//        return socket;
//    }
}
