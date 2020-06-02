package code.network;

import java.net.Socket;

import code.network.enums.ErrorHostConnectionType;

public final class SocketResults {

    private final Socket socket;

    private final ErrorHostConnectionType error;

    public SocketResults(Socket _socket) {
        socket = _socket;
        error = ErrorHostConnectionType.NOTHING;
    }

    public SocketResults(ErrorHostConnectionType _error) {
        socket = null;
        error = _error;
    }

    public Socket getSocket() {
        return socket;
    }

    public ErrorHostConnectionType getError() {
        return error;
    }

}
