package code.formathtml;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.coverage.Coverage;
import code.formathtml.util.BeanCustLgNames;
import code.maths.montecarlo.AbstractGenerator;

public abstract class TestedBeanCustLgNames extends BeanCustLgNames {
    protected TestedBeanCustLgNames(AbstractGenerator _gene) {
        super(_gene);
    }

    @Override
    public ContextEl newContext(int _tabWidth, int _stack, Coverage _coverage) {
        return new TestedContextEl(new CommonExecutionInfos(_tabWidth,_stack,this,new Classes(new ClassesCommon()),_coverage,new DefaultLockingClass(),new DefaultInitializer()));
    }

}
