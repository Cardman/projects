package code.maths.litteraladv;

import code.maths.LgInt;
import code.maths.Rate;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class FctMaOperation extends MethodMaOperation {
    private final String methodName;
    private final StringMap<String> mapping;
    public FctMaOperation(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op, StringMap<String> _mapping) {
        super(_index, _indexChild, _m, _op);
        methodName = getOperats().getFct().trim();
        mapping = _mapping;
    }

    @Override
    void calculate(StringMap<MaStruct> _conf, MaError _error, MaDelimiters _del) {
        String id_ = aliasOrId();
        if (StringUtil.quickEq(TRUE_STRING,id_)) {
            procTrue(_error);
            return;
        }
        if (StringUtil.quickEq(FALSE_STRING,id_)) {
            procFalse(_error);
            return;
        }
        if (StringUtil.quickEq(PUIS, id_)) {
            procPower(_error);
            return;
        }
        if (StringUtil.quickEq(QUOT, id_)) {
            procQuot(_error);
            return;
        }
        if (StringUtil.quickEq(MOD, id_)) {
            procMod(_error);
            return;
        }
        _error.setOffset(getIndexExp());
    }

    private String aliasOrId() {
        String res_ = StringUtil.nullToEmpty(mapping.getVal(methodName));
        if (res_.isEmpty()) {
            return methodName;
        }
        return res_;
    }
    private void procTrue(MaError _error) {
        if (getChildren().isEmpty()) {
            setStruct(MaBoolStruct.of(true));
            return;
        }
        _error.setOffset(getIndexExp());
    }

    private void procFalse(MaError _error) {
        if (getChildren().isEmpty()) {
            setStruct(MaBoolStruct.of(false));
            return;
        }
        _error.setOffset(getIndexExp());
    }

    private void procPower(MaError _error) {
        if (getChildren().size() == 2) {
            CustList<MaRateStruct> rates_ = tryGetRates();
            if (rates_.size() == 2) {
                Rate base_= rates_.first().getRate();
                Rate exposant_= rates_.last().getRate();
                if (base_.isZero() && !exposant_.isZeroOrGt()) {
                    _error.setOffset(getIndexExp());
                } else {
                    setStruct(new MaRateStruct(Rate.powNb(base_, exposant_)));
                }
                return;
            }
        }
        _error.setOffset(getIndexExp());
    }

    private void procQuot(MaError _error) {
        if (getChildren().size() == 2) {
            CustList<MaRateStruct> rates_ = tryGetRates();
            if (rates_.size() == 2) {
                LgInt quot_= rates_.first().getRate().intPart();
                LgInt div_= rates_.last().getRate().intPart();
                if (div_.isZero()) {
                    _error.setOffset(getIndexExp());
                } else {
                    setStruct(new MaRateStruct(new Rate(LgInt.divide(quot_,div_))));
                }
                return;
            }
        }
        _error.setOffset(getIndexExp());
    }

    private void procMod(MaError _error) {
        if (getChildren().size() == 2) {
            CustList<MaRateStruct> rates_ = tryGetRates();
            if (rates_.size() == 2) {
                LgInt quot_= rates_.first().getRate().intPart();
                LgInt div_= rates_.last().getRate().intPart();
                if (div_.isZero()) {
                    _error.setOffset(getIndexExp());
                } else {
                    setStruct(new MaRateStruct(new Rate(LgInt.remain(quot_,div_))));
                }
                return;
            }
        }
        _error.setOffset(getIndexExp());
    }
    CustList<MaRateStruct> tryGetRates() {
        CustList<MaRateStruct> rates_ = new CustList<MaRateStruct>();
        int len_ = getChildren().size();
        for (int i = 0; i < len_; i++) {
            MaStruct str_ = MaNumParsers.tryGet(this, i);
            if (str_ instanceof MaRateStruct) {
                rates_.add((MaRateStruct)str_);
            }
        }
        return rates_;
    }
    @Override
    void calculate() {
        StrTypes vs_ = getOperats().getParts();
        vs_.remove(0);
        getChs().addAllEntries(vs_);
    }
}
