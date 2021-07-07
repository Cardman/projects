package code.formathtml;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.fwd.Forwards;
import code.formathtml.util.BeanCustLgNames;
import code.formathtml.util.DualConfigurationContext;

public final class AnalyzedTestConfigurationBis {
    private final Configuration configuration;
    private final AnalyzedPageEl analyzing;
    private final BeanCustLgNames adv;
    private final Forwards fwd;
    private final DualConfigurationContext dual;

    public AnalyzedTestConfigurationBis(Forwards _fwd, Configuration _configuration, BeanCustLgNames _standards, DualConfigurationContext _dual, AnalyzedPageEl _page) {
        fwd = _fwd;
        this.configuration = _configuration;
        adv= _standards;
        dual = _dual;
        this.analyzing = _page;
    }

    public Forwards getFwd() {
        return fwd;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public AnalyzedPageEl getAnalyzing() {
        return analyzing;
    }

    public BeanCustLgNames getAdvStandards() {
        return adv;
    }

    public DualConfigurationContext getDual() {
        return dual;
    }

}
