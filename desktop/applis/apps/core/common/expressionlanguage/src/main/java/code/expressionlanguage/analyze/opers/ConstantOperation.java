package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.instr.OperationsSequence;

public abstract class ConstantOperation extends LeafOperation {

    private final int len;
    protected ConstantOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        len = _op.getLength();
    }

    public int getLen() {
        return len;
    }
}
