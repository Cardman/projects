package code.formathtml.nat;

import code.bean.nat.BeanNatLgNames;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.fwd.Forwards;
import code.formathtml.util.DualConfigurationContext;

class NativeAnalyzedTestContext {
    private final ContextEl context;
    private final AnalyzedPageEl analyzing;
    private final Forwards forwards;
    private final BeanNatLgNames stds;
    private DualConfigurationContext dual = new DualConfigurationContext();

    NativeAnalyzedTestContext(ContextEl context, AnalyzedPageEl analyzing, Forwards _forwards, BeanNatLgNames _standards) {
        this.context = context;
        this.analyzing = analyzing;
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

    BeanNatLgNames getStds() {
        return stds;
    }

    public DualConfigurationContext getDual() {
        return dual;
    }
}
