package code.network;

import code.gui.initialize.AbstractSocket;
import code.network.enums.ErrorHostConnectionType;

public final class SocketResults {

    private final AbstractSocket socket;

    private final ErrorHostConnectionType error;

    public SocketResults(AbstractSocket _socket) {
        socket = _socket;
        error = ErrorHostConnectionType.NOTHING;
    }

    public SocketResults(ErrorHostConnectionType _error) {
        socket = null;
        error = _error;
    }

    public AbstractSocket getSocket() {
        return socket;
    }

    public ErrorHostConnectionType getError() {
        return error;
    }

}
