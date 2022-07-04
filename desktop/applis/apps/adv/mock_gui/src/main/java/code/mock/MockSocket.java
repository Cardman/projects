package code.mock;

import code.gui.initialize.AbstractBufferedReader;
import code.gui.initialize.AbstractSocket;
import code.util.StringList;

public final class MockSocket implements AbstractSocket {
    private boolean cl;
    private final boolean ko;
    private final StringList instr = new StringList();
    private final StringList output = new StringList();

    public MockSocket(boolean _k) {
        this.ko = _k;
    }

    @Override
    public AbstractBufferedReader getInput() {
        return new MockBufferedReader(getInstr());
    }

    @Override
    public String println(String _st) {
        if (cl) {
            getOutput().clear();
            return "";
        }
        getOutput().add(_st);
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

    public StringList getInstr() {
        return instr;
    }

    public StringList getOutput() {
        return output;
    }
}
