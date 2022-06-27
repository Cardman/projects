package aiki.network;

import aiki.map.pokemon.PokemonPlayer;
import aiki.network.sml.DocumentReaderAikiMultiUtil;
import aiki.network.stream.*;
import code.gui.initialize.AbstractSocket;
import code.network.*;
import code.sml.Document;
import code.sml.Element;
import code.threads.AbstractBaseExecutorService;
import code.util.ByteTreeMap;
import code.util.comparators.ComparatorBoolean;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

/**This class thread is independant from EDT,
Thread safe class*/
public final class SendReceiveServerAiki extends BasicServer {

    private final AbstractBaseExecutorService lock;

    private final NetAiki instance;

    /**This class thread is independant from EDT*/
    public SendReceiveServerAiki(AbstractSocket _socket, NetGroupFrame _net, NetAiki _instance) {
        super(_socket, _net);
        lock = _net.getLock();
        instance = _instance;
    }

    @Override
    public void loopServer(String _input, Document _object) {
        lock.execute(new ServerIterationPk(instance, _input, _object, getNet().getSockets()));
    }

    static void loop(String _input, Document _object, NetAiki _instance, NetCommon _common) {
        Element elt_ = _object.getDocumentElement();
        PlayerActionBeforeGameAiki playerActionBeforeGame_ = DocumentReaderAikiMultiUtil.getPlayerActionBeforeGame(elt_);
        if (playerActionBeforeGame_ instanceof AddingPlayer) {
            AddingPlayer newPlayer_ = (AddingPlayer)playerActionBeforeGame_;
            if (!newPlayer_.isAcceptable()) {
                Exiting forcedBye_ = new Exiting();
                forcedBye_.setBusy(true);
                forcedBye_.setForced(true);
                forcedBye_.setClosing(false);
                forcedBye_.setTooManyPlayers(true);
//                Socket socket_ = Net.getSockets().getVal(newPlayer_.getIndex());
//                Net.getSockets().removeKey(newPlayer_.getIndex());
//                Net.getConnectionsServer().removeKey(newPlayer_.getIndex());
//                Net.getReadyPlayers().removeKey(newPlayer_.getIndex());
//                Net.getPlacesPlayers().removeKey(newPlayer_.getIndex());
//                Net.sendObject(socket_,forcedBye_);
                removePlayer(newPlayer_.getIndex(), forcedBye_, _common);
                return;
            }
        }
        if (playerActionBeforeGame_ instanceof NewPlayerAiki) {
            NewPlayerAiki newPlayer_ = (NewPlayerAiki)playerActionBeforeGame_;
            if (_common.getNicknames().size() == NetAiki.NB_PLAYERS) {
                Exiting forcedBye_ = new Exiting();
                forcedBye_.setForced(true);
                forcedBye_.setClosing(false);
                forcedBye_.setTooManyPlayers(true);
//                Socket socket_ = Net.getSockets().getVal(newPlayer_.getIndex());
//                Net.getSockets().removeKey(newPlayer_.getIndex());
//                Net.getConnectionsServer().removeKey(newPlayer_.getIndex());
//                Net.getReadyPlayers().removeKey(newPlayer_.getIndex());
//                Net.getPlacesPlayers().removeKey(newPlayer_.getIndex());
//                Net.sendObject(socket_,forcedBye_);
                removePlayer(newPlayer_.getIndex(), forcedBye_, _common);
                return;
            }
            if (newPlayer_.getIndex() == IndexConstants.SECOND_INDEX) {
                NetAiki.sendObjectInitTrading(_common.getSockets().getVal((int) IndexConstants.SECOND_INDEX));
                return;
            }
            if (newPlayer_.getIndex() == IndexConstants.FIRST_INDEX) {
                //init trading condition
                NetAiki.sendObjectInitTrading(_common.getSockets().getVal((int) IndexConstants.FIRST_INDEX));
                return;
            }
            return;
        }
        String tagName_ = DocumentReaderAikiMultiUtil.tagName(elt_);
        if (StringUtil.quickEq(DocumentReaderAikiMultiUtil.TYPE_CHECK_COMPATIBILITY,tagName_)) {
            CheckCompatibility check_ = DocumentReaderAikiMultiUtil.getCheckCompatibility(elt_);
            if (check_.getIndex() == IndexConstants.FIRST_INDEX) {
                NetAiki.getCheckCompatibility(_instance).put((int) IndexConstants.FIRST_INDEX, check_);
                return;
            }
            if (check_.getIndex() == IndexConstants.SECOND_INDEX) {
                CheckCompatibility first_ = NetAiki.getCheckCompatibility(_instance).getVal((int) IndexConstants.FIRST_INDEX);
                ByteTreeMap< PokemonPlayer> pkFirst_ = check_.getData().getTeam(first_.getTeam());
                ByteTreeMap< PokemonPlayer> pkSecond_ = first_.getData().getTeam(check_.getTeam());
                if (pkFirst_.isEmpty() || pkSecond_.isEmpty()) {
                    Exiting forcedBye_ = new Exiting();
                    forcedBye_.setForced(true);
                    forcedBye_.setClosing(false);
                    forcedBye_.setTooManyPlayers(false);
//                    Socket socket_ = Net.getSockets().getVal(CustList.SECOND_INDEX);
//                    Net.getSockets().removeKey(CustList.SECOND_INDEX);
//                    Net.getConnectionsServer().removeKey(CustList.SECOND_INDEX);
//                    Net.getReadyPlayers().removeKey(CustList.SECOND_INDEX);
//                    Net.getPlacesPlayers().removeKey(CustList.SECOND_INDEX);
//                    Net.sendObject(socket_,forcedBye_);
                    removePlayer(IndexConstants.SECOND_INDEX, forcedBye_, _common);
                    return;
                }
                NetPokemon net_ = new NetPokemon();
                net_.setTradablePokemon(pkFirst_);
                NetAiki.sendObject(_common.getSockets().getVal((int) IndexConstants.FIRST_INDEX),net_);
                net_ = new NetPokemon();
                net_.setTradablePokemon(pkSecond_);
                NetAiki.sendObject(_common.getSockets().getVal((int) IndexConstants.SECOND_INDEX),net_);
                return;
            }
        }
        if (StringUtil.quickEq(DocumentReaderAikiMultiUtil.TYPE_SENT_POKEMON,tagName_)) {
            SentPokemon sent_ = DocumentReaderAikiMultiUtil.getSentPokemon(elt_);
            if (sent_.getIndex() == IndexConstants.FIRST_INDEX) {
                NetAiki.sendObject(_common.getSockets().getVal((int) IndexConstants.SECOND_INDEX),sent_.getPokemon());
            }
            if (sent_.getIndex() == IndexConstants.SECOND_INDEX) {
                NetAiki.sendObject(_common.getSockets().getVal((int) IndexConstants.FIRST_INDEX),sent_.getPokemon());
            }
            return;
        }
        if (playerActionBeforeGame_ instanceof ReadyAiki) {
            int noClient_ = playerActionBeforeGame_.getIndex();
            _common.getReadyPlayers().put(noClient_, ComparatorBoolean.of(((ReadyAiki)playerActionBeforeGame_).isReady()));
            return;
        }
        if (StringUtil.quickEq(DocumentReaderAikiMultiUtil.TYPE_OK,tagName_)) {
            if (_common.allReady()) {
                for(AbstractSocket so_: _common.getSockets().values()){
                    NetGroupFrame.trySendString(_input, so_);
                }
                for (int i: _common.getReadyPlayers().getKeys()) {
                    _common.getReadyPlayers().put(i, BoolVal.FALSE);
                }
            }
            return;
        }
        PlayerActionGameAiki playerActionGame_ = DocumentReaderAikiMultiUtil.getPlayerActionGame(elt_);
        if (playerActionGame_ instanceof QuitAiki) {
            QuitAiki bye_ = (QuitAiki)playerActionGame_;
            Exiting forcedBye_ = new Exiting();
            forcedBye_.setForced(false);
            forcedBye_.setServer(true);
            forcedBye_.setTooManyPlayers(false);
            AbstractSocket socket_;
            socket_ = _common.getSockets().getVal((int) IndexConstants.FIRST_INDEX);
            _common.getConnectionsServer().removeKey((int) IndexConstants.FIRST_INDEX);
            _common.getReadyPlayers().removeKey((int) IndexConstants.FIRST_INDEX);
            _common.getPlacesPlayers().removeKey((int) IndexConstants.FIRST_INDEX);
            if (bye_.getPlace() == IndexConstants.FIRST_INDEX) {
                forcedBye_.setClosing(bye_.isClosing());
            } else {
                forcedBye_.setClosing(false);
            }
            if (socket_ != null) {
                NetAiki.sendObject(socket_,forcedBye_);
            }
            socket_ = _common.getSockets().getVal((int) IndexConstants.SECOND_INDEX);
            _common.getConnectionsServer().removeKey((int) IndexConstants.SECOND_INDEX);
            _common.getReadyPlayers().removeKey((int) IndexConstants.SECOND_INDEX);
            _common.getPlacesPlayers().removeKey((int) IndexConstants.SECOND_INDEX);
            if (bye_.getPlace() == IndexConstants.SECOND_INDEX) {
                forcedBye_.setClosing(bye_.isClosing());
            } else {
                forcedBye_.setClosing(false);
            }
            if (socket_ != null) {
                NetAiki.sendObject(socket_,forcedBye_);
            }
            _common.getSockets().clear();
        }
    }

    static void removePlayer(int _player, Exiting _bye, NetCommon _common) {
        AbstractSocket socket_ = _common.getSockets().getVal(_player);
        _common.getSockets().removeKey(_player);
        _common.getConnectionsServer().removeKey(_player);
        _common.getReadyPlayers().removeKey(_player);
        _common.getPlacesPlayers().removeKey(_player);
        NetAiki.sendObject(socket_,_bye);
    }
}
