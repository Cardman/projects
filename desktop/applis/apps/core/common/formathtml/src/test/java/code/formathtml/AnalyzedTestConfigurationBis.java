package code.formathtml;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.exec.StackCall;
import code.formathtml.util.BeanCustLgNames;
import code.formathtml.util.DualConfigurationContext;

public final class AnalyzedTestConfigurationBis {
    private final Configuration configuration;
    private final AnalyzedPageEl analyzing;
    private final BeanCustLgNames adv;
    private final ContextEl context;
    private final DualConfigurationContext dual;
    private StackCall stackCall;

    public AnalyzedTestConfigurationBis(Configuration _configuration, BeanCustLgNames _standards, ContextEl _context, DualConfigurationContext _dual, AnalyzedPageEl _page) {
        this.configuration = _configuration;
        adv= _standards;
        context = _context;
        dual = _dual;
        dual.setContext(context);
        this.analyzing = _page;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public AnalyzedPageEl getAnalyzing() {
        return analyzing;
    }

    public ContextEl getContext() {
        return context;
    }

    public BeanCustLgNames getAdvStandards() {
        return adv;
    }

    public DualConfigurationContext getDual() {
        return dual;
    }

    public StackCall getStackCall() {
        return stackCall;
    }

    public void setStackCall(StackCall _stackCall) {
        this.stackCall = _stackCall;
    }

}
