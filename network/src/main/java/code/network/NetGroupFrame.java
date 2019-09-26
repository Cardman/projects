package code.network;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import code.gui.CustComponent;
import code.gui.GroupFrame;
import code.network.enums.ErrorHostConnectionType;
import code.network.enums.IpType;
import code.util.StringList;

public abstract class NetGroupFrame extends GroupFrame implements NetWindow {

    private Socket socket;

    private Thread connectionTh;
    private ConnectionToServer connection;

    private String ipHost;

    private int port;

    protected NetGroupFrame(String _lg) {
        super(_lg);
    }
    /**
        Create a server then a client
        If is not possible to create a server, then no connection can be established
        @param _window the used window of the player for creating a server
        @param _ipHost the chosen IP address
    */
    public void createServer(String _ipHost, IpType _ipType, int _port) {
        try {
            String ip_ = NetCreate.getHostAddress(_ipType, _ipHost);
            ServerSocket serverSocket_ = NetCreate.createServerSocket(ip_, _port);
            if (serverSocket_ == null) {
                return;
            }
            ipHost = ip_;
            connection=new ConnectionToServer(serverSocket_,this,ip_, _port);
            connectionTh = CustComponent.newThread(connection);
            connectionTh.start();
        } catch (RuntimeException _0) {
        }
    }

    /**server and client*/
    public boolean closeConnexion(Socket _socket) {
        if (connection == null) {
            return true;
        }
        return connection.fermer(_socket);
    }

    public SocketResults createClient(String _host, IpType _ipType, boolean _first, int _port) {
        port = _port;
        if (_first) {
            try {
                Socket socket_ = new Socket(_host, _port);
                CustComponent.newThread(new BasicClient(socket_, this)).start();
                initIndexInGame(_first);
                socket = socket_;
                return new SocketResults(socket_);
            } catch (SecurityException _0) {
                return new SocketResults(ErrorHostConnectionType.SECURITY);
            } catch (UnknownHostException _0) {
                return new SocketResults(ErrorHostConnectionType.UNKNOWN_HOST);
            } catch (Throwable _0) {
                return new SocketResults(ErrorHostConnectionType.UNKNOWN_ERROR);
            }
        }
        StringList allAddresses_ = NetCreate.getAllAddresses(_ipType, _host);
        if (allAddresses_ == null) {
            return new SocketResults(ErrorHostConnectionType.UNKNOWN_HOST);
        }
        if (allAddresses_.isEmpty()) {
            return new SocketResults(ErrorHostConnectionType.NO_ADDRESS);
        }
        try {
            Socket socket_ = new Socket(allAddresses_.first(), _port);
            CustComponent.newThread(new BasicClient(socket_, this)).start();
            initIndexInGame(_first);
            socket = socket_;
            return new SocketResults(socket_);
        } catch (SecurityException _0) {
            return new SocketResults(ErrorHostConnectionType.SECURITY);
        } catch (UnknownHostException _0) {
            return new SocketResults(ErrorHostConnectionType.UNKNOWN_HOST);
        } catch (Throwable _0) {
            return new SocketResults(ErrorHostConnectionType.UNKNOWN_ERROR);
        }
    }

    /**server and client
    Method allowing the client to send a serializable object by its socket
    @param _serializable the serializable object to be sent
    */
    public void sendObject(Object _serializable) {
        sendText(socket, setObject(_serializable));
    }

    public abstract Object getObject(String _object);
    public abstract String setObject(Object _object);

    /**client*/
    protected boolean sendCheckedObject(Object _serializable) {
        try {
            PrintWriter out_ = new PrintWriter(socket.getOutputStream(), true);
            out_.println(setObject(_serializable));
            return true;
        } catch (IOException _0) {
            return false;
        }
    }

    public static void sendText(Socket _socket, String _text) {
        try {
            OutputStream output_ = _socket.getOutputStream();
            PrintWriter out_ = new PrintWriter(output_, true);
            out_.println(_text);
        } catch (Exception _0) {
        }
    }

    public int getPort() {
        return port;
    }

    public Socket getSocket() {
        return socket;
    }

    public String getIpHost() {
        return ipHost;
    }

    /**
    @param _first if the connected player is the first player
    */
    public void initIndexInGame(boolean _first) {
    }
}
