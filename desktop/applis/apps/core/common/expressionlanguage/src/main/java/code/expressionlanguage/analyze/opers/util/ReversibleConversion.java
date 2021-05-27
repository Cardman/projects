package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.functionid.ClassMethodId;

public final class ReversibleConversion {
    private final ClassMethodIdReturn from;
    private final ClassMethodIdReturn to;

    public ReversibleConversion(ClassMethodIdReturn _from,
                                ClassMethodIdReturn _to) {
        from = _from;
        to = _to;
    }

    public ClassMethodIdReturn getFrom() {
        return from;
    }

    public ClassMethodIdReturn getTo() {
        return to;
    }

}
