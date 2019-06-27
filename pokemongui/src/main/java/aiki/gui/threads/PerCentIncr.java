package aiki.gui.threads;

import aiki.db.PerCent;

import java.util.concurrent.atomic.AtomicInteger;

public final class PerCentIncr implements PerCent {
    private AtomicInteger val = new AtomicInteger();
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
