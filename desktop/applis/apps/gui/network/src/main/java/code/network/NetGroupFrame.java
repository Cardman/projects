package code.network;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import code.gui.CustComponent;
import code.gui.GroupFrame;
import code.gui.initialize.AbstractProgramInfos;
import code.network.enums.ErrorHostConnectionType;
import code.network.enums.IpType;
import code.threads.AbstractLock;
import code.threads.AbstractThread;
import code.threads.LockFactory;
import code.util.StringList;

public abstract class NetGroupFrame extends GroupFrame implements NetWindow {

    private Socket socket;

    private ConnectionToServer connection;

    private String ipHost;

    private int port;
    private final AbstractLock lock = LockFactory.newLock();

    protected NetGroupFrame(String _lg, AbstractProgramInfos _list) {
        super(_lg, _list);
    }
    /**
        Create a server then a client
        If is not possible to create a server, then no connection can be established
        @param _window the used window of the player for creating a server
        @param _ipHost the chosen IP address
    */
    public void createServer(String _ipHost, IpType _ipType, int _port) {
        String ip_ = NetCreate.getHostAddress(_ipType, _ipHost);
        ServerSocket serverSocket_ = NetCreate.createServerSocket(ip_, _port);
        tryCreateServer(_port, ip_, serverSocket_);
    }

    private void tryCreateServer(int _port, String _ip, ServerSocket _serverSocket) {
        if (_serverSocket == null) {
            return;
        }
        ipHost = _ip;
        connection=new ConnectionToServer(_serverSocket,this, _ip, _port);
        getThreadFactory().newStartedThread(connection);
    }

    /**server and client*/
    public void closeConnexion(Socket _socket) {
        if (connection == null) {
            return;
        }
        connection.fermer(_socket);
    }

    public SocketResults createClient(String _host, IpType _ipType, boolean _first, int _port) {
        port = _port;
        if (_first) {
            return getSocketResults(true, _port, _host);
        }
        StringList allAddresses_ = NetCreate.getAllAddresses(_ipType, _host);
        if (allAddresses_ == null) {
            return new SocketResults(ErrorHostConnectionType.UNKNOWN_HOST);
        }
        if (allAddresses_.isEmpty()) {
            return new SocketResults(ErrorHostConnectionType.NO_ADDRESS);
        }
        String first_ = allAddresses_.first();
        return getSocketResults(false, _port, first_);
    }

    private SocketResults getSocketResults(boolean _first, int _port, String _address) {
        try {
            Socket socket_ = new Socket(_address, _port);
            getThreadFactory().newStartedThread(new BasicClient(socket_, this));
            initIndexInGame(_first);
            socket = socket_;
            return new SocketResults(socket_);
        } catch (UnknownHostException e) {
            return new SocketResults(ErrorHostConnectionType.UNKNOWN_HOST);
        } catch (IOException e) {
            return new SocketResults(ErrorHostConnectionType.UNKNOWN_ERROR);
        }
    }

    /**server and client
    Method allowing the client to send a serializable object by its socket
    @param _serializable the serializable object to be sent
    */
    public boolean sendObject(Object _serializable) {
        if (socket == null) {
            return false;
        }
        String str_ = setObject(_serializable);
        return trySendString(str_, socket);
    }

    public static boolean trySendString(String _str, Socket _socket) {
        try {
            OutputStream output_ = _socket.getOutputStream();
            PrintWriter out_ = new PrintWriter(output_, true);
            out_.println(_str);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public abstract Object getObject(String _object);
    public abstract String setObject(Object _object);

    public int getPort() {
        return port;
    }

    public Socket getSocket() {
        return socket;
    }

    public String getIpHost() {
        return ipHost;
    }

    @Override
    public AbstractLock getLock() {
        return lock;
    }

    /**
    @param _first if the connected player is the first player
    */
    public void initIndexInGame(boolean _first) {
    }
}
