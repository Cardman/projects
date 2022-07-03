package code.mock;

import code.gui.initialize.AbstractServerSocket;
import code.gui.initialize.AbstractSocket;

public final class MockServerSocket implements AbstractServerSocket {
    private boolean ok;
    private boolean closed;

    @Override
    public boolean close() {
        closed = true;
        return true;
    }

    @Override
    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean _o) {
        this.ok = _o;
    }

    @Override
    public boolean isClosed() {
        return closed;
    }

    @Override
    public AbstractSocket accept() {
        if (closed) {
            return new MockSocket(true);
        }
        return new MockSocket(false);
    }
}
