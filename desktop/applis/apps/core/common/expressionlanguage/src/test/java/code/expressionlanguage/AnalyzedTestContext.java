package code.expressionlanguage;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.stds.LgNames;

public final class AnalyzedTestContext {
    private final ContextEl context;
    private final AnalyzedPageEl analyzing;

    public AnalyzedTestContext(ContextEl context, AnalyzedPageEl analyzing) {
        this.context = context;
        this.analyzing = analyzing;
    }

    public ContextEl getContext() {
        return context;
    }

    public AnalyzedPageEl getAnalyzing() {
        return analyzing;
    }

    public LgNames getStandards() {
        return analyzing.getStandards();
    }
}
