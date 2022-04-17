package code.maths.litteral;

public final class OrMbOperation extends QuickMbOperation {

    public OrMbOperation(int _index,
                         int _indexChild, MethodMbOperation _m, MbOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public boolean absorbingStruct() {
        return true;
    }


}
