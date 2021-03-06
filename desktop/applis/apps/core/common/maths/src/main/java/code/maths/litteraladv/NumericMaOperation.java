package code.maths.litteraladv;

import code.maths.Rate;
import code.maths.litteralcom.StrTypes;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class NumericMaOperation extends MethodMaOperation {
    public NumericMaOperation(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculate(StringMap<MaStruct> _conf, MaError _error, MaDelimiters _del) {
        String oper_ = getOperats().getOpers().firstValue().trim();
        MaStruct first_ = MaNumParsers.tryGet(this, 0);
        MaStruct second_ = MaNumParsers.tryGet(this, 1);
        if (!(first_ instanceof MaRateStruct) || !(second_ instanceof MaRateStruct)) {
            _error.setOffset(getIndexExp()+ getOperats().getOpers().firstKey());
            return;
        }
        if (StringUtil.quickEq(oper_,"+")) {
            setStruct(new MaRateStruct(Rate.plus(((MaRateStruct)first_).getRate(),((MaRateStruct)second_).getRate())));
            return;
        }
        if (StringUtil.quickEq(oper_,"-")) {
            setStruct(new MaRateStruct(Rate.minus(((MaRateStruct)first_).getRate(),((MaRateStruct)second_).getRate())));
            return;
        }
        if (StringUtil.quickEq(oper_,"*")) {
            setStruct(new MaRateStruct(Rate.multiply(((MaRateStruct)first_).getRate(),((MaRateStruct)second_).getRate())));
            return;
        }
        if (((MaRateStruct)second_).getRate().isZero()) {
            _error.setOffset(getIndexExp()+ getOperats().getOpers().firstKey());
            return;
        }
        setStruct(new MaRateStruct(Rate.divide(((MaRateStruct)first_).getRate(),((MaRateStruct)second_).getRate())));
    }

    @Override
    void calculate() {
        StrTypes vs_ = getOperats().getParts();
        getChs().addAllEntries(vs_);
    }
}
