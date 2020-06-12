package code.expressionlanguage.opers;

import code.expressionlanguage.instr.OperationsSequence;

public abstract class LeafOperation extends OperationNode {

    protected LeafOperation(int _indexInEl, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
    }

    @Override
    public final OperationNode getFirstChild() {
        return null;
    }
}
