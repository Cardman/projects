package code.bean.nat;

public final class NaNbSt implements NatDisplayableStruct {
    private final long nb;

    public NaNbSt(long _v) {
        this.nb = _v;
    }

    @Override
    public boolean sameReference(NaSt _other) {
        if (!(_other instanceof NaNbSt)) {
            return false;
        }
        return longStruct() == ((NaNbSt)_other).longStruct();
    }

    @Override
    public NaStSt getDisplayedString() {
        return new NaStSt(Long.toString(nb));
    }

    public long longStruct() {
        return nb;
    }
    public int intStruct() {
        return (int) nb;
    }
}
