package code.maths.litteral;

public abstract class InvokingOperation extends MethodOperation {

    protected InvokingOperation(int _index,
                                int _indexChild, MethodOperation _m,
                                OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }
}
