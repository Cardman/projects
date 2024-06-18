package code.network;

import code.gui.initialize.AbstractSocket;

public final class BasicClientFalse extends BasicClientAbs {
    public BasicClientFalse(AbstractSocket _socket) {
        super(_socket);
    }

    @Override
    protected boolean iterate(AbstractSocket _socket, String _input) {
        return false;
    }
}
