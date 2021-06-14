package aiki.network;

import aiki.network.sml.DocumentWriterAikiMultiUtil;
import aiki.network.stream.CheckCompatibility;
import code.gui.initialize.AbstractSocket;
import code.network.NetGroupFrame;
import code.util.*;

public final class Net {

    public static final int NB_PLAYERS = 2;

    /** A used port for connections*/
    private static final int PORT = 668;

    private static final String POKEMON = "POKEMON";

    private IntMap<AbstractSocket> sockets =new IntMap<AbstractSocket>();
    private IntTreeMap< Byte> placesPlayers = new IntTreeMap< Byte>();
    private IntMap<Boolean> readyPlayers = new IntMap<Boolean>();

    private IntMap<CheckCompatibility> checkCompatibility = new IntMap<CheckCompatibility>();

    private IntMap<String> nicknames =new IntMap<String>();
    private IntMap<SendReceiveServer> connectionsServer =new IntMap<SendReceiveServer>();

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

    public static void sendObject(AbstractSocket _socket,Object _serializable) {
        Net.sendText(_socket,DocumentWriterAikiMultiUtil.setObject(_serializable));
    }

    /**server
     * @param _instance*/
    public static IntMap<SendReceiveServer> getConnectionsServer(Net _instance) {
        return _instance.connectionsServer;
    }

    /**server
     * @param _instance*/
    public static IntMap<Boolean> getReadyPlayers(Net _instance) {
        return _instance.readyPlayers;
    }

    /**server
     * @param _instance*/
    public static IntMap<AbstractSocket> getSockets(Net _instance) {
        return _instance.sockets;
    }

    /**server
     * @param _instance*/
    public static IntTreeMap< Byte> getPlacesPlayers(Net _instance) {
        return _instance.placesPlayers;
    }

    /**server
     * @param _instance*/
    public static IntMap<CheckCompatibility> getCheckCompatibility(Net _instance) {
        return _instance.checkCompatibility;
    }

    /**server
     * @param _instance*/
    public static IntMap<String> getNicknames(Net _instance) {
        return _instance.nicknames;
    }

}
