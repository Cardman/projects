package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.exec.util.ExecFormattedRootBlock;

public final class MethodPointBlockPairRootBlock {
    private final MethodPointBlockPair id;
    private final int preference;
    private final ExecFormattedRootBlock value;

    public MethodPointBlockPairRootBlock(MethodPointBlockPair _i, int _pref, ExecFormattedRootBlock _v) {
        this.id = _i;
        this.preference = _pref;
        this.value = _v;
    }

    public MethodPointBlockPair getId() {
        return id;
    }

    public int getPreference() {
        return preference;
    }

    public ExecFormattedRootBlock getValue() {
        return value;
    }
}
