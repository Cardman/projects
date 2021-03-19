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
        int len_ = getChildren().size();
        CustList<MaRateStruct> rates_ = new CustList<MaRateStruct>();
        int index_ = -1;
        for (int i = 0; i < len_; i++) {
            MaStruct str_ = MaNumParsers.tryGet(this, i);
            if (!(str_ instanceof MaRateStruct)) {
                index_ = i;
                rates_.clear();
                break;
            }
            rates_.add((MaRateStruct)str_);
        }
        if (rates_.isEmpty()) {
            _error.setOffset(getIndexExp()+ StrTypes.offset(getOperats().getParts(),index_));
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
