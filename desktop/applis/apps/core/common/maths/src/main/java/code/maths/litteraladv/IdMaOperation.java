package code.maths.litteraladv;

import code.maths.Rate;
import code.maths.litteralcom.StrTypes;
import code.maths.montecarlo.EventFreq;
import code.maths.montecarlo.MonteCarloNumber;
import code.util.CustList;
import code.util.StringMap;

public final class IdMaOperation extends MethodMaOperation {
    public IdMaOperation(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculate(StringMap<MaStruct> _conf, MaError _error, MaDelimiters _del) {
        CustList<MaOperationNode> chidren_ = getChildren();
        boolean allEvts_ = true;
        int len_ = chidren_.size();
        MonteCarloNumber law_ = new MonteCarloNumber();
        for (int i = 0; i < len_; i++) {
            MaStruct value_ = MaNumParsers.tryGet(this, i);
            if (!(value_ instanceof MaEventFreqStruct)) {
                allEvts_ = false;
                break;
            }
            EventFreq<Rate> pair_ = ((MaEventFreqStruct) value_).getPair();
            law_.addQuickEvent(pair_.getEvent(),pair_.getFreq());
        }
        if (allEvts_) {
            setStruct(new MaMonteCarloNumberStruct(law_));
            return;
        }
        if (len_ != 1) {
            _error.setOffset(getIndexExp());
            return;
        }
        setStruct(chidren_.first().getStruct());
    }

    @Override
    void calculate() {
        StrTypes vs_ = getOperats().getParts();
        vs_.remove(0);
        getChs().addAllEntries(vs_);
    }
}
