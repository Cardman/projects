package code.maths.litteral;

public final class OrOperation extends QuickOperation {

    public OrOperation(int _index,
                       int _indexChild, MethodOperation _m, OperationsSequence _op) {
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
