package code.maths.litteraladv;

import code.maths.LgInt;
import code.maths.Rate;
import code.maths.litteralcom.IndexStrPart;
import code.maths.litteralcom.StrTypes;
import code.util.StringMap;

public final class PolMemMaOperation extends MethodMaOperation {
    PolMemMaOperation(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculate(StringMap<MaStruct> _conf, MaError _error, MaDelimiters _del) {
        if (getChildren().size() != 2) {
            _error.setOffset(getIndexExp());
            return;
        }
        MaStruct first_ = MaNumParsers.tryGet(this, 0);
        MaStruct second_ = MaNumParsers.tryGet(this, 1);
        IndexStrPart firstOper_ = getOperats().getOpers().getValues().first();
        if (first_ instanceof MaRateStruct && second_ instanceof MaRateStruct) {
            processRates(_error, (MaRateStruct) first_, (MaRateStruct) second_, firstOper_);
            return;
        }
        MaFractPolStruct fract_ = MaFractPolStruct.wrapOrNull(first_);
        if (fract_ != null) {
            processRatesPol(_error,fract_,second_,firstOper_);
            return;
        }
        _error.setOffset(getIndexExp() + firstOper_.getIndex());
    }

    private void processRates(MaError _error, MaRateStruct _first, MaRateStruct _second, IndexStrPart _firstOper) {
        if (!_second.getRate().isInteger() || !_second.getRate().isZeroOrGt()) {
            _error.setOffset(getIndexExp() + _firstOper.getIndex());
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
