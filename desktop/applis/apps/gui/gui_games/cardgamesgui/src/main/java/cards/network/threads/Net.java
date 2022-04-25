package cards.network.threads;

import cards.belote.ResultsBelote;
import cards.belote.TricksHandsBelote;
import cards.facade.Games;
import cards.network.belote.displaying.DealtHandBelote;
import cards.network.belote.displaying.errors.ErrorBiddingBelote;
import cards.network.belote.displaying.errors.ErrorPlayingBelote;
import cards.network.belote.unlock.AllowBiddingBelote;
import cards.network.belote.unlock.AllowPlayingBelote;
import cards.network.common.ByeCards;
import cards.network.common.DelegateServer;
import cards.network.common.PlayerActionGame;
import cards.network.common.Quit;
import cards.network.common.before.*;
import cards.network.common.select.TeamsPlayers;
import cards.network.president.displaying.DealtHandPresident;
import cards.network.president.displaying.ReceivedGivenCards;
import cards.network.president.displaying.errors.ErrorPlayingPresident;
import cards.network.president.unlock.AllowDiscarding;
import cards.network.president.unlock.AllowPlayingPresident;
import cards.network.sml.DocumentReaderCardsMultiUtil;
import cards.network.sml.DocumentWriterCardsMultiUtil;
import cards.network.tarot.Dog;
import cards.network.tarot.actions.DiscardedTrumps;
import cards.network.tarot.displaying.DealtHandTarot;
import cards.network.tarot.displaying.errors.ErrorBidding;
import cards.network.tarot.displaying.errors.ErrorDiscarding;
import cards.network.tarot.displaying.errors.ErrorHandful;
import cards.network.tarot.displaying.errors.ErrorPlaying;
import cards.network.tarot.unlock.AllowBiddingTarot;
import cards.network.tarot.unlock.AllowPlayingTarot;
import cards.network.tarot.unlock.CallableCards;
import cards.president.ResultsPresident;
import cards.president.TricksHandsPresident;
import cards.tarot.ResultsTarot;
import cards.tarot.TricksHandsTarot;
import code.gui.initialize.AbstractSocket;
import code.network.NetGroupFrame;
import code.util.CustList;
import code.util.EntryCust;
import code.util.*;
import code.util.core.BoolVal;

public final class Net {

    private static final String CARDS = "CARDS";

    /** A used port for connections*/
    private static final int PORT = 667;

    private int nbPlayers;

    private boolean progressingGame;

    /**A useful facade for games*/

    private Games games = new Games();


    private IntMap<AbstractSocket> sockets =new IntMap<AbstractSocket>();
    private IntMap<BoolVal> servers =new IntMap<BoolVal>();

    private IntTreeMap< Byte> placesPlayers = new IntTreeMap< Byte>();

    private IntMap<Boolean> readyPlayers = new IntMap<Boolean>();


    private IntMap<String> nicknames =new IntMap<String>();

    private IntMap<SendReceiveServerCards> connectionsServer =new IntMap<SendReceiveServerCards>();

    private IntMap<String> playersLocales = new IntMap<String>();

    private CustList<Longs> scores = new CustList<Longs>();

    private ByteMap<Boolean> activePlayers;
    private ByteMap<Boolean> received;

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
    static void sendText(AbstractSocket _socket, String _text) {
        NetGroupFrame.trySendString(_text,_socket);
    }
    /**server
    only clicks can call directly this method
    @return true &hArr; the players are ready to begin a deal
     * @param _instance*/
    public static boolean allReady(Net _instance) {
        boolean allReady_ = true;
        for (boolean r: Net.getReadyPlayers(_instance).values()) {
            if (r) {
                continue;
            }
            allReady_ = false;
            break;
        }
        return allReady_;
    }
    /**server
    @return true &hArr; the players are correctly placed around the "table"
     * @param _instance*/
    public static boolean distinctPlaces(Net _instance) {
        boolean distinct_ = true;
        Bytes places_ = new Bytes();
        for (byte r: activePlayers(_instance)) {
            if (places_.containsObj(r)) {
                distinct_ = false;
                break;
            }
            places_.add(r);
        }
        return distinct_;
    }

