package code.formathtml;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;

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

}
