package code.maths.litteraladv;

import code.maths.Rate;
import code.maths.litteralcom.StrTypes;
import code.maths.matrix.RateImage;
import code.util.CustList;
import code.util.StringMap;

public final class PolInterMaOperation extends MethodMaOperation {
    PolInterMaOperation(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculate(StringMap<MaStruct> _conf, MaError _error, MaDelimiters _del, CustList<String> _rands) {
        MaStruct first_ = MaNumParsers.tryGet(this, 0);
        MaStruct second_ = MaNumParsers.tryGet(this, 1);
        int index_ = StrTypes.offset(getOps(),0);
        if (first_ instanceof MaRateStruct && second_ instanceof MaRateStruct) {
            processRates((MaRateStruct) first_, (MaRateStruct) second_);
            return;
        }
        _error.setOffset(getIndexExp() + index_);
    }

    private void processRates(MaRateStruct _first, MaRateStruct _second) {
        Rate evt_ = _first.getRate();
        Rate freq_ = _second.getRate();
        setStruct(new MaRateImageStruct(new RateImage(evt_, freq_)));
    }

}
