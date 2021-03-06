package code.maths.litteraladv;

import code.maths.LgInt;
import code.maths.Rate;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.StringMap;

public final class ArrMaOperation extends MethodMaOperation {
    public ArrMaOperation(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculate(StringMap<MaStruct> _conf, MaError _error, MaDelimiters _del) {
        CustList<MaStruct> values_ = tryGetAll();
        if (values_.size() == 2 && values_.first() instanceof MaBezoutNbStruct && values_.get(1) instanceof MaRateStruct && ((MaRateStruct)values_.get(1)).getRate().isInteger()) {
            procArr((MaBezoutNbStruct)values_.first(),(MaRateStruct)values_.get(1),_error);
            return;
        }
        _error.setOffset(getIndexExp());
    }
    private void procArr(MaBezoutNbStruct _bezout,MaRateStruct _index, MaError _error) {
        LgInt lgInt_ = _index.getRate().intPart();
        if (!lgInt_.isZeroOrGt()) {
            lgInt_.addNb(new LgInt(4));
        }
        if (LgInt.zero().eq(lgInt_)) {
            setStruct(new MaRateStruct(new Rate(_bezout.getIdBezout().getFirst())));
            return;
        }
        if (LgInt.one().eq(lgInt_)) {
            setStruct(new MaRateStruct(new Rate(_bezout.getIdBezout().getSecond())));
            return;
        }
        if (new LgInt(2).eq(lgInt_)) {
            setStruct(new MaRateStruct(new Rate(_bezout.getIdBezout().getPgcd())));
            return;
        }
        if (new LgInt(3).eq(lgInt_)) {
            setStruct(new MaRateStruct(new Rate(_bezout.getIdBezout().getPpcm())));
            return;
        }
        _error.setOffset(getIndexExp());
    }
    CustList<MaStruct> tryGetAll() {
        CustList<MaStruct> rates_ = new CustList<MaStruct>();
        int len_ = getChildren().size();
        for (int i = 0; i < len_; i++) {
            rates_.add(MaNumParsers.tryGet(this, i));
        }
        return rates_;
    }
    @Override
    void calculate() {
        StrTypes vs_ = getOperats().getParts();
        getChs().addAllEntries(vs_);
    }
}
