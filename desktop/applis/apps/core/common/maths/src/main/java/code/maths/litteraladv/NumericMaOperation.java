package code.maths.litteraladv;

import code.maths.Rate;
import code.maths.litteralcom.StrTypes;
import code.maths.matrix.FractPol;
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
        if (first_ instanceof MaRateStruct && second_ instanceof MaRateStruct) {
            processRates(_error, oper_, (MaRateStruct) first_, (MaRateStruct) second_);
            return;
        }
        MaFractPolStruct wrOne_ = MaFractPolStruct.wrapOrNull(first_);
        MaFractPolStruct wrTwo_ = MaFractPolStruct.wrapOrNull(second_);
        if (wrOne_ != null && wrTwo_ != null) {
            processFracts(_error, oper_, wrOne_,wrTwo_);
            return;
        }
        _error.setOffset(getIndexExp() + getOperats().getOpers().firstKey());
    }

    private void processRates(MaError _error, String _oper, MaRateStruct _first, MaRateStruct _second) {
        if (StringUtil.quickEq(_oper, "+")) {
            setStruct(new MaRateStruct(Rate.plus(_first.getRate(), _second.getRate())));
            return;
        }
        if (StringUtil.quickEq(_oper, "-")) {
            setStruct(new MaRateStruct(Rate.minus(_first.getRate(), _second.getRate())));
            return;
        }
        if (StringUtil.quickEq(_oper, "*")) {
            setStruct(new MaRateStruct(Rate.multiply(_first.getRate(), _second.getRate())));
            return;
        }
        if (_second.getRate().isZero()) {
            _error.setOffset(getIndexExp() + getOperats().getOpers().firstKey());
            return;
        }
        setStruct(new MaRateStruct(Rate.divide(_first.getRate(), _second.getRate())));
    }

    private void processFracts(MaError _error, String _oper, MaFractPolStruct _first, MaFractPolStruct _second) {
        if (StringUtil.quickEq(_oper, "+")) {
            setStruct(new MaFractPolStruct(FractPol.plus(_first.getFractPol(),_second.getFractPol())));
            return;
        }
        if (StringUtil.quickEq(_oper, "-")) {
            setStruct(new MaFractPolStruct(FractPol.minus(_first.getFractPol(),_second.getFractPol())));
            return;
        }
        if (StringUtil.quickEq(_oper, "*")) {
            setStruct(new MaFractPolStruct(FractPol.multiply(_first.getFractPol(),_second.getFractPol())));
            return;
        }
        if (_second.getFractPol().isZero()) {
            _error.setOffset(getIndexExp() + getOperats().getOpers().firstKey());
            return;
        }
        setStruct(new MaFractPolStruct(FractPol.divide(_first.getFractPol(),_second.getFractPol())));
    }
    @Override
    void calculate() {
        StrTypes vs_ = getOperats().getParts();
        getChs().addAllEntries(vs_);
    }
}
