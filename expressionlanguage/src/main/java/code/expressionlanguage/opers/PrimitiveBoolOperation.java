package code.expressionlanguage.opers;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OperationsSequence;


public abstract class PrimitiveBoolOperation extends MethodOperation {

    public PrimitiveBoolOperation(String _el, int _index,
            ContextEl _importingPage, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_el, _index, _importingPage, _indexChild, _m, _op);
    }
}
