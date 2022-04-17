package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.instr.OperationsSequence;

public final class ShortTernaryOperation extends AbstractTernaryOperation implements ImplementChoice {

    public ShortTernaryOperation(int _index, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        setOffsetLocal(0);
    }

}
