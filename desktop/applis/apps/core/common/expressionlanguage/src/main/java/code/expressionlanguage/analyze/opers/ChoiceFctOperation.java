package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.util.StringList;
import code.util.core.StringUtil;

public final class ChoiceFctOperation extends AbsFctOperation {

    public ChoiceFctOperation(int _index, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void preAnalyze(AnalyzedPageEl _page) {
        preAna(_page, false);
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        String methodName_ = getCallFctContent().getMethodName();
        int off_ = StringUtil.getFirstPrintableCharIndex(methodName_);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _page);
        StringList bounds_ = getBounds(clNameAna(_page), _page);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _page);
        lookup(_page, methodName_, bounds_, false);
    }


}
