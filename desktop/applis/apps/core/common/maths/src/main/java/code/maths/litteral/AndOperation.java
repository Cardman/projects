package code.maths.litteral;

public final class AndOperation extends QuickOperation {

    public AndOperation(int _index,
                        int _indexChild, MethodOperation _m, OperationsSequence _op) {
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
