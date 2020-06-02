package code.maths.litteral;
import code.util.StringMap;


public abstract class PrimitiveBoolOperation extends MethodOperation {

    public PrimitiveBoolOperation(String _el, int _index,
            StringMap<String> _importingPage, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_el, _index, _importingPage, _indexChild, _m, _op);
    }
}
