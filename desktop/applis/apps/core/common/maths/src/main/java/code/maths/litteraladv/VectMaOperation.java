package code.maths.litteraladv;

import code.maths.Rate;
import code.maths.litteralcom.StrTypes;
import code.maths.matrix.Vect;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.StringMap;

public final class VectMaOperation extends MethodMaOperation {
    protected VectMaOperation(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculate(StringMap<MaStruct> _conf, MaError _error, MaDelimiters _del) {
        CustList<MaStruct> values_ = tryGetAll(this);
        int firstIndex_ = firstIndex(values_);
        CustList<MaRateStruct> rates_ = tryGetAllAsRate(this);
        if (rates_ == null) {
            rates_ = new CustList<MaRateStruct>();
        }
        if (rates_.isEmpty()) {
            _error.setOffset(getIndexExp()+ StrTypes.offset(getOperats().getParts(),firstIndex_));
            return;
        }
        CustList<Rate> val_ = new CustList<Rate>(new CollCapacity(rates_.size()));
        for (MaRateStruct r: rates_) {
            val_.add(r.getRate());
        }
        setStruct(new MaVectStruct(new Vect(val_)));
    }

    private static int firstIndex(CustList<MaStruct> _values) {
        int index_ = -1;
        int ind_ = 0;
        for (MaStruct m: _values) {
            if (koNb(m)) {
                index_ = ind_;
                break;
            }
            ind_++;
        }
        return index_;
    }

    private static boolean koNb(MaStruct _m) {
        return !(_m instanceof MaRateStruct);
    }

    @Override
    void calculate() {
        StrTypes vs_ = getOperats().getParts();
        getChs().addAllEntries(vs_);
    }
}
