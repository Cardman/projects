package code.network;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

import code.gui.GroupFrame;
import code.network.enums.IpType;
import code.serialize.SerializeXmlObject;
import code.stream.exceptions.RuntimeIOException;
import code.util.StringList;

public abstract class NetGroupFrame extends GroupFrame implements NetWindow {

    private static final String NO_POSSIBILITY_TO_CONNECT = "no possibility to connect on host:{0} with protocol: {1}";

    private Socket socket;

    private ConnectionToServer connection;

    private String ipHost;

    private int port;

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
            connection.start();
            return;
        } catch (RuntimeException _0) {
            _0.printStackTrace();
        }
    }

    /**server and client*/
    public void closeConnexion() {
        if (connection == null) {
            return;
        }
        connection.fermer();
    }

    public Socket createClient(String _host, IpType _ipType, boolean _first, int _port) {
        port = _port;
        if (_first) {
            try {
                Socket socket_ = new Socket(_host, _port);
                new BasicClient(socket_, this).start();
                initIndexInGame(_first);
                socket = socket_;
                return socket_;
            } catch (IOException _0) {
                _0.printStackTrace();
                return null;
            }
        }
        StringList allAddresses_ = NetCreate.getAllAddresses(_ipType, _host);
        if (allAddresses_.isEmpty()) {
            //TODO put message in error
            String error_ = StringList.simpleFormat(NO_POSSIBILITY_TO_CONNECT, _host, _ipType);
            Logger.getLogger(getClass().getName()).warning(error_);
            return null;
        }
        try {
            Socket socket_ = new Socket(allAddresses_.first(), _port);
            new BasicClient(socket_, this).start();
            initIndexInGame(_first);
            socket = socket_;
            return socket_;
        } catch (IOException _0) {
            _0.printStackTrace();
            return null;
        }
    }

    /**client
    Method allowing the client to send a serializable object by its socket
    @param _serializable the serializable object to be sent
    */
    public void sendObject(Object _serializable) {
        sendObject(socket,_serializable);
    }

    /**server and client*/
    public static void sendObject(Socket _socket,Object _serializable) {
        sendText(_socket,SerializeXmlObject.toXmlString(_serializable));
    }

    /**client*/
    public void sendQuit(Object _serializable) {
        try {
            PrintWriter out_ = new PrintWriter(socket.getOutputStream(), true);
            out_.println(SerializeXmlObject.toXmlString(_serializable));
        } catch (IOException _0) {
            throw new RuntimeIOException(_0);
        }
    }

    /**
    Method allowing the client to send text by its socket
    @param _text the text to be sent*/
    public void sendText(String _text) {
        sendText(socket,_text);
    }

    public static void sendText(Socket _socket, String _text) {
        try {
            OutputStream output_ = _socket.getOutputStream();
            PrintWriter out_ = new PrintWriter(output_, true);
            out_.println(_text);
        } catch (IOException _0) {
            _0.printStackTrace();
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
