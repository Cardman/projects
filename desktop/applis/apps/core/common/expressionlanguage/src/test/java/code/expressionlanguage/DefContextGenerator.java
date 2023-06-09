package code.expressionlanguage;

import code.expressionlanguage.fwd.AbsContextGenerator;
import code.expressionlanguage.fwd.Forwards;

public final class DefContextGenerator implements AbsContextGenerator {
    @Override
    public ContextEl gene(Forwards _f) {
        return new SingleContextEl(_f.getGenerator().newContextCommon(_f.getOptions(),_f));
    }
}
