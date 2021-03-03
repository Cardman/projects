package code.maths.litteral;

public abstract class InvokingMbOperation extends MethodMbOperation {

    protected InvokingMbOperation(int _index,
                                  int _indexChild, MethodMbOperation _m,
                                  MbOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }
}
