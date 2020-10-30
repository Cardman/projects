package code.formathtml.nat;

import code.bean.nat.BeanNatLgNames;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.fwd.Forwards;
import code.formathtml.util.DualConfigurationContext;

class NativeOtherAnalyzedTestContext {
    private final ContextEl context;
    private final AnalyzedPageEl analyzing;
    private final Forwards forwards;
    private final BeanTestNatLgNames stds;
    private DualConfigurationContext dual = new DualConfigurationContext();

    NativeOtherAnalyzedTestContext(ContextEl _context, AnalyzedPageEl _analyzing, Forwards _forwards, BeanTestNatLgNames _standards) {
        this.context = _context;
        this.analyzing = _analyzing;
        stds = _standards;
        forwards = _forwards;
    }

    ContextEl getContext() {
        return context;
    }

    Forwards getForwards() {
        return forwards;
    }

    AnalyzedPageEl getAnalyzing() {
        return analyzing;
    }

    BeanTestNatLgNames getStds() {
        return stds;
    }

    public DualConfigurationContext getDual() {
        return dual;
    }
}
