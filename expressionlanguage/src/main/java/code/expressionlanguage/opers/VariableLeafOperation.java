package code.expressionlanguage.opers;

import code.expressionlanguage.OperationsSequence;

public abstract class VariableLeafOperation extends LeafOperation implements AtomicCalculableOperation,DirectCalculableOperation {

    VariableLeafOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
    }

}
