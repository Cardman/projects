package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.maths.litteralcom.StrTypes;

public final class DefaultOperation extends AbstractUnaryOperation {

    private int offset;
    private final int delta;
    public DefaultOperation(int _index, int _indexChild, MethodOperation _m,
                            OperationsSequence _op, int _delta) {
        super(_index, _indexChild, _m, _op);
        delta = _delta;
    }

    @Override
    void calculateChildren() {
        StrTypes vs_ = getOperations().getValues();
        offset = vs_.firstKey();
        vs_.remove(0);
        getChildren().addAllEntries(vs_);
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

    public int getDelta() {
        return delta;
    }
}
