package aiki.gui.threads;

import aiki.db.LoadFlag;

import java.util.concurrent.atomic.AtomicBoolean;

public final class LoadFlagImpl implements LoadFlag {
    private AtomicBoolean val = new AtomicBoolean();
    @Override
    public void set(boolean _b) {
        val.set(_b);
    }

    @Override
    public boolean get() {
        return val.get();
    }
}
