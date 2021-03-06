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
        if (StringUtil.contains(MaParser.fcts(),id_)) {
            procUnary(_error, id_);
            procBinary(_error, id_);
            procTrFalse(_error, id_);
            return;
        }
        _error.setOffset(getIndexExp());
    }

    private void procUnary(MaError _error, String _id) {
        if (StringUtil.quickEq(SGN, _id)) {
            procSgn(_error);
        }
        if (StringUtil.quickEq(ABS, _id)) {
            procAbs(_error);
        }
        if (StringUtil.quickEq(ENT, _id)) {
            procEnt(_error);
        }
        if (StringUtil.quickEq(TRONC, _id)) {
            procTroncature(_error);
        }
        if (StringUtil.quickEq(NUM, _id)) {
            procNum(_error);
        }
        if (StringUtil.quickEq(DEN, _id)) {
            procDen(_error);
        }
        if (StringUtil.quickEq(LG, _id)) {
            procLg(_error);
        }
        if (StringUtil.quickEq(PREM, _id)) {
            procPrem(_error);
        }
        if (StringUtil.quickEq(DIVS, _id)) {
            procDivs(_error);
        }
    }

    private void procBinary(MaError _error, String _id) {
        if (StringUtil.quickEq(PUIS, _id)) {
            procPower(_error);
        }
        if (StringUtil.quickEq(QUOT, _id)) {
            procQuot(_error);
        }
        if (StringUtil.quickEq(MOD, _id)) {
            procMod(_error);
        }
        if (StringUtil.quickEq(MODTAUX, _id)) {
            procModTaux(_error);
        }
        if (StringUtil.quickEq(BEZOUT, _id)) {
            procBezout(_error);
        }
    }

    private void procTrFalse(MaError _error, String _id) {
        if (StringUtil.quickEq(TRUE_STRING, _id)) {
            procTrue(_error);
        }
        if (StringUtil.quickEq(FALSE_STRING, _id)) {
            procFalse(_error);
        }
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

    private void procModTaux(MaError _error) {
        if (getChildren().size() == 2) {
            CustList<MaRateStruct> rates_ = tryGetRates();
            if (rates_.size() == 2) {
                Rate quot_= rates_.first().getRate();
                Rate div_= rates_.last().getRate();
                if (div_.isZero()) {
                    _error.setOffset(getIndexExp());
                } else {
                    setStruct(new MaRateStruct(Rate.minus(quot_,Rate.multiply(new Rate(Rate.divide(quot_,div_).intPart()),div_))));
                }
                return;
            }
        }
        _error.setOffset(getIndexExp());
    }

    private void procBezout(MaError _error) {
        if (getChildren().size() == 2) {
            CustList<MaRateStruct> rates_ = tryGetRates();
            if (rates_.size() == 2) {
                LgInt quot_= rates_.first().getRate().intPart();
                LgInt div_= rates_.last().getRate().intPart();
                setStruct(new MaBezoutNbStruct(LgInt.identiteBezoutPgcdPpcm(quot_,div_)));
                return;
            }
        }
        _error.setOffset(getIndexExp());
    }

    private void procSgn(MaError _error) {
        if (getChildren().size() == 1) {
            CustList<MaRateStruct> rates_ = tryGetRates();
            if (rates_.size() == 1) {
                Rate nb_= rates_.first().getRate();
                setStruct(new MaRateStruct(nb_.signum()));
                return;
            }
        }
        _error.setOffset(getIndexExp());
    }

    private void procAbs(MaError _error) {
        if (getChildren().size() == 1) {
            CustList<MaRateStruct> rates_ = tryGetRates();
            if (rates_.size() == 1) {
                Rate nb_= rates_.first().getRate();
                setStruct(new MaRateStruct(nb_.absNb()));
                return;
            }
        }
        _error.setOffset(getIndexExp());
    }

    private void procEnt(MaError _error) {
        if (getChildren().size() == 1) {
            CustList<MaRateStruct> rates_ = tryGetRates();
            if (rates_.size() == 1) {
                Rate nb_= rates_.first().getRate();
                setStruct(new MaRateStruct(new Rate(nb_.intPart())));
                return;
            }
        }
        _error.setOffset(getIndexExp());
    }

    private void procTroncature(MaError _error) {
        if (getChildren().size() == 1) {
            CustList<MaRateStruct> rates_ = tryGetRates();
            if (rates_.size() == 1) {
                Rate nb_= rates_.first().getRate();
                setStruct(new MaRateStruct(new Rate(nb_.toLgInt())));
                return;
            }
        }
        _error.setOffset(getIndexExp());
    }

    private void procNum(MaError _error) {
        if (getChildren().size() == 1) {
            CustList<MaRateStruct> rates_ = tryGetRates();
            if (rates_.size() == 1) {
                Rate nb_= rates_.first().getRate();
                setStruct(new MaRateStruct(new Rate(nb_.getNumerator())));
                return;
            }
        }
        _error.setOffset(getIndexExp());
    }

    private void procDen(MaError _error) {
        if (getChildren().size() == 1) {
            CustList<MaRateStruct> rates_ = tryGetRates();
            if (rates_.size() == 1) {
                Rate nb_= rates_.first().getRate();
                setStruct(new MaRateStruct(new Rate(nb_.getDenominator())));
                return;
            }
        }
        _error.setOffset(getIndexExp());
    }

    private void procLg(MaError _error) {
        CustList<MaStruct> list_ = tryGetAll();
        if (list_.size() == 1) {
            if (list_.first() instanceof MaBezoutNbStruct) {
                setStruct(new MaRateStruct(new Rate(4)));
                return;
            }
            if (list_.first() instanceof MaDividersNbStruct) {
                setStruct(new MaRateStruct(new Rate(((MaDividersNbStruct)list_.first()).getDividers().size())));
                return;
            }
            setStruct(new MaRateStruct(new Rate(-1)));
            return;
        }
        _error.setOffset(getIndexExp());
    }

    private void procPrem(MaError _error) {
        if (getChildren().size() == 1) {
            CustList<MaRateStruct> rates_ = tryGetRates();
            if (rates_.size() == 1) {
                Rate nb_= rates_.first().getRate();
                if (nb_.isInteger()) {
                    setStruct(MaBoolStruct.of(nb_.intPart().isPrime()));
                    return;
                }
            }
        }
        _error.setOffset(getIndexExp());
    }

    private void procDivs(MaError _error) {
        if (getChildren().size() == 1) {
            CustList<MaRateStruct> rates_ = tryGetRates();
            if (rates_.size() == 1) {
                Rate nb_= rates_.first().getRate();
                if (nb_.isInteger()) {
                    setStruct(new MaDividersNbStruct(nb_.intPart().getDividers()));
                    return;
                }
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
        vs_.remove(0);
        getChs().addAllEntries(vs_);
    }
}
