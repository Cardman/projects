package code.maths.litteraladv;

import code.maths.LgInt;
import code.maths.Rate;
import code.maths.litteralcom.IndexStrPart;
import code.maths.litteralcom.StrTypes;
import code.maths.montecarlo.EventFreq;
import code.util.StringMap;
import code.util.core.IndexConstants;

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
        int i_ = IndexConstants.SECOND_INDEX;
        Rate evt_ = Rate.zero();
        LgInt freq_ = LgInt.zero();
        for (IndexStrPart e: getOperats().getOpers().getValues()) {
            MaStruct first_ = MaNumParsers.tryGet(this, i_-1);
            MaStruct second_ = MaNumParsers.tryGet(this, i_);
            if (!(first_ instanceof MaRateStruct) || !(second_ instanceof MaRateStruct)) {
                _error.setOffset(getIndexExp()+e.getIndex());
                return;
            }
            if (!((MaRateStruct)second_).getRate().isInteger()||((MaRateStruct)second_).getRate().isZeroOrLt()) {
                _error.setOffset(getIndexExp()+e.getIndex());
                return;
            }
            evt_ = ((MaRateStruct)first_).getRate();
            freq_ = ((MaRateStruct)second_).getRate().intPart();
            i_++;
        }
        setStruct(new MaEventFreqStruct(new EventFreq<Rate>(evt_,freq_)));
    }

    @Override
    void calculate() {
        StrTypes vs_ = getOperats().getParts();
        getChs().addAllEntries(vs_);
    }
}
