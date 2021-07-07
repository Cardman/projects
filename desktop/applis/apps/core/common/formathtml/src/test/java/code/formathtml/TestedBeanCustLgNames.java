package code.formathtml;

import code.expressionlanguage.ContextEl;
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
    public ContextEl newContext(Options _opt,Forwards _options) {
        return new TestedContextEl(new CommonExecutionInfos(_opt.getTabWidth(),_opt.getStack(),this,_options.getClasses(), _options.getCoverage(), new DefaultLockingClass(),new DefaultInitializer()));
    }
}
