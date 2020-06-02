package code.expressionlanguage.opers;

import code.expressionlanguage.instr.OperationsSequence;

public final class SafeDotOperation extends AbstractDotOperation {
    public SafeDotOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }
}
