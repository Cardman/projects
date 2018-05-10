package code.expressionlanguage.opers;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.opers.util.ConstructorId;


public abstract class PrimitiveBoolOperation extends MethodOperation {

    public PrimitiveBoolOperation(int _index,
            int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public final boolean isOtherConstructorClass() {
        return false;
    }

    @Override
    public final ConstructorId getConstId() {
        return null;
    }

    @Override
    public final boolean isSuperConstructorCall() {
        return false;
    }

}
