package code.maths.litteral;

import code.maths.litteralcom.StrTypes;

public final class OrMbOperation extends QuickMbOperation {

    public OrMbOperation(int _index,
                         int _indexChild, MethodMbOperation _m, MbOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public boolean absorbingStruct() {
        return true;
    }

    @Override
    void calculateChildren() {
        StrTypes vs_ = getOperations().getValues();
        getChildren().addAllEntries(vs_);
    }

}
