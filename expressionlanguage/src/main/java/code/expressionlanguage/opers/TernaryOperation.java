package code.expressionlanguage.opers;

import code.expressionlanguage.instr.OperationsSequence;
import code.util.NatTreeMap;
import code.util.StringList;

public final class TernaryOperation extends AbstractTernaryOperation {

    public TernaryOperation(int _index, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        vs_.removeKey(vs_.firstKey());
        getChildren().putAllMap(vs_);
        String fct_ = getOperations().getFctName();
        setOffsetLocal(StringList.getFirstPrintableCharIndex(fct_));
    }

}
