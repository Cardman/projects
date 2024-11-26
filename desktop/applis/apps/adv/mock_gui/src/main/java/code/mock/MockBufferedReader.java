package code.mock;

import code.util.StringList;

public final class MockBufferedReader {
    private int index;
    private final StringList instrs;
    public MockBufferedReader() {
        this(new StringList());
    }
    public MockBufferedReader(StringList _is) {
        instrs = _is;
    }

    public String readLine() {
        if (instrs.isValidIndex(index)) {
            String i_ = instrs.get(index);
            incr();
            return i_;
        }
        return null;
    }

    public void incr() {
        index++;
    }

    public StringList getInstrs() {
        return instrs;
    }
}
