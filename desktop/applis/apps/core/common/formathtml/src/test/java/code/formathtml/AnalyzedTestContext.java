package code.formathtml;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.fwd.Forwards;

public final class AnalyzedTestContext {
    private final ContextEl context;
    private final AnalyzedPageEl analyzing;
    private final Forwards forwards;

    public AnalyzedTestContext(ContextEl context, AnalyzedPageEl analyzing, Forwards _forwards) {
        this.context = context;
        this.analyzing = analyzing;
        forwards = _forwards;
    }

    public ContextEl getContext() {
        return context;
    }

    public Forwards getForwards() {
        return forwards;
    }

    public AnalyzedPageEl getAnalyzing() {
        return analyzing;
    }

}
