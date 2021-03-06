package code.maths.litteral;

import code.maths.litteralcom.StrTypes;

public final class AndMbOperation extends QuickMbOperation {

    public AndMbOperation(int _index,
                          int _indexChild, MethodMbOperation _m, MbOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public boolean absorbingStruct() {
        return false;
    }

    @Override
    void calculateChildren() {
        StrTypes vs_ = getOperations().getValues();
        getChildren().addAllEntries(vs_);
    }

}
