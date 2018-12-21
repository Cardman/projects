package code.expressionlanguage.opers;

import code.expressionlanguage.instr.OperationsSequence;

public abstract class ReflectableInvokingOperation extends InvokingOperation {

    ReflectableInvokingOperation(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

}
