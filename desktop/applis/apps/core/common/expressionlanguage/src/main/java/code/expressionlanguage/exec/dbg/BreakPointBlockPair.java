package code.expressionlanguage.exec.dbg;

public final class BreakPointBlockPair {
    private final BreakPointBlockKey key;
    private final BreakPoint value;

    public BreakPointBlockPair(BreakPointBlockKey _k, BreakPoint _v) {
        this.key = _k;
        this.value = _v;
    }

    public BreakPointBlockKey getKey() {
        return key;
    }

    public BreakPoint getValue() {
        return value;
    }
}
