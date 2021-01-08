package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.util.IntTreeMap;

public final class RefShortTernaryOperation extends AbstractRefTernaryOperation {

    public RefShortTernaryOperation(int _index, int _indexChild, MethodOperation _m,
                                    OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculateChildren() {
        IntTreeMap< String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
        setOffsetLocal(0);
    }

}
