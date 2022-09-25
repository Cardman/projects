package aiki.tsts;

import aiki.db.PerCent;

public final class TstsPerCentImpl implements PerCent {
    private int percent;

    @Override
    public int getPercent() {
        return percent;
    }

    @Override
    public void setPercent(int _p) {
        percent = _p;
    }

    @Override
    public void addPercent(int _p) {
        percent+=_p;
    }
}