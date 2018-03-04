package aiki.network.stream;
import code.network.Exiting;
import code.util.annot.RwXml;

@RwXml
public final class Bye extends Exiting {

    private boolean forced;

    private boolean closing;

    private boolean server;

    private boolean tooManyPlayers;

    private boolean busy;

    @Override
    public boolean isForced() {
        return forced;
    }

    public void setForced(boolean _forced) {
        forced = _forced;
    }

    @Override
    public boolean isClosing() {
        return closing;
    }

    public void setClosing(boolean _closing) {
        closing = _closing;
    }

    @Override
    public boolean isServer() {
        return server;
    }

    public void setServer(boolean _server) {
        server = _server;
    }

    @Override
    public boolean isTooManyPlayers() {
        return tooManyPlayers;
    }

    public void setTooManyPlayers(boolean _tooManyPlayers) {
        tooManyPlayers = _tooManyPlayers;
    }

    @Override
    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean _busy) {
        busy = _busy;
    }
}
