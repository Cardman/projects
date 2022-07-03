package code.mock;

import code.gui.initialize.AbstractBufferedReader;
import code.util.StringList;

public final class MockBufferedReader implements AbstractBufferedReader {
    private int index;
    private final StringList instrs = new StringList();
    @Override
    public String readLine() {
        if (instrs.isValidIndex(index)) {
            String i_ = instrs.get(index);
            index++;
            return i_;
        }
        return null;
    }

    public StringList getInstrs() {
        return instrs;
    }
}
