package code.expressionlanguage.opers;
import code.expressionlanguage.OperationsSequence;
import code.util.NatTreeMap;

public final class OrOperation extends QuickOperation {

    public OrOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }

    @Override
    boolean absorbingValue() {
        return true;
    }

}
