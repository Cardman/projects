package code.expressionlanguage.analyze.opers;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.instr.OperationsSequence;

public final class AndOperation extends QuickOperation {

    public AndOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void tryCalculateNode(AnalyzedPageEl _page) {
        tryGetResult(this, false, isOkNum(), _page);
    }


}
