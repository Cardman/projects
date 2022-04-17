package code.maths.litteraladv;

abstract class AbsSymbFixMaOperation extends MethodMaOperation {
    private final String oper;
    private final int operOff;
    protected AbsSymbFixMaOperation(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op, int _off, String _ope) {
        super(_index, _indexChild, _m, _op);
        operOff = _off;
        oper = _ope;
    }

    String getOper() {
        return oper;
    }

    int getOperOff() {
        return operOff;
    }
}
