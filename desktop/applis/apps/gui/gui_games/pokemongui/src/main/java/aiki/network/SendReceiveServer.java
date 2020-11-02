package aiki.network;
import java.net.Socket;

import aiki.map.pokemon.PokemonPlayer;
import aiki.network.stream.Bye;
import aiki.network.stream.CheckCompatibility;
import aiki.network.stream.InitTrading;
import aiki.network.stream.NetPokemon;
import aiki.network.stream.NewPlayer;
import aiki.network.stream.Ok;
import aiki.network.stream.Quit;
import aiki.network.stream.Ready;
import aiki.network.stream.SentPokemon;
import code.network.AddingPlayer;
import code.network.BasicServer;
import code.network.NetGroupFrame;
import code.threads.AbstractLock;
import code.util.*;
import code.util.core.IndexConstants;

/**This class thread is independant from EDT,
Thread safe class*/
public final class SendReceiveServer extends BasicServer {

    private final AbstractLock lock;

    private final Net instance;

    /**This class thread is independant from EDT*/
    public SendReceiveServer(Socket _socket, NetGroupFrame _net, Net _instance) {
        super(_socket, _net);
        lock = _net.getLock();
        instance = _instance;
    }

    @Override
    public void loopServer(String _input, Object _object) {
        lock.lock(this);
        loop(_input, _object, instance);
        lock.unlock(this);
    }

    private static void loop(String _input, Object _readObject, Net _instance) {
        if (_readObject instanceof AddingPlayer) {
            AddingPlayer newPlayer_ = (AddingPlayer)_readObject;
            if (!newPlayer_.isAcceptable()) {
                Bye forcedBye_ = new Bye();
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
        if (_readObject instanceof NewPlayer) {
            NewPlayer newPlayer_ = (NewPlayer)_readObject;
            if (Net.getNicknames(_instance).size() == Net.NB_PLAYERS) {
                Bye forcedBye_ = new Bye();
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
                Net.sendObject(Net.getSockets(_instance).getVal((int) IndexConstants.SECOND_INDEX),InitTrading.INSTANCE);
                return;
            }
            if (newPlayer_.getIndex() == IndexConstants.FIRST_INDEX) {
                //init trading condition
                Net.sendObject(Net.getSockets(_instance).getVal((int) IndexConstants.FIRST_INDEX),InitTrading.INSTANCE);
                return;
            }
            return;
        }
        if (_readObject instanceof CheckCompatibility) {
            CheckCompatibility check_ = (CheckCompatibility) _readObject;
            if (check_.getIndex() == IndexConstants.FIRST_INDEX) {
                Net.getCheckCompatibility(_instance).put((int) IndexConstants.FIRST_INDEX, check_);
                return;
            }
            if (check_.getIndex() == IndexConstants.SECOND_INDEX) {
                CheckCompatibility first_ = Net.getCheckCompatibility(_instance).getVal((int) IndexConstants.FIRST_INDEX);
                ByteTreeMap< PokemonPlayer> pkFirst_ = check_.getData().getTeam(first_.getTeam());
                ByteTreeMap< PokemonPlayer> pkSecond_ = first_.getData().getTeam(check_.getTeam());
                if (pkFirst_.isEmpty() || pkSecond_.isEmpty()) {
                    Bye forcedBye_ = new Bye();
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
                Net.sendObject(Net.getSockets(_instance).getVal((int) IndexConstants.FIRST_INDEX),net_);
                net_ = new NetPokemon();
                net_.setTradablePokemon(pkSecond_);
                Net.sendObject(Net.getSockets(_instance).getVal((int) IndexConstants.SECOND_INDEX),net_);
                return;
            }
        }
        if (_readObject instanceof SentPokemon) {
            SentPokemon sent_ = (SentPokemon) _readObject;
            if (sent_.getIndex() == IndexConstants.FIRST_INDEX) {
                Net.sendObject(Net.getSockets(_instance).getVal((int) IndexConstants.SECOND_INDEX),sent_.getPokemon());
            }
            if (sent_.getIndex() == IndexConstants.SECOND_INDEX) {
                Net.sendObject(Net.getSockets(_instance).getVal((int) IndexConstants.FIRST_INDEX),sent_.getPokemon());
            }
            return;
        }
        if (_readObject instanceof Ready) {
            int noClient_ = ((Ready)_readObject).getIndex();
            Net.getReadyPlayers(_instance).put(noClient_, (( Ready)_readObject).isReady());
            return;
        }
        if (_readObject == Ok.INSTANCE) {
            if (Net.allReady(_instance)) {
                for(Socket so_:Net.getSockets(_instance).values()){
                    Net.sendText(so_,_input);
                }
                for (int i: Net.getReadyPlayers(_instance).getKeys()) {
                    Net.getReadyPlayers(_instance).put(i, false);
                }
            }
            return;
        }
        if (_readObject instanceof Quit) {
            Quit bye_ = (Quit)_readObject;
            Bye forcedBye_ = new Bye();
            forcedBye_.setForced(false);
            forcedBye_.setServer(false);
            forcedBye_.setTooManyPlayers(false);
            Socket socket_;
            socket_ = Net.getSockets(_instance).getVal((int) IndexConstants.FIRST_INDEX);
            Net.getConnectionsServer(_instance).removeKey((int) IndexConstants.FIRST_INDEX);
            Net.getReadyPlayers(_instance).removeKey((int) IndexConstants.FIRST_INDEX);
            Net.getPlacesPlayers(_instance).removeKey((int) IndexConstants.FIRST_INDEX);
            if (bye_.getPlace() == IndexConstants.FIRST_INDEX) {
                forcedBye_.setClosing(bye_.isClosing());
            } else {
                forcedBye_.setClosing(false);
            }
            if (socket_ != null) {
                Net.sendObject(socket_,forcedBye_);
            }
            socket_ = Net.getSockets(_instance).getVal((int) IndexConstants.SECOND_INDEX);
            Net.getConnectionsServer(_instance).removeKey((int) IndexConstants.SECOND_INDEX);
            Net.getReadyPlayers(_instance).removeKey((int) IndexConstants.SECOND_INDEX);
            Net.getPlacesPlayers(_instance).removeKey((int) IndexConstants.SECOND_INDEX);
            if (bye_.getPlace() == IndexConstants.SECOND_INDEX) {
                forcedBye_.setClosing(bye_.isClosing());
            } else {
                forcedBye_.setClosing(false);
            }
            if (socket_ != null) {
                Net.sendObject(socket_,forcedBye_);
            }
            Net.getSockets(_instance).clear();
        }
    }

    static void removePlayer(int _player, Bye _bye, Net _instance) {
        Socket socket_ = Net.getSockets(_instance).getVal(_player);
        Net.getSockets(_instance).removeKey(_player);
        Net.getConnectionsServer(_instance).removeKey(_player);
        Net.getReadyPlayers(_instance).removeKey(_player);
        Net.getPlacesPlayers(_instance).removeKey(_player);
        Net.sendObject(socket_,_bye);
    }
}
