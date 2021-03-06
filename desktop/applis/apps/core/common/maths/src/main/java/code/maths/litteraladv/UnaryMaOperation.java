package code.maths.litteraladv;

import code.maths.Rate;
import code.maths.litteralcom.StrTypes;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class UnaryMaOperation extends MethodMaOperation {
    public UnaryMaOperation(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculate(StringMap<MaStruct> _conf, MaError _error, MaDelimiters _del) {
        MaStruct value_ = MaNumParsers.tryGet(this, 0);
        if (!(value_ instanceof MaRateStruct)) {
            _error.setOffset(getIndexExp());
            return;
        }
        Rate o_ = ((MaRateStruct)value_).getRate();
        if (StringUtil.quickEq(getOperats().getOpers().firstValue().trim(), UNARY_MINUS)) {
            setStruct(new MaRateStruct(o_.opposNb()));
        } else {
            setStruct(new MaRateStruct(o_));
        }
    }

    @Override
    void calculate() {
        StrTypes vs_ = getOperats().getParts();
        getChs().addAllEntries(vs_);
    }
}
