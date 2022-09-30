package code.formathtml;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.options.Options;
import code.formathtml.util.BeanCustLgNames;
import code.maths.montecarlo.AbstractGenerator;

public abstract class TestedBeanCustLgNames extends BeanCustLgNames {
    protected TestedBeanCustLgNames(AbstractGenerator _gene) {
        super(_gene);
    }
    @Override
    public void build() {
        buildBase();
        buildOther();
    }

    @Override
    public void logIssue(String _info, ReportedMessages _rep) {
        _rep.notAllEmptyErrors();
    }

    @Override
    public ContextEl newContext(Options _opt,Forwards _options) {
        return new TestedContextEl(new CommonExecutionInfos(new TestedRenderInterceptorStdCaller(),new CommonExecutionMetricsInfos(_opt.getTabWidth(),_opt.getStack(),_opt.getSeedGene()),this,_options.getClasses(), _options.getCoverage(), new DefaultLockingClass(),new DefaultInitializer()));
    }
}
