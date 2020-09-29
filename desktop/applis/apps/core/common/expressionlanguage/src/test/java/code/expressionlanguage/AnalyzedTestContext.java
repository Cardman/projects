package code.expressionlanguage;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.stds.LgNames;

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

    public AnalyzedPageEl getAnalyzing() {
        return analyzing;
    }

    public Forwards getForwards() {
        return forwards;
    }

    public LgNames getStandards() {
        return analyzing.getStandards();
    }
}
