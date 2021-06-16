package aiki.gui.threads;

import aiki.db.LoadFlag;
import code.threads.AbstractAtomicBoolean;

public final class LoadFlagImpl implements LoadFlag {
    private AbstractAtomicBoolean val;
    public LoadFlagImpl(AbstractAtomicBoolean _val) {
        val = _val;
    }
    @Override
    public void set(boolean _b) {
        val.set(_b);
    }

    @Override
    public boolean get() {
        return val.get();
    }
}
