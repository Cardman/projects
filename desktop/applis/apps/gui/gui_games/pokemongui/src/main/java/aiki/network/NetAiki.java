package aiki.network;

import aiki.map.pokemon.PokemonPlayer;
import aiki.network.sml.DocumentWriterAikiMultiUtil;
import aiki.network.stream.*;
import code.gui.initialize.AbstractSocket;
import code.network.NetCommon;
import code.network.NetGroupFrame;
import code.util.*;
import code.util.core.BoolVal;

public final class NetAiki {

    public static final int NB_PLAYERS = 2;

    /** A used port for connections*/
    private static final int PORT = 668;

    private static final String POKEMON = "POKEMON";

    private IntMap<CheckCompatibility> checkCompatibility = new IntMap<CheckCompatibility>();

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
    static void sendText(AbstractSocket _socket, String _text) {
        NetGroupFrame.trySendString(_text,_socket);
    }

    /**server
        @return true &hArr; the players are ready to begin a deal
         * @param _common */
    public static boolean allReady(NetCommon _common) {
        boolean allReady_ = true;
        for (BoolVal r: _common.getReadyPlayers().values()) {
            if (r == BoolVal.TRUE) {
                continue;
            }
            allReady_ = false;
            break;
        }
        return allReady_;
    }

    public static void sendObjectInitTrading(AbstractSocket _socket) {
        NetAiki.sendText(_socket,DocumentWriterAikiMultiUtil.initTrading());
    }

    public static void sendObject(AbstractSocket _socket, NetPokemon _serializable) {
        NetAiki.sendText(_socket,DocumentWriterAikiMultiUtil.netPokemon(_serializable));
    }

    public static void sendObject(AbstractSocket _socket, PokemonPlayer _serializable) {
        NetAiki.sendText(_socket,DocumentWriterAikiMultiUtil.pokemonPlayer(_serializable));
    }

    public static void sendObject(AbstractSocket _socket, ByeAiki _serializable) {
        NetAiki.sendText(_socket,DocumentWriterAikiMultiUtil.bye(_serializable));
    }

    public static void sendObject(AbstractSocket _socket, IndexOfArrivingAiki _serializable) {
        NetAiki.sendText(_socket,DocumentWriterAikiMultiUtil.indexOfArrivingAiki(_serializable));
    }

    public static void sendObject(AbstractSocket _socket, NewPlayerAiki _serializable) {
        NetAiki.sendText(_socket,DocumentWriterAikiMultiUtil.newPlayerAiki(_serializable));
    }

    public static void sendObject(AbstractSocket _socket, CheckCompatibility _serializable) {
        NetAiki.sendText(_socket,DocumentWriterAikiMultiUtil.checkCompatibility(_serializable));
    }
    /**server
     * @param _instance*/
    public static IntMap<CheckCompatibility> getCheckCompatibility(NetAiki _instance) {
        return _instance.checkCompatibility;
    }

}
