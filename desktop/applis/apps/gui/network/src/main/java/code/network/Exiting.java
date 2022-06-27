package code.network;


public final class Exiting {

    private boolean forced;

    private boolean closing;

    private boolean server;

    private boolean tooManyPlayers;

    private boolean busy;

    public boolean isForced() {
        return forced;
    }

    public void setForced(boolean _forced) {
        forced = _forced;
    }

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

    public boolean isTooManyPlayers() {
        return tooManyPlayers;
    }

    public void setTooManyPlayers(boolean _tooManyPlayers) {
        tooManyPlayers = _tooManyPlayers;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean _busy) {
        busy = _busy;
    }
}
