package code.network;

import code.gui.GroupFrame;
import code.gui.initialize.AbstractProgramInfos;
import code.gui.initialize.AbstractServerSocket;
import code.gui.initialize.AbstractSocket;
import code.gui.initialize.AbstractSocketFactory;
import code.network.enums.ErrorHostConnectionType;
import code.network.enums.IpType;
import code.sml.Document;
import code.threads.AbstractBaseExecutorService;
import code.threads.AbstractLock;
import code.threads.LockFactory;
import code.util.StringList;

public abstract class NetGroupFrame extends GroupFrame implements NetWindow {

    private AbstractSocket socket;

    private ConnectionToServer connection;

    private String ipHost;

    private int port;
    private final AbstractBaseExecutorService lock;

    protected NetGroupFrame(String _lg, AbstractProgramInfos _list) {
        super(_lg, _list);
        lock = _list.getThreadFactory().newExecutorService();
    }
    /**
        Create a server then a client
        If is not possible to create a server, then no connection can be established
        @param _window the used window of the player for creating a server
        @param _ipHost the chosen IP address
    */
    public void createServer(String _ipHost, IpType _ipType, int _port) {
        String ip_ = NetCreate.getHostAddress(getSocketFactory(),_ipType, _ipHost);
        AbstractServerSocket serverSocket_ = getSocketFactory().newServerSocket(ip_, _port);
        if (serverSocket_.isOk()) {
            ipHost = ip_;
            connection = new ConnectionToServer(serverSocket_, this, ip_, _port);
            getThreadFactory().newStartedThread(connection);
        }
    }

    /**server and client*/
    public void closeConnexion(AbstractSocket _socket) {
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
        if (_socket.isKo()) {
            return new SocketResults(ErrorHostConnectionType.UNKNOWN_HOST);
        }
        getThreadFactory().newStartedThread(new BasicClient(_socket, this));
        socket =  initIndexInGame(_first,_socket);
        return new SocketResults(_socket);
    }

    public AbstractSocketFactory getSocketFactory() {
        return getFrames().getSocketFactory();
    }

    public static boolean trySendString(String _str, AbstractSocket _socket) {
        if (_socket == null || _socket.isKo()) {
            return false;
        }
        return !_socket.println(_str).isEmpty();
    }

    public abstract Document getDoc(String _object);
    public abstract Exiting getExiting(Document _doc);

    public int getPort() {
        return port;
    }

    protected AbstractSocket getSocket() {
        return socket;
    }

    public String getIpHost() {
        return ipHost;
    }

    public static boolean match(byte[] _address, int _size) {
        return _address != null && _address.length == _size;
    }
    @Override
    public AbstractBaseExecutorService getLock() {
        return lock;
    }

    //_first if the connected player is the first player
    public abstract AbstractSocket initIndexInGame(boolean _first, AbstractSocket _socket);
}
