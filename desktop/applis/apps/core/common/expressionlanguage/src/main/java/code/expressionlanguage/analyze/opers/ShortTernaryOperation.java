package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.maths.litteralcom.StrTypes;

public final class ShortTernaryOperation extends AbstractTernaryOperation {

    public ShortTernaryOperation(int _index, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculateChildren() {
        StrTypes vs_ = getOperations().getValues();
        getChildren().addAllEntries(vs_);
        setOffsetLocal(0);
    }

}
