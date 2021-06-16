package aiki.gui.threads;

import aiki.db.PerCent;
import code.threads.AbstractAtomicInteger;

public final class PerCentIncr implements PerCent {
    private AbstractAtomicInteger val;
    public PerCentIncr(AbstractAtomicInteger _prog) {
        val = _prog;
    }
    @Override
    public int getPercent() {
        return val.get();
    }

    @Override
    public void setPercent(int _p) {
        val.set(_p);
    }

    @Override
    public void addPercent(int _p) {
        val.addAndGet(_p);
    }
}
