package code.expressionlanguage.fwd;

import code.expressionlanguage.ContextEl;

public interface AbsContextGenerator extends AbsLightContextGenerator {
    ContextEl geneWith(Forwards _f);
}
