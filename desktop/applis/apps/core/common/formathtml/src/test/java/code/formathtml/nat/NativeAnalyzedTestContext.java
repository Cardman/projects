package code.formathtml.nat;

import code.bean.nat.BeanNatLgNames;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.fwd.Forwards;
import code.formathtml.util.DualConfigurationContext;

class NativeAnalyzedTestContext {
    private final AnalyzedPageEl analyzing;
    private final Forwards forwards;
    private final BeanNatLgNames stds;
    private DualConfigurationContext dual = new DualConfigurationContext();

    NativeAnalyzedTestContext(AnalyzedPageEl _analyzing, Forwards _forwards, BeanNatLgNames _standards) {
        this.analyzing = _analyzing;
        stds = _standards;
        forwards = _forwards;
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
