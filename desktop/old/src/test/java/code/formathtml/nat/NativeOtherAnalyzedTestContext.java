package code.formathtml.nat;

import code.bean.nat.BeanNatLgNames;
import code.bean.nat.analyze.blocks.NatAnalyzedCode;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.fwd.Forwards;
import code.formathtml.util.DualConfigurationContext;

class NativeOtherAnalyzedTestContext {
    private final NatAnalyzedCode analyzing;
    private final Forwards forwards;
    private final BeanTestNatLgNames stds;
    private DualConfigurationContext dual = new DualConfigurationContext();

    NativeOtherAnalyzedTestContext(NatAnalyzedCode _analyzing, Forwards _forwards, BeanTestNatLgNames _standards) {
        this.analyzing = _analyzing;
        stds = _standards;
        forwards = _forwards;
    }

    Forwards getForwards() {
        return forwards;
    }

    NatAnalyzedCode getAnalyzing() {
        return analyzing;
    }

    BeanTestNatLgNames getStds() {
        return stds;
    }

    public DualConfigurationContext getDual() {
        return dual;
    }
}
