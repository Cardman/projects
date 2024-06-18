package code.network;

import code.gui.initialize.AbstractSocket;

public final class BasicServerSample extends BasicServer {
    public BasicServerSample(AbstractSocket _socket, NetGroupFrame _net) {
        super(_socket, _net);
    }

    @Override
    public void loopServer(String _input) {
        getSocket().inetAddress();
    }
}
