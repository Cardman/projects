package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.instr.OperationsSequence;
import code.util.CustList;

public final class DotOperation extends AbstractDotOperation {

    public DotOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void quickCalculate(AnalyzedPageEl _page) {
        DotOperation.setArg(this, _page);
    }

    public static void setArg(MethodOperation _op, AnalyzedPageEl _page) {
        CustList<OperationNode> chidren_ = _op.getChildrenNodes();
        _op.setSimpleArgumentAna(chidren_.last().getArgument(), _page);
    }
}
