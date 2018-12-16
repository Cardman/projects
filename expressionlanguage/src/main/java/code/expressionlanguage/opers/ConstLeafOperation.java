package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.OperationsSequence;

public abstract class ConstLeafOperation extends LeafOperation {

    ConstLeafOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
    }

    @Override
    public final void tryCalculateNode(Analyzable _conf) {
    }

}
