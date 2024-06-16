package aiki.network;

import aiki.map.pokemon.PokemonPlayer;
import aiki.network.stream.CheckCompatibility;
import aiki.network.stream.NetPokemon;
import code.gui.initialize.AbstractSocket;
import code.network.Exiting;
import code.network.NetCommon;
import code.network.NetGroupFrame;
import code.util.ByteTreeMap;
import code.util.CustList;
import code.util.core.IndexConstants;

public final class ServerActLoopAikiCheckCompatibility implements IntServerActLoopAiki {
    @Override
    public void loop(CustList<String> _input, NetAiki _instance, NetCommon _common) {
        CheckCompatibility check_ = NetAiki.importCheckCompatibility(_input);
        if (check_.getIndex() == IndexConstants.FIRST_INDEX) {
            NetAiki.getCheckCompatibility(_instance).put((int) IndexConstants.FIRST_INDEX, check_);
            return;
        }
        CheckCompatibility first_ = NetAiki.getCheckCompatibility(_instance).getVal((int) IndexConstants.FIRST_INDEX);
        ByteTreeMap<PokemonPlayer> pkFirst_ = check_.getData().getTeam(first_.getTeam());
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
        NetGroupFrame.trySendString(NetAiki.exportNetPokemon(net_), _common.getSockets().getVal((int) IndexConstants.FIRST_INDEX));
        net_ = new NetPokemon();
        net_.setTradablePokemon(pkSecond_);
        NetGroupFrame.trySendString(NetAiki.exportNetPokemon(net_), _common.getSockets().getVal((int) IndexConstants.SECOND_INDEX));
//        if (check_.getIndex() == IndexConstants.SECOND_INDEX) {
//            CheckCompatibility first_ = NetAiki.getCheckCompatibility(_instance).getVal((int) IndexConstants.FIRST_INDEX);
//            ByteTreeMap<PokemonPlayer> pkFirst_ = check_.getData().getTeam(first_.getTeam());
//            ByteTreeMap< PokemonPlayer> pkSecond_ = first_.getData().getTeam(check_.getTeam());
//            if (pkFirst_.isEmpty() || pkSecond_.isEmpty()) {
//                Exiting forcedBye_ = new Exiting();
//                forcedBye_.setForced(true);
//                forcedBye_.setClosing(false);
//                forcedBye_.setTooManyPlayers(false);
////                    Socket socket_ = Net.getSockets().getVal(CustList.SECOND_INDEX);
////                    Net.getSockets().removeKey(CustList.SECOND_INDEX);
////                    Net.getConnectionsServer().removeKey(CustList.SECOND_INDEX);
////                    Net.getReadyPlayers().removeKey(CustList.SECOND_INDEX);
////                    Net.getPlacesPlayers().removeKey(CustList.SECOND_INDEX);
////                    Net.sendObject(socket_,forcedBye_);
//                removePlayer(IndexConstants.SECOND_INDEX, forcedBye_, _common);
//                return;
//            }
//            NetPokemon net_ = new NetPokemon();
//            net_.setTradablePokemon(pkFirst_);
//            NetAiki.sendObject(_common.getSockets().getVal((int) IndexConstants.FIRST_INDEX),net_);
//            net_ = new NetPokemon();
//            net_.setTradablePokemon(pkSecond_);
//            NetAiki.sendObject(_common.getSockets().getVal((int) IndexConstants.SECOND_INDEX),net_);
//            return;
//        }
//        int index_ = NumberUtil.parseInt(_input.get(0));
//        NetGroupFrame.trySendString(NetAiki.exportInitTrading(), _common.getSockets().getVal(index_));
    }

    static void removePlayer(int _player, Exiting _bye, NetCommon _common) {
        AbstractSocket socket_ = _common.getSockets().getVal(_player);
        _common.getSockets().removeKey(_player);
        _common.getConnectionsServer().removeKey(_player);
        _common.getReadyPlayers().removeKey(_player);
        _common.getPlacesPlayers().removeKey(_player);
        NetGroupFrame.trySendString(NetCommon.exportExiting(_bye), socket_);
    }
}
