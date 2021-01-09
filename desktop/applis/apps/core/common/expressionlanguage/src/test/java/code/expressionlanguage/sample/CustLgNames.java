package code.expressionlanguage.sample;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.SingleContextEl;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.coverage.Coverage;
import code.expressionlanguage.stds.LgNames;
import code.maths.montecarlo.DefaultGenerator;

public final class CustLgNames extends LgNames {

    public CustLgNames() {
        super(new DefaultGenerator());
    }

    @Override
    public void logIssue(String _info) {
    }

    @Override
    public void buildOther() {
    }

    @Override
    public ContextEl newContext(int _tabWidth, int _stack, Coverage _coverage) {
        return new SingleContextEl(new CommonExecutionInfos(_tabWidth,_stack,this,new Classes(new ClassesCommon()),_coverage,new DefaultLockingClass(),new DefaultInitializer()));
    }
}
