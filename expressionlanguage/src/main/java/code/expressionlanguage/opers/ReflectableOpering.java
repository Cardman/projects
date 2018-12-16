package code.expressionlanguage.opers;

import code.expressionlanguage.OperationsSequence;

public abstract class ReflectableOpering extends MethodOperation implements AtomicCalculableOperation, DirectCalculableOperation {

    public ReflectableOpering(int _index, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

}
