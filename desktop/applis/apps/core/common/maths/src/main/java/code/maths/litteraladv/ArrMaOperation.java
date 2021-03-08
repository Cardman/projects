package code.maths.litteraladv;

import code.maths.LgInt;
import code.maths.PrimDivisor;
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
        if (values_.size() == 2) {
            procOneIndex(_error, values_);
            return;
        }
        if (values_.size() == 3 && values_.first() instanceof MaDecompositionNbStruct
                && areIndexes(values_)) {
            procDecomp((MaDecompositionNbStruct)values_.first(),(MaRateStruct)values_.get(1),(MaRateStruct)values_.get(2),_error);
            return;
        }
        _error.setOffset(getIndexExp());
    }

    private void procOneIndex(MaError _error, CustList<MaStruct> _values) {
        if (_values.first() instanceof MaBezoutNbStruct && isIndex(_values)) {
            procArr((MaBezoutNbStruct) _values.first(),(MaRateStruct) _values.get(1), _error);
            return;
        }
        if (_values.first() instanceof MaDividersNbStruct && isIndex(_values)) {
            procDivs((MaDividersNbStruct) _values.first(),(MaRateStruct) _values.get(1), _error);
            return;
        }
        if (_values.first() instanceof MaDecompositionNbStruct && isIndex(_values)) {
            procDecomp((MaDecompositionNbStruct) _values.first(),(MaRateStruct) _values.get(1), _error);
            return;
        }
        if (_values.first() instanceof MaPrimDivisorNbStruct && isIndex(_values)) {
            procPrimDivisor((MaPrimDivisorNbStruct) _values.first(),(MaRateStruct) _values.get(1), _error);
            return;
        }
        _error.setOffset(getIndexExp());
    }

    private static boolean isIndex(CustList<MaStruct> _values) {
        return _values.get(1) instanceof MaRateStruct && ((MaRateStruct) _values.get(1)).getRate().isInteger();
    }

    private static boolean areIndexes(CustList<MaStruct> _values) {
        return _values.get(1) instanceof MaRateStruct && ((MaRateStruct) _values.get(1)).getRate().isInteger()
                && _values.get(2) instanceof MaRateStruct && ((MaRateStruct) _values.get(2)).getRate().isInteger();
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
    private void procDivs(MaDividersNbStruct _divs,MaRateStruct _index, MaError _error) {
        LgInt lgInt_ = _index.getRate().intPart();
        CustList<LgInt> dividers_ = _divs.getDividers();
        int len_ = dividers_.size();
        if (!lgInt_.isZeroOrGt()) {
            lgInt_.addNb(new LgInt(len_));
        }
        if (lgInt_.isZeroOrGt() && LgInt.strLower(lgInt_,new LgInt(len_))) {
            setStruct(new MaRateStruct(new Rate(dividers_.get((int) lgInt_.ll()))));
            return;
        }
        _error.setOffset(getIndexExp());
    }
    private void procDecomp(MaDecompositionNbStruct _divs,MaRateStruct _index, MaError _error) {
        LgInt lgInt_ = _index.getRate().intPart();
        CustList<PrimDivisor> dividers_ = _divs.getDecomposition().getFactors();
        int len_ = dividers_.size();
        if (!lgInt_.isZeroOrGt()) {
            lgInt_.addNb(new LgInt(len_));
        }
        if (lgInt_.isZeroOrGt() && LgInt.strLower(lgInt_,new LgInt(len_))) {
            setStruct(new MaPrimDivisorNbStruct(dividers_.get((int) lgInt_.ll())));
            return;
        }
        _error.setOffset(getIndexExp());
    }
    private void procDecomp(MaDecompositionNbStruct _divs,MaRateStruct _index,MaRateStruct _indexTwo, MaError _error) {
        LgInt lgInt_ = _index.getRate().intPart();
        CustList<PrimDivisor> dividers_ = _divs.getDecomposition().getFactors();
        int len_ = dividers_.size();
        if (!lgInt_.isZeroOrGt()) {
            lgInt_.addNb(new LgInt(len_));
        }
        if (lgInt_.isZeroOrGt() && LgInt.strLower(lgInt_,new LgInt(len_))) {
            PrimDivisor primDivisor_ = dividers_.get((int) lgInt_.ll());
            LgInt lgIntSec_ = _indexTwo.getRate().intPart();
            if (!lgIntSec_.isZeroOrGt()) {
                lgIntSec_.addNb(new LgInt(2));
            }
            if (lgIntSec_.eq(LgInt.zero())) {
                setStruct(new MaRateStruct(new Rate(primDivisor_.getPrime())));
                return;
            }
            if (lgIntSec_.eq(LgInt.one())) {
                setStruct(new MaRateStruct(new Rate(primDivisor_.getExponent())));
                return;
            }
        }
        _error.setOffset(getIndexExp());
    }
    private void procPrimDivisor(MaPrimDivisorNbStruct _divs,MaRateStruct _index, MaError _error) {
        LgInt lgInt_ = _index.getRate().intPart();
        PrimDivisor primDivisor_ = _divs.getPrimDivisor();
        if (!lgInt_.isZeroOrGt()) {
            lgInt_.addNb(new LgInt(2));
        }
        if (lgInt_.eq(LgInt.zero())) {
            setStruct(new MaRateStruct(new Rate(primDivisor_.getPrime())));
            return;
        }
        if (lgInt_.eq(LgInt.one())) {
            setStruct(new MaRateStruct(new Rate(primDivisor_.getExponent())));
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
