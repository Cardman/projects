package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.fwd.opers.AnaArrayInstancingContent;
import code.expressionlanguage.options.KeyWords;
import code.util.core.StringUtil;

public abstract class AbstractArrayInstancingOperation extends InvokingOperation {
    private final AnaArrayInstancingContent arrayInstancingContent;

    protected AbstractArrayInstancingOperation(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        arrayInstancingContent = new AnaArrayInstancingContent(_op.getFctName());
    }
    protected String initAnalyze(AnalyzedPageEl _page) {
        String me_ = getMethodName();
        int off_ = StringUtil.getFirstPrintableCharIndex(me_);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _page);
        setClassName(_page.getAliasObject());
        KeyWords keyWords_ = _page.getKeyWords();
        String new_ = keyWords_.getKeyWordNew();
        return me_.trim().substring(new_.length());
    }

    public final String getMethodName() {
        return arrayInstancingContent.getMethodName();
    }

    public final void setClassName(String _className) {
        arrayInstancingContent.setClassName(_className);
    }

    public AnaArrayInstancingContent getArrayInstancingContent() {
        return arrayInstancingContent;
    }
}
