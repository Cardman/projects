package code.formathtml;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.fwd.Forwards;
import code.formathtml.util.BeanLgNames;

public final class AnalyzedTestContext {
    private final ContextEl context;
    private final AnalyzedPageEl analyzing;
    private final Forwards forwards;
    private final BeanLgNames stds;

    public AnalyzedTestContext(ContextEl context, AnalyzedPageEl analyzing, Forwards _forwards, BeanLgNames _standards) {
        this.context = context;
        this.analyzing = analyzing;
        stds = _standards;
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

    public BeanLgNames getStds() {
        return stds;
    }
}
