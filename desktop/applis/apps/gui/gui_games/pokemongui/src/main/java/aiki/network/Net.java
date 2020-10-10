package aiki.network;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import aiki.network.sml.DocumentWriterAikiMultiUtil;
import aiki.network.stream.CheckCompatibility;
import code.network.NetGroupFrame;
import code.util.*;

public final class Net {

    public static final int NB_PLAYERS = 2;

    /** A used port for connections*/
    private static final int PORT = 668;

    private static final String POKEMON = "POKEMON";

    private static final Net INSTANCE = new Net();

    private IntMap<Socket> sockets =new IntMap<Socket>();
    private IntTreeMap< Byte> placesPlayers = new IntTreeMap< Byte>();
    private IntMap<Boolean> readyPlayers = new IntMap<Boolean>();

    private IntMap<CheckCompatibility> checkCompatibility = new IntMap<CheckCompatibility>();

    private IntMap<String> nicknames =new IntMap<String>();
    private IntMap<SendReceiveServer> connectionsServer =new IntMap<SendReceiveServer>();

    private Net() {
    }

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
    static void sendText(Socket _socket, String _text) {
        NetGroupFrame.trySendString(_text,_socket);
    }

    /**server
        @return true &hArr; the players are ready to begin a deal*/
    public static boolean allReady() {
        boolean allReady_ = true;
        for (boolean r: Net.getReadyPlayers().values()) {
            if (r) {
                continue;
            }
            allReady_ = false;
            break;
        }
        return allReady_;
    }

    public static void sendObject(Socket _socket,Object _serializable) {
        Net.sendText(_socket,DocumentWriterAikiMultiUtil.setObject(_serializable));
    }

    /**server*/
    public static IntMap<SendReceiveServer> getConnectionsServer() {
        return INSTANCE.connectionsServer;
    }

    /**server*/
    public static IntMap<Boolean> getReadyPlayers() {
        return INSTANCE.readyPlayers;
    }

    /**server*/
    public static IntMap<Socket> getSockets() {
        return INSTANCE.sockets;
    }

    /**server*/
    public static IntTreeMap< Byte> getPlacesPlayers() {
        return INSTANCE.placesPlayers;
    }

    /**server*/
    public static IntMap<CheckCompatibility> getCheckCompatibility() {
        return INSTANCE.checkCompatibility;
    }

    /**server*/
    public static IntMap<String> getNicknames() {
        return INSTANCE.nicknames;
    }
}
