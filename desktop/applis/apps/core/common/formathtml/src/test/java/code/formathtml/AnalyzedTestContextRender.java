package code.formathtml;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.options.Options;
import code.formathtml.util.BeanCustLgNames;
import code.formathtml.util.DualConfigurationContext;

public final class AnalyzedTestContextRender {
    private final AnalyzedPageEl analyzing;
    private final Forwards forwards;
    private final BeanCustLgNames stds;
    private DualConfigurationContext dual = new DualConfigurationContext();
    private final Options opt;

    public AnalyzedTestContextRender(Options _opt, AnalyzedPageEl _analyzing, Forwards _forwards, BeanCustLgNames _standards) {
        opt = _opt;
        this.analyzing = _analyzing;
        stds = _standards;
        forwards = _forwards;
    }

    public Options getOpt() {
        return opt;
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
