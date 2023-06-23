package code.expressionlanguage.fwd;

import code.expressionlanguage.ContextEl;

public interface AbsContextGenerator {
    ContextEl geneWith(Forwards _f);
    ContextEl gene(Forwards _f);
}
