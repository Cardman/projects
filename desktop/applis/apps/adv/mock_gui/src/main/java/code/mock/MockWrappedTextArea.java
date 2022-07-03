package code.mock;

import code.gui.AbsWrappedTextArea;

public final class MockWrappedTextArea extends MockInput implements AbsWrappedTextArea {
    private final StringBuilder tx = new StringBuilder();

    private final int rows;
    private final int cols;
    public MockWrappedTextArea(int _r, int _c) {
        rows = _r;
        cols = _c;
    }

    @Override
    public void setText(String _s) {
        tx.delete(0,tx.length()).append(_s);
    }

    @Override
    public void append(String _s) {
        tx.append(_s);
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public String getText() {
        return tx.toString();
    }

    @Override
    public void setEditable(boolean _b) {
        setEnabled(_b);
    }
}
