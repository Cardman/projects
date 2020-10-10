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
import code.network.NetGroupFrame;
import code.util.CustList;
import code.util.EntryCust;
import code.util.*;

public final class Net {

    private static final String CARDS = "CARDS";

    /** A used port for connections*/
    private static final int PORT = 667;

    private static final Net INSTANCE = new Net();

    private int _nbPlayers_;

    private boolean _progressingGame_;

    /**A useful facade for games*/

    private Games _games_ = new Games();


    private IntMap<Socket> _sockets_=new IntMap<Socket>();

    private IntTreeMap< Byte> _placesPlayers_ = new IntTreeMap< Byte>();

    private IntMap<Boolean> _readyPlayers_ = new IntMap<Boolean>();


    private IntMap<String> _nicknames_=new IntMap<String>();

    private IntMap<SendReceiveServer> _connectionsServer_=new IntMap<SendReceiveServer>();

    private IntMap<String> _playersLocales_ = new IntMap<String>();

    private CustList<Longs> _scores_ = new CustList<Longs>();

    private ByteMap<Boolean> _activePlayers_;
    private ByteMap<Boolean> _received_;

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
        if (_socket == null) {
            return;
        }
        NetGroupFrame.trySendString(_text,_socket);
    }
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
        Bytes places_ = new Bytes();
        for (byte r: activePlayers()) {
            if (places_.containsObj(r)) {
                distinct_ = false;
                break;
            }
            places_.add(r);
        }
        return distinct_;
    }

    /**server and client*/
    public static void sendObject(Socket _socket, Object _serializable) {
        Net.sendText(_socket,DocumentWriterCardsMultiUtil.setObject(_serializable));
    }
    /**server*/
    static void initAllPresent() {
        INSTANCE._activePlayers_ = new ByteMap<Boolean>();
        for (byte r: Net.getPlacesPlayers().values()) {
            INSTANCE._activePlayers_.put(r, true);
        }
    }
    /**server*/
    static void initAllReceived() {
        if (INSTANCE._received_ == null) {
            INSTANCE._received_ = new ByteMap<Boolean>();
        }
        for (byte r: Net.getPlacesPlayers().values()) {
            if (INSTANCE._activePlayers_.getVal(r)) {
                INSTANCE._received_.put(r, false);
            } else {
                INSTANCE._received_.put(r, true);
            }
        }
    }

    //bk: synchronized
    /**server*/
    static void quit(byte _p) {
        if (INSTANCE._activePlayers_ == null) {
            return;
        }
        INSTANCE._activePlayers_.put(_p, false);
        INSTANCE._received_.put(_p,true);
    }
    //bk: synchronized
    /**server*/
    static void setReceivedForPlayer(byte _p) {
        INSTANCE._received_.put(_p,true);
    }

    //bk: synchronized
    /**server*/
    static boolean allReceivedAmong(Bytes _players) {
        boolean allReceived_ = true;
        for (byte p: INSTANCE._received_.getKeys()) {
            if (!_players.containsObj(p)) {
                continue;
            }
            if (INSTANCE._received_.getVal(p)) {
                continue;
            }
            allReceived_ = false;
            break;
        }
        return allReceived_;
    }

    static boolean allReceived() {
        boolean allReceived_ = true;
        for (boolean r: INSTANCE._received_.values()) {
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
        Bytes players_ = new Bytes(activePlayers());
        return Net.getGames().isSameTeam(players_);
    }
    /**server
    bk: synchronized, called from mouse events or server loop
    @return the connected players*/
    static Bytes activePlayers() {
        if (INSTANCE._activePlayers_ == null) {
            Bytes activePlayers_ = new Bytes();
            for (byte i: getPlacesPlayers().values()) {
                activePlayers_.add(i);
            }
            return activePlayers_;
        }
        Bytes activePlayers_ = new Bytes();
        for (byte i: getPlacesPlayers().values()) {
            if (!INSTANCE._activePlayers_.getVal(i)) {
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
        return !getPlacesPlayersByValue(_place).isEmpty() && INSTANCE._activePlayers_.getVal(_place);
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

    /**server*/
    static boolean delegateServer(Quit _bye) {
        for (byte p: Net.activePlayers()) {
            if (p == _bye.getPlace()) {
                continue;
            }
            DelegateServer d_ = new DelegateServer();
            d_.setGames(Net.getGames());
            d_.setNicknames(new IntMap<String>());
            Net.sendObject(Net.getSocketByPlace(p),d_);
            return true;
        }
        Bye forcedBye_ = new Bye();
        forcedBye_.setForced(false);
        forcedBye_.setServer(false);
        forcedBye_.setClosing(_bye.isClosing());
        Ints players_ = Net.getPlacesPlayersByValue(_bye.getPlace());
        if (!players_.isEmpty()) {
            removePlayer(players_.first(), forcedBye_);
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
    public static IntMap<SendReceiveServer> getConnectionsServer() {
        return INSTANCE._connectionsServer_;
    }

    /**server*/
    public static Games getGames() {
        return INSTANCE._games_;
    }

    /**server (delegating)*/
    public static void setGames(Games _games) {
        INSTANCE._games_ = _games;
    }

    /**server*/
    public static int getNbPlayers() {
        return INSTANCE._nbPlayers_;
    }

    /**server*/
    public static void setNbPlayers(int _nbPlayers) {
        INSTANCE._nbPlayers_ = _nbPlayers;
    }

    /**server*/
    public static IntMap<String> getNicknames() {
        return INSTANCE._nicknames_;
    }

    /**server*/
    public static IntMap<Socket> getSockets() {
        return INSTANCE._sockets_;
    }

    /**server*/
    public static IntMap<Boolean> getReadyPlayers() {
        return INSTANCE._readyPlayers_;
    }

    /**server*/
    public static IntTreeMap< Byte> getPlacesPlayers() {
        return INSTANCE._placesPlayers_;
    }
    /**server*/
    public static Ints getPlacesPlayersByValue(byte _value) {
        Ints l_;
        l_ = new Ints();
        for (EntryCust<Integer, Byte> e: INSTANCE._placesPlayers_.entryList()) {
            if (e.getValue() != _value) {
                continue;
            }
            l_.add(e.getKey());
        }
        return l_;
    }

    /**server and client (delegating)*/
    public static boolean isProgressingGame() {
        return INSTANCE._progressingGame_;
    }

    /**server*/
    public static void setProgressingGame(boolean _progressingGame) {
        INSTANCE._progressingGame_ = _progressingGame;
    }

    /**server*/
    public static CustList<Longs> getScores() {
        return INSTANCE._scores_;
    }

    /**server*/
    public static IntMap<String> getPlayersLocales() {
        return INSTANCE._playersLocales_;
    }
}
