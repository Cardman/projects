package code.expressionlanguage.opers;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.opers.util.ConstructorId;


public abstract class PrimitiveBoolOperation extends MethodOperation {

    public PrimitiveBoolOperation(int _index,
            ContextEl _importingPage, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_index, _importingPage, _indexChild, _m, _op);
    }

    @Override
    public boolean isOtherConstructorClass() {
        return false;
    }

    @Override
    public ConstructorId getConstId() {
        return null;
    }

    @Override
    public boolean isPossibleInitClass() {
        return false;
    }
    @Override
    public boolean isSuperConstructorCall() {
        return false;
    }

}
