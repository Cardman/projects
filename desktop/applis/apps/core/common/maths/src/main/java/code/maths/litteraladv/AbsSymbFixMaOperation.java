package code.maths.litteraladv;

import code.maths.litteralcom.StrTypes;

abstract class AbsSymbFixMaOperation extends MethodMaOperation {
    private String oper = "";
    private int operOff;
    protected AbsSymbFixMaOperation(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }
    @Override
    void calculate() {
        StrTypes vs_ = getOperats().getParts();
        operOff = vs_.lastKey();
        oper = vs_.lastValue();
        vs_.remove(vs_.size()-1);
        vs_.remove(0);
        getChs().addAllEntries(vs_);
    }

    String getOper() {
        return oper;
    }

    int getOperOff() {
        return operOff;
    }
}
