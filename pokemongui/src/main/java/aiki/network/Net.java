package aiki.network;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import aiki.network.stream.CheckCompatibility;
import code.serialize.SerializeXmlObject;
import code.util.NatTreeMap;
import code.util.NumberMap;

public final class Net {

    public static final int NB_PLAYERS = 2;

    static final String RESOURCE_ANNOTATION = "resource";

    private static final String POKEMON = "POKEMON";

    private static NumberMap<Integer,Socket> _sockets_=new NumberMap<Integer,Socket>();
    private static NatTreeMap<Integer, Byte> _placesPlayers_ = new NatTreeMap<Integer, Byte>();
    private static NumberMap<Integer,Boolean> _readyPlayers_ = new NumberMap<Integer,Boolean>();

    private static NumberMap<Integer,CheckCompatibility> _checkCompatibility_ = new NumberMap<Integer,CheckCompatibility>();

    private static NumberMap<Integer,String> _nicknames_=new NumberMap<Integer,String>();
    private static NumberMap<Integer,SendReceiveServer> _connectionsServer_=new NumberMap<Integer,SendReceiveServer>();

//    //network: socket allowing a player to send and receive informations which is use for a game
//    private static Socket _socket_;
//    private static String _ipHost_;
    private static volatile NumberMap<Byte,Boolean> _activePlayers_;
    private static volatile NumberMap<Byte,Boolean> _received_;

//    private static ConnectionToServer _connection_;

//    private static final String NET_ZERO = "net0";
//    private static final String WLAN_ZERO = "wlan0";
//    private static final String NO_POSSIBILITY_TO_CONNECT = "no possibility to connect";

    /** A used port for connections*/
    private static final int PORT = 668;

    private Net() {
    }

    public static int getPort() {
        return PORT;
    }

    public static String getPokemon() {
        return POKEMON;
    }

    /**
        Method allowing a client to send text by its socket
        @param _socket The used socket (client) which is used for sending
        @param _text the text to be sent
    */
    static void sendText(Socket _socket, String _text) {
        try {
            OutputStream str_ = _socket.getOutputStream();
            PrintWriter out_ = new PrintWriter(str_, true);
            out_.println(_text);
        } catch (IOException _0) {
            _0.printStackTrace();
        }
    }
//
//        Method allowing the client to send text by its socket
//        @param _text the text to be sent
//    public static void sendText(String _text) {
//        sendText(_socket_,_text);
//    }

//server and client
//    public static void closeConnexion() throws IOException {
//        if (_connection_ == null) {
//            return;
//        }
//        checkSend();
//        _connection_.fermer();
//    }
    //bk: synchronized (only called from loop server)
    /**server
        @return true &hArr; the players are ready to begin a deal*/
    public static boolean allReady() {
        boolean allReady_ = true;
        for (boolean r: Net.getReadyPlayers().values()) {
            if (r) {
                continue;
            }
            allReady_ = false;
            break;
        }
        return allReady_;
    }

//
//        Method allowing the client to send a serializable object by its socket
//        @param _serializable the serializable object to be sent
//
//    public static void sendObject(Object _serializable) {
//        sendObject(_socket_,_serializable);
//    }

//    public static void sendQuit(Object _serializable) throws Exception {
//        checkSend();
//        PrintWriter out_ = new PrintWriter(_socket_.getOutputStream(), true);
//        out_.println(SerializeXmlObject.toXmlString(_serializable));
//    }
    public static void sendObject(Socket _socket,Object _serializable) {
        Net.sendText(_socket,SerializeXmlObject.toXmlString(_serializable));
    }

    //bk: synchronized (only called from loop server)
    /**server*/
    static void quit(byte _p) {
        if (_activePlayers_ == null) {
            return;
        }
//        _activePlayers_.synchronizedPut(_p, false);
        _activePlayers_.put(_p, false);
//        _received_.synchronizedPut(_p,true);
        _received_.put(_p,true);
    }

    /**server*/
    public static NumberMap<Integer,SendReceiveServer> getConnectionsServer() {
        return _connectionsServer_;
    }

    /**server*/
    public static NumberMap<Integer,Boolean> getReadyPlayers() {
        return _readyPlayers_;
    }

    /**server*/
    public static NumberMap<Integer,Socket> getSockets() {
        return _sockets_;
    }

    /**server*/
    public static NatTreeMap<Integer, Byte> getPlacesPlayers() {
        return _placesPlayers_;
    }

    /**server*/
    public static NumberMap<Integer,CheckCompatibility> getCheckCompatibility() {
        return _checkCompatibility_;
    }

    /**server*/
    public static NumberMap<Integer,String> getNicknames() {
        return _nicknames_;
    }
}
