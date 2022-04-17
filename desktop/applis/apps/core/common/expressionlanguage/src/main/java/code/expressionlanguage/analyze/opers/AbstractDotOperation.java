package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.util.CustList;

public abstract class AbstractDotOperation extends MethodOperation {
    protected AbstractDotOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        setResultClass(AnaClassArgumentMatching.copy(chidren_.last().getResultClass(), _page.getPrimitiveTypes()));
    }
}
