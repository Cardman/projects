package code.expressionlanguage.sample;

import code.expressionlanguage.exec.CommonExecutionInfos;
import code.expressionlanguage.exec.CommonExecutionMetricsInfos;
import code.expressionlanguage.exec.DefaultInitializer;
import code.expressionlanguage.exec.DefaultLockingClass;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LgNames;
import code.maths.montecarlo.DefaultGenerator;

public final class CustLgNames extends LgNames {

    public CustLgNames() {
        super(DefaultGenerator.oneElt());
    }

    @Override
    public void build() {
        buildBase();
    }

    @Override
    public CommonExecutionInfos newContextCommon(Options _opt, Forwards _options) {
        return new CommonExecutionInfos(new ElInterceptorStdCaller(),new CommonExecutionMetricsInfos(_opt.getTabWidth(),_opt.getStack(),_opt.getSeedGene()),this,_options.getClasses(), _options.getCoverage(), new DefaultLockingClass(),new DefaultInitializer());
    }
}
