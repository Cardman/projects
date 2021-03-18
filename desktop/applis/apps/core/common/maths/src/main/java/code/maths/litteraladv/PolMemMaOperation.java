package code.maths.litteraladv;

import code.maths.LgInt;
import code.maths.Rate;
import code.maths.litteralcom.StrTypes;
import code.util.StringMap;

public final class PolMemMaOperation extends MethodMaOperation {
    PolMemMaOperation(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculate(StringMap<MaStruct> _conf, MaError _error, MaDelimiters _del) {
        MaStruct first_ = MaNumParsers.tryGet(this, 0);
        MaStruct second_ = MaNumParsers.tryGet(this, 1);
        int index_ = StrTypes.offset(getOperats().getOpers(),0);
        if (first_ instanceof MaRateStruct && second_ instanceof MaRateStruct) {
            processRates(_error, (MaRateStruct) first_, (MaRateStruct) second_, index_);
            return;
        }
        MaFractPolStruct fract_ = MaFractPolStruct.wrapOrNull(first_);
        if (fract_ != null) {
            processRatesPol(_error,fract_,second_, index_);
            return;
        }
        _error.setOffset(getIndexExp() + index_);
    }

    private void processRates(MaError _error, MaRateStruct _first, MaRateStruct _second, int _index) {
        if (!_second.getRate().isInteger() || !_second.getRate().isZeroOrGt()) {
            _error.setOffset(getIndexExp() + _index);
            return;
        }
        Rate evt_ = _first.getRate();
        LgInt freq_ = _second.getRate().intPart();
        setStruct(new MaPolMemberStruct(evt_, freq_));
    }

    @Override
    void calculate() {
        StrTypes vs_ = getOperats().getParts();
        getChs().addAllEntries(vs_);
    }
}
