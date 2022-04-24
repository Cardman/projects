package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;

public final class DefaultOperation extends AbstractUnaryOperation {

    private final int offset;

    public DefaultOperation(int _index, int _indexChild, MethodOperation _m,
                            OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        offset = _op.getOffset();
    }

    @Override
    public void analyzeUnary(AnalyzedPageEl _page) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+offset, _page);
        OperationNode child_ = getFirstChild();
        AnaClassArgumentMatching res_ = child_.getResultClass();
        if (res_.isVariable()) {
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        setResultClass(AnaClassArgumentMatching.copy(res_, _page.getPrimitiveTypes()));
    }

    public int getOffset() {
        return offset;
    }

}
