package code.expressionlanguage.opers;

import code.expressionlanguage.OperationsSequence;
import code.util.NatTreeMap;

public abstract class AbstractInstancingOperation extends InvokingOperation {

    public AbstractInstancingOperation(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    final void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        vs_.removeKey(vs_.firstKey());
        getChildren().putAllMap(vs_);
    }
}
