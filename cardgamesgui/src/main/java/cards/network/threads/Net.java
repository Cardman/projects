package cards.network.threads;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import cards.facade.Games;
import cards.network.common.Bye;
import cards.network.common.DelegateServer;
import cards.network.common.Quit;
import cards.network.sml.DocumentWriterCardsMultiUtil;
import code.util.CustList;
import code.util.EntryCust;
import code.util.NatTreeMap;
import code.util.NumberMap;
import code.util.Numbers;

public final class Net {

    static final String RESOURCE_ANNOTATION = "resource";


    private static int _nbPlayers_;

    private static final String CARDS = "CARDS";

    private static boolean _progressingGame_;

    /**A useful facade for games*/

    private static Games _games_ = new Games();


    private static NumberMap<Integer,Socket> _sockets_=new NumberMap<Integer,Socket>();

    private static NatTreeMap<Integer, Byte> _placesPlayers_ = new NatTreeMap<Integer, Byte>();

    private static NumberMap<Integer,Boolean> _readyPlayers_ = new NumberMap<Integer,Boolean>();


    private static NumberMap<Integer,String> _nicknames_=new NumberMap<Integer,String>();

    private static NumberMap<Integer,SendReceiveServer> _connectionsServer_=new NumberMap<Integer,SendReceiveServer>();

    private static NumberMap<Integer,String> _playersLocales_ = new NumberMap<Integer,String>();

    private static CustList<Numbers<Long>> _scores_ = new CustList<Numbers<Long>>();

//    private static final String NET_ZERO = "net0";
//    private static final String WLAN_ZERO = "wlan0";
//    private static final String NO_POSSIBILITY_TO_CONNECT = "no possibility to connect";

//    // network: socket allowing a player to send and receive informations which is use for a game
//    private static Socket _socket_;
//    private static String _ipHost_;
    private static volatile NumberMap<Byte,Boolean> _activePlayers_;
    private static volatile NumberMap<Byte,Boolean> _received_;

//    private static ConnectionToServer _connection_;

    /** A used port for connections*/
    private static final int PORT = 667;

    private Net() {
    }

    public static int getPort() {
        return PORT;
    }

    public static String getCards() {
        return CARDS;
    }

    /**
    Method allowing a client to send text by its socket
    @param _socket The used socket (client) which is used for sending
    @param _text the text to be sent
    */
    static void sendText(Socket _socket, String _text) {
        try {
            OutputStream output_ = _socket.getOutputStream();
            PrintWriter out_ = new PrintWriter(output_, true);
            out_.println(_text);
        } catch (IOException _0) {
            _0.printStackTrace();
        }
    }
//
//    Method allowing the client to send text by its socket
//    @param _text the text to be sent
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
    /**server
    only clicks can call directly this method
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
    /**server
    @return true &hArr; the players are correctly placed around the "table"*/
    public static boolean distinctPlaces() {
        boolean distinct_ = true;
        Numbers<Byte> places_ = new Numbers<Byte>();
        for (byte r: activePlayers()) {
            if (places_.containsObj(r)) {
                distinct_ = false;
                break;
            }
            places_.add(r);
        }
        return distinct_;
    }

//client
//    Method allowing the client to send a serializable object by its socket
//    @param _serializable the serializable object to be sent
//
//    public static void sendObject(Object _serializable) {
//        sendObject(_socket_,_serializable);
//    }
//
//client
//    public static void sendQuit(Object _serializable) throws Exception {
//        checkSend();
//        PrintWriter out_ = new PrintWriter(_socket_.getOutputStream(), true);
//        out_.println(SerializeXmlObject.toXmlString(_serializable));
//    }
    /**server and client*/
    public static void sendObject(Socket _socket, Object _serializable) {
        Net.sendText(_socket,DocumentWriterCardsMultiUtil.setObject(_serializable));
    }
    //bk: synchronized
    /**server*/
    static void initAllPresent() {
        _activePlayers_ = new NumberMap<Byte,Boolean>();
        for (byte r: Net.getPlacesPlayers().values()) {
//            _activePlayers_.synchronizedPut(r, true);
            _activePlayers_.put(r, true);
        }
    }
    //bk: synchronized
    /**server*/
    static void initAllReceived() {
        if (_received_ == null) {
            _received_ = new NumberMap<Byte,Boolean>();
        }
        for (byte r: Net.getPlacesPlayers().values()) {
//            if (_activePlayers_.synchronizedGet(r)) {
////                _received_.synchronizedPut(r, false);
//                _received_.put(r, false);
//            } else {
////                _received_.synchronizedPut(r, true);
//                _received_.put(r, true);
//            }
            if (_activePlayers_.getVal(r)) {
                _received_.put(r, false);
            } else {
                _received_.put(r, true);
            }
        }
    }

    //bk: synchronized
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
    //bk: synchronized
    /**server*/
    static void setReceivedForPlayer(byte _p) {
//        _received_.synchronizedPut(_p,true);
        _received_.put(_p,true);
    }

    //bk: synchronized
    /**server*/
    static boolean allReceivedAmong(Numbers<Byte> _players) {
        boolean allReceived_ = true;
        for (byte p: Net._received_.getKeys()) {
            if (!_players.containsObj(p)) {
                continue;
            }
            if (Net._received_.getVal(p)) {
                continue;
            }
            allReceived_ = false;
            break;
        }
        return allReceived_;
    }

