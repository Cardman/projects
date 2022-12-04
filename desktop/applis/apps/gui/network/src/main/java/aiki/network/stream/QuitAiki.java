package aiki.network.stream;


public final class QuitAiki extends PlayerActionGameAiki {

    private boolean closing;

    private boolean server;

    public boolean isClosing() {
        return closing;
    }

    public void setClosing(boolean _closing) {
        closing = _closing;
    }

    public boolean isServer() {
        return server;
    }

    public void setServer(boolean _server) {
        server = _server;
    }
}
