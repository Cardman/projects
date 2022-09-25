package aiki.tsts;

import aiki.db.LoadFlag;
import aiki.db.PerCent;

public abstract class TstsLoadFlagImpl implements LoadFlag {

    private final TstsLoadFlag loadFlag;

    protected TstsLoadFlagImpl(int _max) {
        this.loadFlag = new TstsLoadFlag(new TstsPerCentImpl(),_max);
    }

    @Override
    public boolean get() {
        return loadFlag.getPerCent().getPercent()<loadFlag.getMax();
    }

    @Override
    public void set(boolean _b) {
        getLoadFlag().setPercent(getLoadFlag().getPercent());
    }
    public PerCent getLoadFlag() {
        return loadFlag.getPerCent();
    }
}
