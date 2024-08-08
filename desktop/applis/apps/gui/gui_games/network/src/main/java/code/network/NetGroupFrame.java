package code.network;

import code.gui.AbsOpenQuit;
import code.gui.GroupFrame;
import code.gui.GuiBaseUtil;
import code.gui.initialize.AbstractProgramInfos;
import code.gui.initialize.AbstractSocket;
import code.gui.initialize.AbstractSocketFactory;
import code.network.enums.ErrorHostConnectionType;
import code.network.enums.IpType;
import code.threads.AbstractBaseExecutorService;
import code.util.StringList;

public abstract class NetGroupFrame extends GroupFrame implements NetWindow, AbsOpenQuit {

    private AbstractSocket socket;

//    private ConnectionToServer connection;

//    private String ipHost;

//    private int port;
    private final AbstractBaseExecutorService lock;
//    private AbstractScheduledExecutorService server;
//    private AbstractFuture task;
    private final NetCommon sockets;

    protected NetGroupFrame(AbstractProgramInfos _list) {
        super(_list);
        sockets = new NetCommon();
        GuiBaseUtil.choose(this);
        lock = _list.getThreadFactory().newExecutorService();
    }
//    /**
//        Create a server then a client
//        If is not possible to create a server, then no connection can be established
//        @param _window the used window of the player for creating a server
//        @param _ipHost the chosen IP address
//    */
//    public void createServer(String _ipHost, IpType _ipType, int _port) {
//        String ip_ = NetCreate.getHostAddress(getSocketFactory(),_ipType, _ipHost);
//        AbstractServerSocket serverSocket_ = getSocketFactory().newServerSocket(ip_, _port);
//        if (serverSocket_.isOk()) {
//            ipHost = ip_;
//            server = getFrames().getThreadFactory().newScheduledExecutorService();
//            connection = new ConnectionToServer(serverSocket_, this, ip_, _port);
//            task = server.scheduleAtFixedRateNanos(connection,0,1);
//        }
//    }
//
//    /**server and client*/
//    public void closeConnexion(Exiting _exit, AbstractSocket _socket) {
//        _socket.close();
//        if (connection == null || !_exit.isServer()) {
//            return;
//        }
//        connection.fermer();
//        task.cancel(false);
//        server.shutdown();
//    }

    public SocketResults createClient(String _host, IpType _ipType, int _port) {
//        port = _port;
//        if (_first) {
//            return getFirstSocketResults(_port);
//        }
        StringList allAddresses_ = NetCreate.getAllAddresses(getSocketFactory(),_ipType, _host);
        if (allAddresses_.isEmpty()) {
            return new SocketResults(ErrorHostConnectionType.UNKNOWN_HOST);
        }
        String first_ = allAddresses_.first();
        return getNextSocketResults(_port, first_);
    }

    public SocketResults getFirstSocketResults(int _port) {
        AbstractSocket socket_ = getSocketFactory().newSocket(_port);
        return results(socket_);
    }

    private SocketResults getNextSocketResults(int _port, String _address) {
        AbstractSocket socket_ = getSocketFactory().newSocket(_port, _address);
        return results(socket_);
    }

    SocketResults results(AbstractSocket _socket) {
        if (_socket.isKo()) {
            return new SocketResults(ErrorHostConnectionType.UNKNOWN_HOST);
        }
        socket = initIndexInGame(_socket);
        return new SocketResults();
    }

    public AbstractSocketFactory getSocketFactory() {
        return getFrames().getSocketFactory();
    }
    /**
     Method allowing a client to send text by its socket
     @param _socket The used socket (client) which is used for sending
     @param _str the text to be sent
     */
    public static boolean trySendString(String _str, AbstractSocket _socket) {
        if (_socket == null || _socket.isKo()) {
            return false;
        }
        return !_socket.println(_str).isEmpty();
    }

//    public int getPort() {
//        return port;
//    }

    public AbstractSocket getSocket() {
        return socket;
    }

//    public String getIpHost() {
//        return ipHost;
//    }

    public static boolean match(byte[] _address, int _size) {
        return _address != null && _address.length == _size;
    }
    @Override
    public AbstractBaseExecutorService getLock() {
        return lock;
    }

    //_first if the connected player is the first player
    public abstract AbstractSocket initIndexInGame(AbstractSocket _socket);

    public NetCommon getSockets() {
        return sockets;
    }
//
//    public ConnectionToServer getConnection() {
//        return connection;
//    }
}
