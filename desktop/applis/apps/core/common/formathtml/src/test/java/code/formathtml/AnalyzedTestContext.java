package code.formathtml;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.fwd.Forwards;
import code.formathtml.util.BeanCustLgNames;
import code.formathtml.util.DualConfigurationContext;

public final class AnalyzedTestContext {
    private final ContextEl context;
    private final AnalyzedPageEl analyzing;
    private final Forwards forwards;
    private final BeanCustLgNames stds;
    private DualConfigurationContext dual = new DualConfigurationContext();

    public AnalyzedTestContext(ContextEl _context, AnalyzedPageEl _analyzing, Forwards _forwards, BeanCustLgNames _standards) {
        this.context = _context;
        this.analyzing = _analyzing;
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

    public BeanCustLgNames getStds() {
        return stds;
    }

    public DualConfigurationContext getDual() {
        return dual;
    }
}
