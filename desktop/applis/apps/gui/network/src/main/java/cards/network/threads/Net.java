package cards.network.threads;

import cards.belote.ResultsBelote;
import cards.belote.TricksHandsBelote;
import cards.facade.Games;
import cards.network.belote.displaying.DealtHandBelote;
import cards.network.belote.displaying.errors.ErrorBiddingBelote;
import cards.network.belote.displaying.errors.ErrorPlayingBelote;
import cards.network.belote.unlock.AllowBiddingBelote;
import cards.network.belote.unlock.AllowPlayingBelote;
import cards.network.common.DelegateServer;
import cards.network.common.PlayerActionGame;
import cards.network.common.before.*;
import cards.gui.TeamsPlayers;
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
import code.network.Exiting;
import code.network.NetCommon;
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

    private IntMap<String> playersLocales = new IntMap<String>();

    private CustList<Longs> scores = new CustList<Longs>();

    private ByteMap<BoolVal> activePlayers;
    private ByteMap<BoolVal> received;

    public static int getPort() {
        return PORT;
    }

    public static String getCards() {
        return CARDS;
    }

    /**server
    @return true &hArr; the players are correctly placed around the "table"
     * @param _instance
     * @param _common */
    public static boolean distinctPlaces(Net _instance, NetCommon _common) {
        boolean distinct_ = true;
        Bytes places_ = new Bytes();
        for (byte r: activePlayers(_instance, _common)) {
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
        NetGroupFrame.trySendString(DocumentWriterCardsMultiUtil.playerActionBeforeGameCards(_serializable), _socket);
    }
    public static void sendObject(AbstractSocket _socket, DelegateServer _serializable) {
        NetGroupFrame.trySendString(DocumentWriterCardsMultiUtil.delegateServer(_serializable), _socket);
    }
    public static void sendObject(AbstractSocket _socket, Exiting _serializable) {
        NetGroupFrame.trySendString(DocumentWriterCardsMultiUtil.bye(_serializable), _socket);
    }
    public static void sendObject(AbstractSocket _socket, ResultsBelote _serializable) {
        NetGroupFrame.trySendString(DocumentWriterCardsMultiUtil.resultsBelote(_serializable), _socket);
    }
    public static void sendObject(AbstractSocket _socket, ResultsPresident _serializable) {
        NetGroupFrame.trySendString(DocumentWriterCardsMultiUtil.resultsPresident(_serializable), _socket);
    }
    public static void sendObject(AbstractSocket _socket, ResultsTarot _serializable) {
        NetGroupFrame.trySendString(DocumentWriterCardsMultiUtil.resultsTarot(_serializable), _socket);
    }
    public static void sendObject(AbstractSocket _socket, PlayersNamePresent _serializable) {
        NetGroupFrame.trySendString(DocumentWriterCardsMultiUtil.playersNamePresent(_serializable), _socket);
    }
    public static void sendObject(AbstractSocket _socket, AllowPlayingBelote _serializable) {
        NetGroupFrame.trySendString(DocumentWriterCardsMultiUtil.allowPlayingBelote(_serializable), _socket);
    }
    public static void sendObject(AbstractSocket _socket, AllowPlayingPresident _serializable) {
        NetGroupFrame.trySendString(DocumentWriterCardsMultiUtil.allowPlayingPresident(_serializable), _socket);
    }
    public static void sendObject(AbstractSocket _socket, AllowPlayingTarot _serializable) {
        NetGroupFrame.trySendString(DocumentWriterCardsMultiUtil.allowPlayingTarot(_serializable), _socket);
    }
    public static void sendObject(AbstractSocket _socket, PlayerActionGame _serializable) {
        NetGroupFrame.trySendString(DocumentWriterCardsMultiUtil.playerActionGame(_serializable), _socket);
    }
    public static void sendObject(AbstractSocket _socket, DealtHandBelote _serializable) {
        NetGroupFrame.trySendString(DocumentWriterCardsMultiUtil.dealtHandBelote(_serializable), _socket);
    }
    public static void sendObject(AbstractSocket _socket, DealtHandPresident _serializable) {
        NetGroupFrame.trySendString(DocumentWriterCardsMultiUtil.dealtHandPresident(_serializable), _socket);
    }
    public static void sendObject(AbstractSocket _socket, DealtHandTarot _serializable) {
        NetGroupFrame.trySendString(DocumentWriterCardsMultiUtil.dealtHandTarot(_serializable), _socket);
    }
    public static void sendObject(AbstractSocket _socket, Dog _serializable) {
        NetGroupFrame.trySendString(DocumentWriterCardsMultiUtil.dog(_serializable), _socket);
    }
    public static void sendObjectDisplaySlamButton(AbstractSocket _socket) {
        NetGroupFrame.trySendString(DocumentWriterCardsMultiUtil.displaySlamButton(), _socket);
    }
    public static void sendObject(AbstractSocket _socket, CallableCards _serializable) {
        NetGroupFrame.trySendString(DocumentWriterCardsMultiUtil.callableCards(_serializable), _socket);
    }
    public static void sendObject(AbstractSocket _socket, AllowBiddingTarot _serializable) {
        NetGroupFrame.trySendString(DocumentWriterCardsMultiUtil.allowBiddingTarot(_serializable), _socket);
    }
    public static void sendObject(AbstractSocket _socket, AllowBiddingBelote _serializable) {
        NetGroupFrame.trySendString(DocumentWriterCardsMultiUtil.allowBiddingBelote(_serializable), _socket);
    }
    public static void sendObject(AbstractSocket _socket, ErrorBidding _serializable) {
        NetGroupFrame.trySendString(DocumentWriterCardsMultiUtil.errorBidding(_serializable), _socket);
    }
    public static void sendObject(AbstractSocket _socket, ErrorBiddingBelote _serializable) {
        NetGroupFrame.trySendString(DocumentWriterCardsMultiUtil.errorBiddingBelote(_serializable), _socket);
    }
    public static void sendObject(AbstractSocket _socket, ErrorPlayingBelote _serializable) {
        NetGroupFrame.trySendString(DocumentWriterCardsMultiUtil.errorPlayingBelote(_serializable), _socket);
    }
    public static void sendObject(AbstractSocket _socket, ErrorPlayingPresident _serializable) {
        NetGroupFrame.trySendString(DocumentWriterCardsMultiUtil.errorPlayingPresident(_serializable), _socket);
    }
    public static void sendObject(AbstractSocket _socket, ErrorPlaying _serializable) {
        NetGroupFrame.trySendString(DocumentWriterCardsMultiUtil.errorPlaying(_serializable), _socket);
    }
    public static void sendObject(AbstractSocket _socket, ErrorHandful _serializable) {
        NetGroupFrame.trySendString(DocumentWriterCardsMultiUtil.errorHandful(_serializable), _socket);
    }
    public static void sendObject(AbstractSocket _socket, ErrorDiscarding _serializable) {
        NetGroupFrame.trySendString(DocumentWriterCardsMultiUtil.errorDiscarding(_serializable), _socket);
    }
    public static void sendObjectPause(AbstractSocket _socket) {
        NetGroupFrame.trySendString(DocumentWriterCardsMultiUtil.pause(), _socket);
    }
    public static void sendObject(AbstractSocket _socket, TricksHandsBelote _serializable) {
        NetGroupFrame.trySendString(DocumentWriterCardsMultiUtil.tricksHandsBelote(_serializable), _socket);
    }
    public static void sendObject(AbstractSocket _socket, TricksHandsPresident _serializable) {
        NetGroupFrame.trySendString(DocumentWriterCardsMultiUtil.tricksHandsPresident(_serializable), _socket);
    }
    public static void sendObject(AbstractSocket _socket, TricksHandsTarot _serializable) {
        NetGroupFrame.trySendString(DocumentWriterCardsMultiUtil.tricksHandsTarot(_serializable), _socket);
    }
    public static void sendObject(AbstractSocket _socket, TeamsPlayers _serializable) {
        NetGroupFrame.trySendString(DocumentWriterCardsMultiUtil.teamsPlayers(_serializable), _socket);
    }
    public static void sendObject(AbstractSocket _socket, DiscardedTrumps _serializable) {
        NetGroupFrame.trySendString(DocumentWriterCardsMultiUtil.discardedTrumps(_serializable), _socket);
    }
    public static void sendObject(AbstractSocket _socket, AllowDiscarding _serializable) {
        NetGroupFrame.trySendString(DocumentWriterCardsMultiUtil.allowDiscarding(_serializable), _socket);
    }
    public static void sendObject(AbstractSocket _socket, ReceivedGivenCards _serializable) {
        NetGroupFrame.trySendString(DocumentWriterCardsMultiUtil.receivedGivenCards(_serializable), _socket);
    }
    /**server
     * @param _instance
     * @param _common*/
    static void initAllPresent(Net _instance, NetCommon _common) {
        _instance.activePlayers = new ByteMap<BoolVal>();
        for (byte r: _common.getPlacesPlayers().values()) {
            _instance.activePlayers.put(r, BoolVal.TRUE);
        }
    }
    static void initAllReady(NetCommon _common) {
        for (EntryCust<Integer,BoolVal> e: _common.getReadyPlayers().entryList()) {
            e.setValue(BoolVal.FALSE);
        }
    }
    /**server
     * @param _instance
     * @param _common*/
    static void initAllReceived(Net _instance, NetCommon _common) {
        if (_instance.received == null) {
            _instance.received = new ByteMap<BoolVal>();
        }
        for (byte r: _common.getPlacesPlayers().values()) {
            if (_instance.activePlayers.getVal(r) == BoolVal.TRUE) {
                _instance.received.put(r, BoolVal.FALSE);
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
        _instance.activePlayers.put(_p, BoolVal.FALSE);
        setReceivedForPlayer(_p, _instance);
    }
    //bk: synchronized
    /**server*/
    static void setReceivedForPlayer(byte _p, Net _instance) {
        _instance.received.put(_p,BoolVal.TRUE);
    }

    //bk: synchronized
    /**server*/
    static boolean allReceivedAmong(Bytes _players, Net _instance) {
        boolean allReceived_ = true;
        for (byte p: _instance.received.getKeys()) {
            if (!_players.containsObj(p)) {
                continue;
            }
            if (_instance.received.getVal(p) == BoolVal.TRUE) {
                continue;
            }
            allReceived_ = false;
            break;
        }
        return allReceived_;
    }

    static boolean allReceived(Net _instance) {
        boolean allReceived_ = true;
        for (BoolVal r: _instance.received.values()) {
            if (r == BoolVal.TRUE) {
                continue;
            }
            allReceived_ = false;
            break;
        }
        return allReceived_;
    }
    /**server
    @return true &hArr; the connected players are belonging to a seam team
     * @param _instance
     * @param _common */
    static boolean isSameTeam(Net _instance, NetCommon _common) {
        Bytes players_ = new Bytes(activePlayers(_instance, _common));
        return Net.getGames(_instance).isSameTeam(players_);
    }
    /**server
    bk: synchronized, called from mouse events or server loop
    @return the connected players
     * @param _instance
     * @param _common */
    static Bytes activePlayers(Net _instance, NetCommon _common) {
        if (_instance.activePlayers == null) {
            Bytes activePlayers_ = new Bytes();
            for (byte i: _common.getPlacesPlayers().values()) {
                activePlayers_.add(i);
            }
            return activePlayers_;
        }
        Bytes activePlayers_ = new Bytes();
        for (byte i: _common.getPlacesPlayers().values()) {
            if (_instance.activePlayers.getVal(i) != BoolVal.TRUE) {
                continue;
            }
            activePlayers_.add(i);
        }
        return activePlayers_;
    }

    /**server
     @return true &hArr; if the <i>_place</i> match with a currently connected player
      * @param _place the place of a bot or a player
     * @param _instance
     * @param _common */
    static boolean isHumanPlayer(byte _place, Net _instance, NetCommon _common) {
        return !getPlacesPlayersByValue(_place, _common).isEmpty() && _instance.activePlayers.getVal(_place) == BoolVal.TRUE;
    }
    /**server
     @return the associated socket of the place or null if it is an invalid place for a player
      * @param _place the place of a player around the table
     * @param _common */
    static AbstractSocket getSocketByPlace(byte _place, NetCommon _common) {
        for (int i: _common.getPlacesPlayers().getKeys()) {
            if (_common.getPlacesPlayers().getVal(i) == _place) {
                return _common.getSockets().getVal(i);
            }
        }
        return null;
    }
    /**server*/
    static String getLanguageByPlace(byte _place, Net _instance, NetCommon _common) {
        for (int i: _common.getPlacesPlayers().getKeys()) {
            if (_common.getPlacesPlayers().getVal(i) == _place) {
                return getPlayersLocales(_instance).getVal(i);
            }
        }
        return null;
    }
//
//    /**server*/
//    static boolean delegateServer(Quit _bye, Net _instance) {
//        for (byte p: Net.activePlayers(_instance)) {
//            if (p == _bye.getPlace()) {
//                continue;
//            }
//            DelegateServer d_ = new DelegateServer();
//            d_.setGames(Net.getGames(_instance));
//            d_.setNicknames(new IntMap<String>());
//            Net.sendObject(Net.getSocketByPlace(p, _instance),d_);
//            return true;
//        }
//        ByeCards forcedBye_ = new ByeCards();
//        forcedBye_.setForced(false);
//        forcedBye_.setServer(true);
//        forcedBye_.setClosing(_bye.isClosing());
//        Ints players_ = Net.getPlacesPlayersByValue(_bye.getPlace(), _instance);
//        if (!players_.isEmpty()) {
//            removePlayer(players_.first(), forcedBye_, _instance);
//        }
//        Net.getNicknames(_instance).clear();
//        Net.getGames(_instance).finirParties();
//        Net.getPlacesPlayers(_instance).clear();
//        return false;
//    }

    static void removePlayer(int _player, Exiting _bye, NetCommon _common) {
        AbstractSocket socket_ = _common.getSockets().getVal(_player);
        _common.getSockets().removeKey(_player);
        _common.getConnectionsServer().removeKey(_player);
        _common.getReadyPlayers().removeKey(_player);
        _common.getPlacesPlayers().removeKey(_player);
        Net.sendObject(socket_,_bye);
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

    public static void sendOkToQuit(Net _instance, NetCommon _common) {
        for (byte p: Net.activePlayers(_instance, _common)) {
            NetGroupFrame.trySendString("<"+ DocumentReaderCardsMultiUtil.TYPE_ENABLED_QUIT+"/>", Net.getSocketByPlace(p, _common));
        }
    }
    /**server*/
    public static Ints getPlacesPlayersByValue(byte _value, NetCommon _common) {
        Ints l_;
        l_ = new Ints();
        for (EntryCust<Integer, Byte> e: _common.getPlacesPlayers().entryList()) {
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
