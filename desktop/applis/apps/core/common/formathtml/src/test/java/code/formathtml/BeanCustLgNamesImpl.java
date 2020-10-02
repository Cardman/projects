package code.formathtml;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.SingleContextEl;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.coverage.Coverage;
import code.expressionlanguage.options.KeyWords;
import code.formathtml.errors.RendAnalysisMessages;
import code.formathtml.errors.RendKeyWords;
import code.formathtml.util.BeanCustLgNames;
import code.maths.montecarlo.DefaultGenerator;
import code.sml.Element;

public final class BeanCustLgNamesImpl extends BeanCustLgNames {
    public BeanCustLgNamesImpl() {
        super(new DefaultGenerator(), new DefaultInitializer());
    }

    @Override
    public void buildAliases(Element _elt, String _lg, RendKeyWords _rkw, KeyWords _kw, RendAnalysisMessages _rMess, AnalysisMessages _mess) {
        //impl
    }

    @Override
    public ContextEl newContext(int _tabWidth, int _stack, Coverage _coverage, Initializer _init) {
        return new SingleContextEl(new CommonExecutionInfos(_tabWidth,_stack,this,new Classes(new ClassesCommon()),_coverage,new DefaultLockingClass(),_init));
    }
}
