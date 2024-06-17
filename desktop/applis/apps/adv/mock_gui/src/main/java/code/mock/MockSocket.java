package code.mock;

import code.gui.initialize.AbstractBufferedReader;
import code.gui.initialize.AbstractSocket;
import code.util.StringList;

public final class MockSocket implements AbstractSocket {
    private boolean cl;
    private final boolean ko;
    private final StringList instr = new StringList();
    private final StringList output = new StringList();
    private String inet = "";
    private String local = "";
    private String localSocket = "";
    private String remoteSocket = "";

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

    @Override
    public String inetAddress() {
        return inet;
    }
    public void inetAddress(String _i) {
        inet = _i;
    }

    @Override
    public String localAddress() {
        return local;
    }
    public void localAddress(String _i) {
        local = _i;
    }
    @Override
    public String localSocketAddress() {
        return localSocket;
    }
    public void localSocketAddress(String _i) {
        localSocket = _i;
    }
    @Override
    public String remoteSocketAddress() {
        return remoteSocket;
    }
    public void remoteSocketAddress(String _i) {
        remoteSocket = _i;
    }
    public StringList getInstr() {
        return instr;
    }

    public StringList getOutput() {
        return output;
    }
}