    /**server and client*/
    public static void sendObject(AbstractSocket _socket, PlayerActionBeforeGameCards _serializable) {
        Net.sendText(_socket,DocumentWriterCardsMultiUtil.playerActionBeforeGameCards(_serializable));
    }
    public static void sendObject(AbstractSocket _socket, DelegateServer _serializable) {
        Net.sendText(_socket,DocumentWriterCardsMultiUtil.delegateServer(_serializable));
    }
    public static void sendObject(AbstractSocket _socket, ByeCards _serializable) {
        Net.sendText(_socket,DocumentWriterCardsMultiUtil.bye(_serializable));
    }
    public static void sendObject(AbstractSocket _socket, ResultsBelote _serializable) {
        Net.sendText(_socket,DocumentWriterCardsMultiUtil.resultsBelote(_serializable));
    }
    public static void sendObject(AbstractSocket _socket, ResultsPresident _serializable) {
        Net.sendText(_socket,DocumentWriterCardsMultiUtil.resultsPresident(_serializable));
    }
    public static void sendObject(AbstractSocket _socket, ResultsTarot _serializable) {
        Net.sendText(_socket,DocumentWriterCardsMultiUtil.resultsTarot(_serializable));
    }
    public static void sendObject(AbstractSocket _socket, PlayersNamePresent _serializable) {
        Net.sendText(_socket,DocumentWriterCardsMultiUtil.playersNamePresent(_serializable));
    }
    public static void sendObject(AbstractSocket _socket, AllowPlayingBelote _serializable) {
        Net.sendText(_socket,DocumentWriterCardsMultiUtil.allowPlayingBelote(_serializable));
    }
    public static void sendObject(AbstractSocket _socket, AllowPlayingPresident _serializable) {
        Net.sendText(_socket,DocumentWriterCardsMultiUtil.allowPlayingPresident(_serializable));
    }
    public static void sendObject(AbstractSocket _socket, AllowPlayingTarot _serializable) {
        Net.sendText(_socket,DocumentWriterCardsMultiUtil.allowPlayingTarot(_serializable));
    }
    public static void sendObject(AbstractSocket _socket, PlayerActionGame _serializable) {
        Net.sendText(_socket,DocumentWriterCardsMultiUtil.playerActionGame(_serializable));
    }
    public static void sendObject(AbstractSocket _socket, DealtHandBelote _serializable) {
        Net.sendText(_socket,DocumentWriterCardsMultiUtil.dealtHandBelote(_serializable));
    }
    public static void sendObject(AbstractSocket _socket, DealtHandPresident _serializable) {
        Net.sendText(_socket,DocumentWriterCardsMultiUtil.dealtHandPresident(_serializable));
    }
    public static void sendObject(AbstractSocket _socket, DealtHandTarot _serializable) {
        Net.sendText(_socket,DocumentWriterCardsMultiUtil.dealtHandTarot(_serializable));
    }
    public static void sendObject(AbstractSocket _socket, Dog _serializable) {
        Net.sendText(_socket,DocumentWriterCardsMultiUtil.dog(_serializable));
    }
    public static void sendObjectDisplaySlamButton(AbstractSocket _socket) {
        Net.sendText(_socket,DocumentWriterCardsMultiUtil.displaySlamButton());
    }
    public static void sendObject(AbstractSocket _socket, CallableCards _serializable) {
        Net.sendText(_socket,DocumentWriterCardsMultiUtil.callableCards(_serializable));
    }
    public static void sendObject(AbstractSocket _socket, AllowBiddingTarot _serializable) {
        Net.sendText(_socket,DocumentWriterCardsMultiUtil.allowBiddingTarot(_serializable));
    }
    public static void sendObject(AbstractSocket _socket, AllowBiddingBelote _serializable) {
        Net.sendText(_socket,DocumentWriterCardsMultiUtil.allowBiddingBelote(_serializable));
    }
    public static void sendObject(AbstractSocket _socket, ErrorBidding _serializable) {
        Net.sendText(_socket,DocumentWriterCardsMultiUtil.errorBidding(_serializable));
    }
    public static void sendObject(AbstractSocket _socket, ErrorBiddingBelote _serializable) {
        Net.sendText(_socket,DocumentWriterCardsMultiUtil.errorBiddingBelote(_serializable));
    }
    public static void sendObject(AbstractSocket _socket, ErrorPlayingBelote _serializable) {
        Net.sendText(_socket,DocumentWriterCardsMultiUtil.errorPlayingBelote(_serializable));
    }
    public static void sendObject(AbstractSocket _socket, ErrorPlayingPresident _serializable) {
        Net.sendText(_socket,DocumentWriterCardsMultiUtil.errorPlayingPresident(_serializable));
    }
    public static void sendObject(AbstractSocket _socket, ErrorPlaying _serializable) {
        Net.sendText(_socket,DocumentWriterCardsMultiUtil.errorPlaying(_serializable));
    }
    public static void sendObject(AbstractSocket _socket, ErrorHandful _serializable) {
        Net.sendText(_socket,DocumentWriterCardsMultiUtil.errorHandful(_serializable));
    }
    public static void sendObject(AbstractSocket _socket, ErrorDiscarding _serializable) {
        Net.sendText(_socket,DocumentWriterCardsMultiUtil.errorDiscarding(_serializable));
    }
    public static void sendObjectPause(AbstractSocket _socket) {
        Net.sendText(_socket,DocumentWriterCardsMultiUtil.pause());
    }
    public static void sendObject(AbstractSocket _socket, TricksHandsBelote _serializable) {
        Net.sendText(_socket,DocumentWriterCardsMultiUtil.tricksHandsBelote(_serializable));
    }
    public static void sendObject(AbstractSocket _socket, TricksHandsPresident _serializable) {
        Net.sendText(_socket,DocumentWriterCardsMultiUtil.tricksHandsPresident(_serializable));
    }
    public static void sendObject(AbstractSocket _socket, TricksHandsTarot _serializable) {
        Net.sendText(_socket,DocumentWriterCardsMultiUtil.tricksHandsTarot(_serializable));
    }
    public static void sendObject(AbstractSocket _socket, TeamsPlayers _serializable) {
        Net.sendText(_socket,DocumentWriterCardsMultiUtil.teamsPlayers(_serializable));
    }
    public static void sendObject(AbstractSocket _socket, DiscardedTrumps _serializable) {
        Net.sendText(_socket,DocumentWriterCardsMultiUtil.discardedTrumps(_serializable));
    }
    public static void sendObject(AbstractSocket _socket, AllowDiscarding _serializable) {
        Net.sendText(_socket,DocumentWriterCardsMultiUtil.allowDiscarding(_serializable));
    }
    public static void sendObject(AbstractSocket _socket, ReceivedGivenCards _serializable) {
        Net.sendText(_socket,DocumentWriterCardsMultiUtil.receivedGivenCards(_serializable));
    }
    /**server
     * @param _instance*/
    static void initAllPresent(Net _instance) {
        _instance.activePlayers = new ByteMap<Boolean>();
        for (byte r: Net.getPlacesPlayers(_instance).values()) {
            _instance.activePlayers.put(r, true);
        }
    }
    static void initAllReady(Net _instance) {
        for (EntryCust<Integer,Boolean> e:_instance.readyPlayers.entryList()) {
            e.setValue(false);
        }
    }
    /**server
     * @param _instance*/
    static void initAllReceived(Net _instance) {
        if (_instance.received == null) {
            _instance.received = new ByteMap<Boolean>();
        }
        for (byte r: Net.getPlacesPlayers(_instance).values()) {
            if (_instance.activePlayers.getVal(r)) {
                _instance.received.put(r, false);
            } else {
                setReceivedForPlayer(r, _instance);
            }
        }
    }

