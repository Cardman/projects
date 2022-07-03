package code.mock;

import code.gui.initialize.AbstractBufferedReader;
import code.gui.initialize.AbstractSocket;

public final class MockSocket implements AbstractSocket {
    private boolean cl;
    private final boolean ko;

    public MockSocket(boolean _k) {
        this.ko = _k;
    }

    @Override
    public AbstractBufferedReader getInput() {
        return new MockBufferedReader();
    }

    @Override
    public String println(String _st) {
        if (cl) {
            return "";
        }
        return _st+"\n";
    }

    @Override
    public void close() {
        cl = true;
    }

    @Override
    public boolean isKo() {
        return ko;
    }
}
