package code.mock;

import code.gui.events.AbsCaretListener;

public final class MockAdvCaret implements AbsCaretListener {
    private int begin = -1;
    private int end = -1;

    @Override
    public void caretUpdate(int _begin, int _end) {
        begin = _begin;
        end = _end;
    }

    public int getBegin() {
        return begin;
    }

    public int getEnd() {
        return end;
    }
}
