package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.functionid.ClassMethodId;

public final class ReversibleConversion {
    private final ClassMethodId from;
    private final ClassMethodId to;

    public ReversibleConversion(ClassMethodId _from, ClassMethodId _to) {
        from = _from;
        to = _to;
    }

    public ClassMethodId getFrom() {
        return from;
    }

    public ClassMethodId getTo() {
        return to;
    }
}
