package aiki.network;

import aiki.map.pokemon.PokemonPlayer;
import aiki.network.sml.DocumentWriterAikiMultiUtil;
import aiki.network.stream.*;
import code.gui.initialize.AbstractSocket;
import code.network.Exiting;
import code.network.NetGroupFrame;
import code.util.*;

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

    public static void sendObjectInitTrading(AbstractSocket _socket) {
        NetGroupFrame.trySendString(DocumentWriterAikiMultiUtil.initTrading(), _socket);
    }

    public static void sendObject(AbstractSocket _socket, NetPokemon _serializable) {
        NetGroupFrame.trySendString(DocumentWriterAikiMultiUtil.netPokemon(_serializable), _socket);
    }

    public static void sendObject(AbstractSocket _socket, PokemonPlayer _serializable) {
        NetGroupFrame.trySendString(DocumentWriterAikiMultiUtil.pokemonPlayer(_serializable), _socket);
    }

    public static void sendObject(AbstractSocket _socket, Exiting _serializable) {
        NetGroupFrame.trySendString(DocumentWriterAikiMultiUtil.bye(_serializable), _socket);
    }

    public static void sendObject(AbstractSocket _socket, IndexOfArrivingAiki _serializable) {
        NetGroupFrame.trySendString(DocumentWriterAikiMultiUtil.indexOfArrivingAiki(_serializable), _socket);
    }

    public static void sendObject(AbstractSocket _socket, NewPlayerAiki _serializable) {
        NetGroupFrame.trySendString(DocumentWriterAikiMultiUtil.newPlayerAiki(_serializable), _socket);
    }

    public static void sendObject(AbstractSocket _socket, CheckCompatibility _serializable) {
        NetGroupFrame.trySendString(DocumentWriterAikiMultiUtil.checkCompatibility(_serializable), _socket);
    }
    /**server
     * @param _instance*/
    public static IntMap<CheckCompatibility> getCheckCompatibility(NetAiki _instance) {
        return _instance.checkCompatibility;
    }

}
