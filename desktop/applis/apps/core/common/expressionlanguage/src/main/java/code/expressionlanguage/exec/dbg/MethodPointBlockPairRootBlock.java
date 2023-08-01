package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.exec.util.ExecFormattedRootBlock;

public final class MethodPointBlockPairRootBlock {
    private final MethodPointBlockPair id;
    private final ExecFormattedRootBlock value;

    public MethodPointBlockPairRootBlock(MethodPointBlockPair _i, ExecFormattedRootBlock _v) {
        this.id = _i;
        this.value = _v;
    }

    public MethodPointBlockPair getId() {
        return id;
    }

    public ExecFormattedRootBlock getValue() {
        return value;
    }
}
