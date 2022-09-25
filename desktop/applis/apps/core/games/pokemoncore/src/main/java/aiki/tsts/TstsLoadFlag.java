package aiki.tsts;

public final class TstsLoadFlag {

    private final TstsPerCentImpl perCent;

    private final int max;

    public TstsLoadFlag(TstsPerCentImpl _p, int _m) {
        this.perCent = _p;
        this.max = _m;
    }

    public int getMax() {
        return max;
    }

    public TstsPerCentImpl getPerCent() {
        return perCent;
    }
}
