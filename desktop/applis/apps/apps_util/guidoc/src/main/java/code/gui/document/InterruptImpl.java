package code.gui.document;

import code.formathtml.Interrupt;

public final class InterruptImpl implements Interrupt {
    private boolean value;
    @Override
    public void set(boolean _b) {
        value=_b;
    }

    @Override
    public boolean get() {
        return value;
    }
}
