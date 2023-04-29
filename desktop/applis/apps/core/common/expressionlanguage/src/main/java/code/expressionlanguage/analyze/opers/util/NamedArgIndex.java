package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.opers.NamedArgumentOperation;

public final class NamedArgIndex {
    private final NamedArgumentOperation arg;
    private final int index;

    public NamedArgIndex(NamedArgumentOperation _a, int _i) {
        this.arg = _a;
        this.index = _i;
    }

    public int getIndex() {
        return index;
    }

    public NamedArgumentOperation getArg() {
        return arg;
    }
}
