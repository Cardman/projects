package code.maths.litteraladv;

import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.StringMap;

final class FactMaOperation extends MethodMaOperation {
    FactMaOperation(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculate(StringMap<MaStruct> _conf, MaError _error, MaDelimiters _del, CustList<String> _rands) {
        MaStruct value_ = MaNumParsers.tryGet(this, 0);
        if (!(value_ instanceof MaRateStruct)) {
            _error.setOffset(getIndexExp()+StrTypes.offset(getOps(),0));
            return;
        }
        setStruct(new MaRateStruct(((MaRateStruct)value_).getRate().fact()));
    }

}
