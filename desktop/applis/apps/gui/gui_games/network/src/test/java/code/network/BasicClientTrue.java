package code.network;

import code.gui.initialize.AbstractSocket;

public final class BasicClientTrue extends BasicClientAbs {
    public BasicClientTrue(AbstractSocket _socket) {
        super(_socket);
    }

    @Override
    protected boolean iterate(AbstractSocket _socket, String _input) {
        return true;
    }
}
