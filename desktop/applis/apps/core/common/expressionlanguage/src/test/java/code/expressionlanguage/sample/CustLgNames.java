package code.expressionlanguage.sample;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.SingleContextEl;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.exec.CommonExecutionInfos;
import code.expressionlanguage.exec.CommonExecutionMetricsInfos;
import code.expressionlanguage.exec.DefaultInitializer;
import code.expressionlanguage.exec.DefaultLockingClass;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.LoggableLgNames;
import code.maths.montecarlo.DefaultGenerator;

public final class CustLgNames extends LgNames implements LoggableLgNames {

    public CustLgNames() {
        super(new DefaultGenerator());
    }

    @Override
    public void build() {
        buildBase();
    }

    @Override
    public void logIssue(String _info, ReportedMessages _rep) {
        _rep.notAllEmptyErrors();
    }

    @Override
    public ContextEl newContext(Options _opt,Forwards _options) {
        return new SingleContextEl(new CommonExecutionInfos(new ElInterceptorStdCaller(),new CommonExecutionMetricsInfos(_opt.getTabWidth(),_opt.getStack(),_opt.getSeedGene()),this,_options.getClasses(), _options.getCoverage(), new DefaultLockingClass(),new DefaultInitializer()));
    }

}
