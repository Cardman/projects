package code.network;
import java.io.*;

import code.gui.GroupFrame;
import code.gui.initialize.AbstractProgramInfos;
import code.gui.initialize.AbstractServerSocket;
import code.gui.initialize.AbstractSocket;
import code.gui.initialize.AbstractSocketFactory;
import code.network.enums.ErrorHostConnectionType;
import code.network.enums.IpType;
import code.threads.AbstractLock;
import code.threads.LockFactory;
import code.util.StringList;

public abstract class NetGroupFrame extends GroupFrame implements NetWindow {

    private AbstractSocket socket;

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
        String ip_ = NetCreate.getHostAddress(getSocketFactory(),_ipType, _ipHost);
        AbstractServerSocket serverSocket_ = NetCreate.createServerSocket(getSocketFactory(),ip_, _port);
        tryCreateServer(_port, ip_, serverSocket_);
    }

    private void tryCreateServer(int _port, String _ip, AbstractServerSocket _serverSocket) {
        if (_serverSocket == null) {
            return;
        }
        ipHost = _ip;
        connection=new ConnectionToServer(_serverSocket,this, _ip, _port);
        getThreadFactory().newStartedThread(connection);
    }

    /**server and client*/
    public void closeConnexion(Closeable _socket) {
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
        StringList allAddresses_ = NetCreate.getAllAddresses(getSocketFactory(),_ipType, _host);
        if (allAddresses_.isEmpty()) {
            return new SocketResults(ErrorHostConnectionType.UNKNOWN_HOST);
        }
        String first_ = allAddresses_.first();
        return getSocketResults(false, _port, first_);
    }

    private SocketResults getSocketResults(boolean _first, int _port, String _address) {
        AbstractSocket socket_ = getSocketFactory().newSocket(_port, _address);
        return results(_first, socket_);
    }

    private SocketResults results(boolean _first, AbstractSocket _socket) {
        if (_socket.getClos() == null) {
            return new SocketResults(ErrorHostConnectionType.UNKNOWN_HOST);
        }
        getThreadFactory().newStartedThread(new BasicClient(_socket, this));
        initIndexInGame(_first);
        socket = _socket;
        return new SocketResults(_socket);
    }

    public AbstractSocketFactory getSocketFactory() {
        return getFrames().getSocketFactory();
    }

    public static boolean trySendString(String _str, AbstractSocket _socket) {
        if (_socket == null || _socket.getClos() == null) {
            return false;
        }
        _socket.println(_str);
        return true;
    }

    public abstract Object getObject(String _object);
    public abstract String setObject(Object _object);

    public int getPort() {
        return port;
    }

    protected AbstractSocket getSocket() {
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
