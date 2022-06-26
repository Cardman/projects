package code.network;

import code.gui.initialize.AbstractSocket;
import code.util.IntMap;
import code.util.IntTreeMap;
import code.util.core.BoolVal;

public final class NetCommon {

    private final IntMap<AbstractSocket> sockets =new IntMap<AbstractSocket>();
    private final IntTreeMap< Byte> placesPlayers = new IntTreeMap< Byte>();
    private final IntMap<BoolVal> readyPlayers = new IntMap<BoolVal>();

    private final IntMap<String> nicknames =new IntMap<String>();

    private final IntMap<BasicServer> connectionsServer =new IntMap<BasicServer>();

    public IntMap<AbstractSocket> getSockets() {
        return sockets;
    }

    public IntMap<BoolVal> getReadyPlayers() {
        return readyPlayers;
    }

    public IntMap<String> getNicknames() {
        return nicknames;
    }

    public IntTreeMap<Byte> getPlacesPlayers() {
        return placesPlayers;
    }

    public IntMap<BasicServer> getConnectionsServer() {
        return connectionsServer;
    }
}