    //bk: synchronized
    /**server*/
    static void quit(byte _p, Net _instance) {
        if (_instance.activePlayers == null) {
            return;
        }
        _instance.activePlayers.put(_p, false);
        setReceivedForPlayer(_p, _instance);
    }
    //bk: synchronized
    /**server*/
    static void setReceivedForPlayer(byte _p, Net _instance) {
        _instance.received.put(_p,true);
    }

    //bk: synchronized
    /**server*/
    static boolean allReceivedAmong(Bytes _players, Net _instance) {
        boolean allReceived_ = true;
        for (byte p: _instance.received.getKeys()) {
            if (!_players.containsObj(p)) {
                continue;
            }
            if (_instance.received.getVal(p)) {
                continue;
            }
            allReceived_ = false;
            break;
        }
        return allReceived_;
    }

    static boolean allReceived(Net _instance) {
        boolean allReceived_ = true;
        for (boolean r: _instance.received.values()) {
            if (r) {
                continue;
            }
            allReceived_ = false;
            break;
        }
        return allReceived_;
    }
    /**server
    @return true &hArr; the connected players are belonging to a seam team
     * @param _instance*/
    static boolean isSameTeam(Net _instance) {
        Bytes players_ = new Bytes(activePlayers(_instance));
        return Net.getGames(_instance).isSameTeam(players_);
    }
    /**server
    bk: synchronized, called from mouse events or server loop
    @return the connected players
     * @param _instance*/
    static Bytes activePlayers(Net _instance) {
        if (_instance.activePlayers == null) {
            Bytes activePlayers_ = new Bytes();
            for (byte i: getPlacesPlayers(_instance).values()) {
                activePlayers_.add(i);
            }
            return activePlayers_;
        }
        Bytes activePlayers_ = new Bytes();
        for (byte i: getPlacesPlayers(_instance).values()) {
            if (!_instance.activePlayers.getVal(i)) {
                continue;
            }
            activePlayers_.add(i);
        }
        return activePlayers_;
    }

