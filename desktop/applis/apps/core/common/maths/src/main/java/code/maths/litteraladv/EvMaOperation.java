package code.maths.litteraladv;

import code.maths.LgInt;
import code.maths.Rate;
import code.maths.litteralcom.IndexStrPart;
import code.maths.litteralcom.StrTypes;
import code.maths.montecarlo.EventFreq;
import code.util.StringMap;

public final class EvMaOperation extends MethodMaOperation {
    EvMaOperation(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op) {
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
            if (!((MaRateStruct)second_).getRate().isInteger()||((MaRateStruct)second_).getRate().isZeroOrLt()) {
                _error.setOffset(getIndexExp()+firstOper_.getIndex());
                return;
            }
            Rate evt_ = ((MaRateStruct) first_).getRate();
            LgInt freq_ = ((MaRateStruct) second_).getRate().intPart();
            setStruct(new MaEventFreqStruct(new EventFreq<Rate>(evt_,freq_)));
            return;
        }
        MaFractPolStruct fract_ = MaFractPolStruct.wrapOrNull(first_);
        if (fract_ != null) {
            processRatesPol(_error,fract_,second_,firstOper_);
            return;
        }
        _error.setOffset(getIndexExp()+firstOper_.getIndex());
    }

    @Override
    void calculate() {
        StrTypes vs_ = getOperats().getParts();
        getChs().addAllEntries(vs_);
    }
}