    static boolean allReceived() {
        boolean allReceived_ = true;
        for (boolean r: Net._received_.values()) {
            if (r) {
                continue;
            }
            allReceived_ = false;
            break;
        }
        return allReceived_;
    }
    /**server
    @return true &hArr; the connected players are belonging to a seam team*/
    static boolean isSameTeam() {
        Numbers<Byte> players_ = new Numbers<Byte>(activePlayers());
        return Net.getGames().isSameTeam(players_);
    }
    /**server
    bk: synchronized, called from mouse events or server loop
    @return the connected players*/
    static Numbers<Byte> activePlayers() {
        if (_activePlayers_ == null) {
            Numbers<Byte> activePlayers_ = new Numbers<Byte>();
            for (byte i: getPlacesPlayers().values()) {
                activePlayers_.add(i);
            }
            return activePlayers_;
        }
        Numbers<Byte> activePlayers_ = new Numbers<Byte>();
        for (byte i: getPlacesPlayers().values()) {
//            if (!_activePlayers_.synchronizedGet(i)) {
//            }
            if (!_activePlayers_.getVal(i)) {
                continue;
            }
            activePlayers_.add(i);
        }
        return activePlayers_;
    }

    /**server
    @param _place the place of a bot or a player
    @return true &hArr; if the <i>_place</i> match with a currently connected player*/
    static boolean isHumanPlayer(byte _place) {
//        return getPlacesPlayers().has(_place) && _activePlayers_.synchronizedGet(_place);
//        return TreeMap.<Integer,Byte>hasNumber(getPlacesPlayers(), _place) && _activePlayers_.getVal(_place);
        return !getPlacesPlayersByValue(_place).isEmpty() && _activePlayers_.getVal(_place);
    }
    /**server
    @param _place the place of a player around the table
    @return the associated socket of the place or null if it is an invalid place for a player*/
    static Socket getSocketByPlace(byte _place) {
        for (int i: getPlacesPlayers().getKeys()) {
            if (getPlacesPlayers().getVal(i) == _place) {
                return getSockets().getVal(i);
            }
        }
        return null;
    }
    /**server*/
    static String getLanguageByPlace(byte _place) {
        for (int i: getPlacesPlayers().getKeys()) {
            if (getPlacesPlayers().getVal(i) == _place) {
                return getPlayersLocales().getVal(i);
            }
        }
        return null;
    }

    //bk: synchronized
    /**server*/
    static boolean delegateServer(Quit _bye) {
        for (byte p: Net.activePlayers()) {
            if (p == _bye.getPlace()) {
                continue;
            }
            DelegateServer d_ = new DelegateServer();
            d_.setGames(Net.getGames());
            d_.setNicknames(new NumberMap<Integer,String>());
            Net.sendObject(Net.getSocketByPlace(p),d_);
            return true;
        }
        Bye forcedBye_ = new Bye();
        forcedBye_.setForced(false);
        forcedBye_.setServer(false);
        forcedBye_.setClosing(_bye.isClosing());
        //for (int i: Net.getPlacesPlayers().getKeys(_bye.getPlace()))
        for (int i: Net.getPlacesPlayersByValue(_bye.getPlace())) {
//            Socket socket_ = Net.getSockets().getVal(i);
//            Net.getSockets().removeKey(i);
//            Net.getConnectionsServer().removeKey(i);
//            Net.getReadyPlayers().removeKey(i);
//            Net.getPlacesPlayers().removeKey(i);
//            Net.sendObject(socket_,forcedBye_);
            removePlayer(i, forcedBye_);
            break;
        }
        Net.getNicknames().clear();
        Net.getGames().finirParties();
        Net.getPlacesPlayers().clear();
        return false;
    }

    static void removePlayer(int _player, Bye _bye) {
        Socket socket_ = Net.getSockets().getVal(_player);
        Net.getSockets().removeKey(_player);
        Net.getConnectionsServer().removeKey(_player);
        Net.getReadyPlayers().removeKey(_player);
        Net.getPlacesPlayers().removeKey(_player);
        Net.sendObject(socket_,_bye);
    }

    /**server*/
    public static NumberMap<Integer,SendReceiveServer> getConnectionsServer() {
        return _connectionsServer_;
    }

    /**server*/
    public static Games getGames() {
        return _games_;
    }

    /**server (delegating)*/
    public static void setGames(Games _games) {
        _games_ = _games;
    }

    /**server*/
    public static int getNbPlayers() {
        return _nbPlayers_;
    }

    /**server*/
    public static void setNbPlayers(int _nbPlayers) {
        _nbPlayers_ = _nbPlayers;
    }

    /**server*/
    public static NumberMap<Integer,String> getNicknames() {
        return _nicknames_;
    }

    /**server*/
    public static NumberMap<Integer,Socket> getSockets() {
        return _sockets_;
    }

    /**server*/
    public static NumberMap<Integer,Boolean> getReadyPlayers() {
        return _readyPlayers_;
    }

    /**server*/
    public static NatTreeMap<Integer, Byte> getPlacesPlayers() {
        return _placesPlayers_;
    }
    /**server*/
    public static Numbers<Integer> getPlacesPlayersByValue(byte _value) {
        Numbers<Integer> l_;
        l_ = new Numbers<Integer>();
        for (EntryCust<Integer, Byte> e: _placesPlayers_.entryList()) {
            if (e.getValue().byteValue() != _value) {
                continue;
            }
            l_.add(e.getKey());
        }
        return l_;
    }

    /**server and client (delegating)*/
    public static boolean isProgressingGame() {
        return _progressingGame_;
    }

    /**server*/
    public static void setProgressingGame(boolean _progressingGame) {
        _progressingGame_ = _progressingGame;
    }

    /**server*/
    public static CustList<Numbers<Long>> getScores() {
        return _scores_;
    }

    /**server*/
    public static NumberMap<Integer,String> getPlayersLocales() {
        return _playersLocales_;
    }
}