    /**server
    @param _place the place of a bot or a player
     * @param _instance
    @return true &hArr; if the <i>_place</i> match with a currently connected player*/
    static boolean isHumanPlayer(byte _place, Net _instance) {
        return !getPlacesPlayersByValue(_place, _instance).isEmpty() && _instance.activePlayers.getVal(_place);
    }
    /**server
    @param _place the place of a player around the table
     * @param _instance
    @return the associated socket of the place or null if it is an invalid place for a player*/
    static AbstractSocket getSocketByPlace(byte _place, Net _instance) {
        for (int i: getPlacesPlayers(_instance).getKeys()) {
            if (getPlacesPlayers(_instance).getVal(i) == _place) {
                return getSockets(_instance).getVal(i);
            }
        }
        return null;
    }
    /**server*/
    static String getLanguageByPlace(byte _place, Net _instance) {
        for (int i: getPlacesPlayers(_instance).getKeys()) {
            if (getPlacesPlayers(_instance).getVal(i) == _place) {
                return getPlayersLocales(_instance).getVal(i);
            }
        }
        return null;
    }

    /**server*/
    static boolean delegateServer(Quit _bye, Net _instance) {
        for (byte p: Net.activePlayers(_instance)) {
            if (p == _bye.getPlace()) {
                continue;
            }
            DelegateServer d_ = new DelegateServer();
            d_.setGames(Net.getGames(_instance));
            d_.setNicknames(new IntMap<String>());
            Net.sendObject(Net.getSocketByPlace(p, _instance),d_);
            return true;
        }
        ByeCards forcedBye_ = new ByeCards();
        forcedBye_.setForced(false);
        forcedBye_.setServer(true);
        forcedBye_.setClosing(_bye.isClosing());
        Ints players_ = Net.getPlacesPlayersByValue(_bye.getPlace(), _instance);
        if (!players_.isEmpty()) {
            removePlayer(players_.first(), forcedBye_, _instance);
        }
        Net.getNicknames(_instance).clear();
        Net.getGames(_instance).finirParties();
        Net.getPlacesPlayers(_instance).clear();
        return false;
    }

    static void removePlayer(int _player, ByeCards _bye, Net _instance) {
        AbstractSocket socket_ = Net.getSockets(_instance).getVal(_player);
        Net.getSockets(_instance).removeKey(_player);
        Net.getConnectionsServer(_instance).removeKey(_player);
        Net.getReadyPlayers(_instance).removeKey(_player);
        Net.getPlacesPlayers(_instance).removeKey(_player);
        Net.sendObject(socket_,_bye);
    }

    /**server
     * @param _instance*/
    public static IntMap<SendReceiveServerCards> getConnectionsServer(Net _instance) {
        return _instance.connectionsServer;
    }

    /**server
     * @param _instance*/
    public static Games getGames(Net _instance) {
        return _instance.games;
    }

    /**server (delegating)*/
    public static void setGames(Games _games, Net _instance) {
        _instance.games = _games;
    }

    /**server
     * @param _instance*/
    public static int getNbPlayers(Net _instance) {
        return _instance.nbPlayers;
    }

    /**server*/
    public static void setNbPlayers(int _nbPlayers, Net _instance) {
        _instance.nbPlayers = _nbPlayers;
    }

    /**server
     * @param _instance*/
    public static IntMap<String> getNicknames(Net _instance) {
        return _instance.nicknames;
    }

    /**server
     * @param _instance*/
    public static IntMap<AbstractSocket> getSockets(Net _instance) {
        return _instance.sockets;
    }
    public static IntMap<BoolVal> getServers(Net _instance) {
        return _instance.servers;
    }
    public static void sendOkToQuit(Net _instance) {
        for (EntryCust<Integer, BoolVal> e: getServers(_instance).entryList()) {
            if (e.getValue() == BoolVal.TRUE) {
                AbstractSocket val_ = getSockets(_instance).getVal(e.getKey());
                sendText(val_,"<"+ DocumentReaderCardsMultiUtil.TYPE_ENABLED_QUIT+"/>");
            }
        }
    }

    /**server
     * @param _instance*/
    public static IntMap<Boolean> getReadyPlayers(Net _instance) {
        return _instance.readyPlayers;
    }

    /**server
     * @param _instance*/
    public static IntTreeMap< Byte> getPlacesPlayers(Net _instance) {
        return _instance.placesPlayers;
    }
    /**server*/
    public static Ints getPlacesPlayersByValue(byte _value, Net _instance) {
        Ints l_;
        l_ = new Ints();
        for (EntryCust<Integer, Byte> e: getPlacesPlayers(_instance).entryList()) {
            if (e.getValue() != _value) {
                continue;
            }
            l_.add(e.getKey());
        }
        return l_;
    }

    /**server and client (delegating)
     * @param _instance*/
    public static boolean isProgressingGame(Net _instance) {
        return _instance.progressingGame;
    }

    /**server*/
    public static void setProgressingGame(boolean _progressingGame, Net _instance) {
        _instance.progressingGame = _progressingGame;
    }

    /**server
     * @param _instance*/
    public static CustList<Longs> getScores(Net _instance) {
        return _instance.scores;
    }

    /**server
     * @param _instance*/
    public static IntMap<String> getPlayersLocales(Net _instance) {
        return _instance.playersLocales;
    }

}
