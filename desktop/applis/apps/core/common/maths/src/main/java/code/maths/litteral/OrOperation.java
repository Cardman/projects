package code.maths.litteral;
import code.util.*;
import code.util.StringMap;

public final class OrOperation extends QuickOperation {

    public OrOperation(String _el, int _index, StringMap<String> _importingPage,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_el, _index, _importingPage, _indexChild, _m, _op);
    }

    @Override
    public boolean absorbingStruct() {
        return true;
    }

    @Override
    void calculateChildren() {
        IntTreeMap< String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }

}
