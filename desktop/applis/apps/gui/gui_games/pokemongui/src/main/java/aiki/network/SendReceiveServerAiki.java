package aiki.network;

import aiki.map.pokemon.PokemonPlayer;
import aiki.network.sml.DocumentReaderAikiMultiUtil;
import aiki.network.stream.*;
import code.network.AddingPlayer;
import code.network.BasicServer;
import code.gui.initialize.AbstractSocket;
import code.network.NetGroupFrame;
import code.sml.Document;
import code.sml.Element;
import code.threads.AbstractLock;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

/**This class thread is independant from EDT,
Thread safe class*/
public final class SendReceiveServerAiki extends BasicServer {

    private final AbstractLock lock;

    private final NetAiki instance;

    /**This class thread is independant from EDT*/
    public SendReceiveServerAiki(AbstractSocket _socket, NetGroupFrame _net, NetAiki _instance) {
        super(_socket, _net);
        lock = _net.getLock();
        instance = _instance;
    }

    @Override
    public void loopServer(String _input, Document _object) {
        lock.lock(this);
        loop(_input, _object, instance);
        lock.unlock(this);
    }

    private static void loop(String _input, Document _object, NetAiki _instance) {
        Element elt_ = _object.getDocumentElement();
        PlayerActionBeforeGameAiki playerActionBeforeGame_ = DocumentReaderAikiMultiUtil.getPlayerActionBeforeGame(elt_);
        if (playerActionBeforeGame_ instanceof AddingPlayer) {
            AddingPlayer newPlayer_ = (AddingPlayer)playerActionBeforeGame_;
            if (!newPlayer_.isAcceptable()) {
                ByeAiki forcedBye_ = new ByeAiki();
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
                removePlayer(newPlayer_.getIndex(), forcedBye_, _instance);
                return;
            }
        }
        if (playerActionBeforeGame_ instanceof NewPlayerAiki) {
            NewPlayerAiki newPlayer_ = (NewPlayerAiki)playerActionBeforeGame_;
            if (NetAiki.getNicknames(_instance).size() == NetAiki.NB_PLAYERS) {
                ByeAiki forcedBye_ = new ByeAiki();
                forcedBye_.setForced(true);
                forcedBye_.setClosing(false);
                forcedBye_.setTooManyPlayers(true);
//                Socket socket_ = Net.getSockets().getVal(newPlayer_.getIndex());
//                Net.getSockets().removeKey(newPlayer_.getIndex());
//                Net.getConnectionsServer().removeKey(newPlayer_.getIndex());
//                Net.getReadyPlayers().removeKey(newPlayer_.getIndex());
//                Net.getPlacesPlayers().removeKey(newPlayer_.getIndex());
//                Net.sendObject(socket_,forcedBye_);
                removePlayer(newPlayer_.getIndex(), forcedBye_, _instance);
                return;
            }
            if (newPlayer_.getIndex() == IndexConstants.SECOND_INDEX) {
                NetAiki.sendObjectInitTrading(NetAiki.getSockets(_instance).getVal((int) IndexConstants.SECOND_INDEX));
                return;
            }
            if (newPlayer_.getIndex() == IndexConstants.FIRST_INDEX) {
                //init trading condition
                NetAiki.sendObjectInitTrading(NetAiki.getSockets(_instance).getVal((int) IndexConstants.FIRST_INDEX));
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
                    ByeAiki forcedBye_ = new ByeAiki();
                    forcedBye_.setForced(true);
                    forcedBye_.setClosing(false);
                    forcedBye_.setTooManyPlayers(false);
//                    Socket socket_ = Net.getSockets().getVal(CustList.SECOND_INDEX);
//                    Net.getSockets().removeKey(CustList.SECOND_INDEX);
//                    Net.getConnectionsServer().removeKey(CustList.SECOND_INDEX);
//                    Net.getReadyPlayers().removeKey(CustList.SECOND_INDEX);
//                    Net.getPlacesPlayers().removeKey(CustList.SECOND_INDEX);
//                    Net.sendObject(socket_,forcedBye_);
                    removePlayer(IndexConstants.SECOND_INDEX, forcedBye_, _instance);
                    return;
                }
                NetPokemon net_ = new NetPokemon();
                net_.setTradablePokemon(pkFirst_);
                NetAiki.sendObject(NetAiki.getSockets(_instance).getVal((int) IndexConstants.FIRST_INDEX),net_);
                net_ = new NetPokemon();
                net_.setTradablePokemon(pkSecond_);
                NetAiki.sendObject(NetAiki.getSockets(_instance).getVal((int) IndexConstants.SECOND_INDEX),net_);
                return;
            }
        }
        if (StringUtil.quickEq(DocumentReaderAikiMultiUtil.TYPE_SENT_POKEMON,tagName_)) {
            SentPokemon sent_ = DocumentReaderAikiMultiUtil.getSentPokemon(elt_);
            if (sent_.getIndex() == IndexConstants.FIRST_INDEX) {
                NetAiki.sendObject(NetAiki.getSockets(_instance).getVal((int) IndexConstants.SECOND_INDEX),sent_.getPokemon());
            }
            if (sent_.getIndex() == IndexConstants.SECOND_INDEX) {
                NetAiki.sendObject(NetAiki.getSockets(_instance).getVal((int) IndexConstants.FIRST_INDEX),sent_.getPokemon());
            }
            return;
        }
        if (playerActionBeforeGame_ instanceof ReadyAiki) {
            int noClient_ = playerActionBeforeGame_.getIndex();
            NetAiki.getReadyPlayers(_instance).put(noClient_, ((ReadyAiki)playerActionBeforeGame_).isReady());
            return;
        }
        if (StringUtil.quickEq(DocumentReaderAikiMultiUtil.TYPE_OK,tagName_)) {
            if (NetAiki.allReady(_instance)) {
                for(AbstractSocket so_: NetAiki.getSockets(_instance).values()){
                    NetAiki.sendText(so_,_input);
                }
                for (int i: NetAiki.getReadyPlayers(_instance).getKeys()) {
                    NetAiki.getReadyPlayers(_instance).put(i, false);
                }
            }
            return;
        }
        PlayerActionGameAiki playerActionGame_ = DocumentReaderAikiMultiUtil.getPlayerActionGame(elt_);
        if (playerActionGame_ instanceof QuitAiki) {
            QuitAiki bye_ = (QuitAiki)playerActionGame_;
            ByeAiki forcedBye_ = new ByeAiki();
            forcedBye_.setForced(false);
            forcedBye_.setServer(false);
            forcedBye_.setTooManyPlayers(false);
            AbstractSocket socket_;
            socket_ = NetAiki.getSockets(_instance).getVal((int) IndexConstants.FIRST_INDEX);
            NetAiki.getConnectionsServer(_instance).removeKey((int) IndexConstants.FIRST_INDEX);
            NetAiki.getReadyPlayers(_instance).removeKey((int) IndexConstants.FIRST_INDEX);
            NetAiki.getPlacesPlayers(_instance).removeKey((int) IndexConstants.FIRST_INDEX);
            if (bye_.getPlace() == IndexConstants.FIRST_INDEX) {
                forcedBye_.setClosing(bye_.isClosing());
            } else {
                forcedBye_.setClosing(false);
            }
            if (socket_ != null) {
                NetAiki.sendObject(socket_,forcedBye_);
            }
            socket_ = NetAiki.getSockets(_instance).getVal((int) IndexConstants.SECOND_INDEX);
            NetAiki.getConnectionsServer(_instance).removeKey((int) IndexConstants.SECOND_INDEX);
            NetAiki.getReadyPlayers(_instance).removeKey((int) IndexConstants.SECOND_INDEX);
            NetAiki.getPlacesPlayers(_instance).removeKey((int) IndexConstants.SECOND_INDEX);
            if (bye_.getPlace() == IndexConstants.SECOND_INDEX) {
                forcedBye_.setClosing(bye_.isClosing());
            } else {
                forcedBye_.setClosing(false);
            }
            if (socket_ != null) {
                NetAiki.sendObject(socket_,forcedBye_);
            }
            NetAiki.getSockets(_instance).clear();
        }
    }

    static void removePlayer(int _player, ByeAiki _bye, NetAiki _instance) {
        AbstractSocket socket_ = NetAiki.getSockets(_instance).getVal(_player);
        NetAiki.getSockets(_instance).removeKey(_player);
        NetAiki.getConnectionsServer(_instance).removeKey(_player);
        NetAiki.getReadyPlayers(_instance).removeKey(_player);
        NetAiki.getPlacesPlayers(_instance).removeKey(_player);
        NetAiki.sendObject(socket_,_bye);
    }
}
