package aiki.network;

import aiki.map.pokemon.PokemonPlayer;
import aiki.network.sml.DocumentWriterAikiMultiUtil;
import aiki.network.stream.*;
import code.gui.initialize.AbstractSocket;
import code.network.NetGroupFrame;
import code.util.*;

public final class NetAiki {

    public static final int NB_PLAYERS = 2;

    /** A used port for connections*/
    private static final int PORT = 668;

    private static final String POKEMON = "POKEMON";

    private IntMap<AbstractSocket> sockets =new IntMap<AbstractSocket>();
    private IntTreeMap< Byte> placesPlayers = new IntTreeMap< Byte>();
    private IntMap<Boolean> readyPlayers = new IntMap<Boolean>();

    private IntMap<CheckCompatibility> checkCompatibility = new IntMap<CheckCompatibility>();

    private IntMap<String> nicknames =new IntMap<String>();
    private IntMap<SendReceiveServerAiki> connectionsServer =new IntMap<SendReceiveServerAiki>();

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
         * @param _instance*/
    public static boolean allReady(NetAiki _instance) {
        boolean allReady_ = true;
        for (boolean r: NetAiki.getReadyPlayers(_instance).values()) {
            if (r) {
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
    public static IntMap<SendReceiveServerAiki> getConnectionsServer(NetAiki _instance) {
        return _instance.connectionsServer;
    }

    /**server
     * @param _instance*/
    public static IntMap<Boolean> getReadyPlayers(NetAiki _instance) {
        return _instance.readyPlayers;
    }

    /**server
     * @param _instance*/
    public static IntMap<AbstractSocket> getSockets(NetAiki _instance) {
        return _instance.sockets;
    }

    /**server
     * @param _instance*/
    public static IntTreeMap< Byte> getPlacesPlayers(NetAiki _instance) {
        return _instance.placesPlayers;
    }

    /**server
     * @param _instance*/
    public static IntMap<CheckCompatibility> getCheckCompatibility(NetAiki _instance) {
        return _instance.checkCompatibility;
    }

    /**server
     * @param _instance*/
    public static IntMap<String> getNicknames(NetAiki _instance) {
        return _instance.nicknames;
    }

}
