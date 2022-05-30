package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.util.NameParametersFilter;
import code.expressionlanguage.analyze.opers.util.ScopeFilter;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.util.ClassMethodIdAncestor;
import code.util.StringList;
import code.util.core.StringUtil;

public final class SuperFctOperation extends AbsFctOperation {

    public SuperFctOperation(int _index, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void preAnalyze(AnalyzedPageEl _page) {
        preAna(_page, true);
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        String methodName_ = getCallFctContent().getMethodName();
        int off_ = StringUtil.getFirstPrintableCharIndex(methodName_);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _page);
        AnaClassArgumentMatching clCur_ = getPreviousResultClass(_page);
        String className_ = clNameAna(_page);
        StringList bounds_ = getBounds(className_, _page);
        checkOwner(_page, clCur_, className_);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _page);
        String trimMeth_ = delta(methodName_);
        ClassMethodIdAncestor feed_ = id(trimMeth_);
        NameParametersFilter name_ = buildFilter(_page);
        if (!name_.getParameterFilterErr().isEmpty()) {
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        if (isTrueFalseKeyWord(trimMeth_, _page)) {
            trueFalse(_page,trimMeth_,true,feed_,bounds_,name_);
            return;
        }
        std(_page,trimMeth_, bounds_,name_, new ScopeFilter(feed_, true, true, isLvalue(), true, _page.getGlobalClass()));
    }


}
