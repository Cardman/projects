package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.instr.OperationsSequence;

public final class SafeDotOperation extends AbstractDotOperation {
    private final int opOff;
    public SafeDotOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        opOff = _op.getOperators().firstKey();
    }

    public int getOpOff() {
        return opOff;
    }
}
