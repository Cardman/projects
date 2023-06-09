package code.formathtml;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.fwd.AbsContextGenerator;
import code.expressionlanguage.fwd.Forwards;

public final class DefRenderContextGenerator implements AbsContextGenerator {
    @Override
    public ContextEl gene(Forwards _f) {
        return new TestedContextEl(_f.getGenerator().newContextCommon(_f.getOptions(),_f));
    }
}
