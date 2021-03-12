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
        CustList<MaRateStruct> rates_ = tryGetAllAsRate(this);
        if (rates_ == null) {
            rates_ = new CustList<MaRateStruct>();
        }
        if (rates_.isEmpty()) {
            _error.setOffset(getIndexExp());
            return;
        }
        CustList<Rate> val_ = new CustList<Rate>(new CollCapacity(rates_.size()));
        for (MaRateStruct r: rates_) {
            val_.add(r.getRate());
        }
        setStruct(new MaVectStruct(new Vect(val_)));
    }

    @Override
    void calculate() {
        StrTypes vs_ = getOperats().getParts();
        getChs().addAllEntries(vs_);
    }
}
