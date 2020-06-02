package code.gui.document;

import code.formathtml.Interrupt;

import java.util.concurrent.atomic.AtomicBoolean;

public final class InterruptImpl implements Interrupt {
    private AtomicBoolean value = new AtomicBoolean();
    @Override
    public void set(boolean _b) {
        value.set(_b);
    }

    @Override
    public boolean get() {
        return value.get();
    }
}
