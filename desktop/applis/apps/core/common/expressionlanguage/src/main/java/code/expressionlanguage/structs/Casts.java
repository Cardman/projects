package code.expressionlanguage.structs;

public final class Casts {
    private final boolean directCast;
    private final boolean expCast;

    public Casts(boolean _directCast, boolean _expCast) {
        this.directCast = _directCast;
        this.expCast = _expCast;
    }

    public boolean isDirectCast() {
        return directCast;
    }

    public boolean isExpCast() {
        return expCast;
    }
}
