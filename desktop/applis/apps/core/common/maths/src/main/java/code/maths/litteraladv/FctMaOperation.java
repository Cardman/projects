package code.maths.litteraladv;

import code.maths.Decomposition;
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
        procUnaryArith(_error, _id);
    }

    private void procUnaryArith(MaError _error, String _id) {
        if (StringUtil.quickEq(PREM, _id)) {
            procPrem(_error);
        }
        if (StringUtil.quickEq(DIVS, _id)) {
            procDivs(_error);
        }
        if (StringUtil.quickEq(DECOMP, _id)) {
            procDecomp(_error);
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
        if (StringUtil.quickEq(PARMI, _id)) {
            procParmi(_error);
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
            if (areTwoIntegers(rates_)) {
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
            if (areTwoIntegers(rates_)) {
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
            if (areTwoIntegers(rates_)) {
                LgInt quot_= rates_.first().getRate().intPart();
                LgInt div_= rates_.last().getRate().intPart();
                setStruct(new MaBezoutNbStruct(LgInt.identiteBezoutPgcdPpcm(quot_,div_)));
                return;
            }
        }
        _error.setOffset(getIndexExp());
    }

    private void procParmi(MaError _error) {
        if (getChildren().size() == 2) {
            CustList<MaRateStruct> rates_ = tryGetRates();
            if (areTwoIntegers(rates_)) {
                Rate nbOne_= rates_.first().getRate();
                Rate nbTwo_= rates_.last().getRate();
                if (nbOne_.isZeroOrGt()&&nbTwo_.isZeroOrGt()) {
                    setStruct(new MaRateStruct(new Rate(LgInt.among(nbOne_.intPart(),nbTwo_.intPart()))));
                    return;
                }
            }
        }
        _error.setOffset(getIndexExp());
    }

    private static boolean areTwoIntegers(CustList<MaRateStruct> _rates) {
        return areAllIntegersNb(_rates, 2);
    }

    private void procSgn(MaError _error) {
        if (getChildren().size() == 1) {
            CustList<MaRateStruct> rates_ = tryGetRates();
            if (rates_.size() == 1) {
                Rate nb_= rates_.first().getRate();
                setStruct(new MaRateStruct(nb_.signum()));
                return;
            }
            CustList<MaDecompositionNbStruct> decomp_ = tryGetDecompNb();
            if (decomp_.size() == 1) {
                Decomposition decomposition_ = decomp_.first().getDecomposition();
                if (decomposition_.getFactors().isEmpty()) {
                    setStruct(new MaRateStruct(Rate.zero()));
                    return;
                }
                if (decomposition_.isPositive()) {
                    setStruct(new MaRateStruct(Rate.one()));
                    return;
                }
                setStruct(new MaRateStruct(Rate.minusOne()));
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
            if (list_.first() instanceof MaDecompositionNbStruct) {
                setStruct(new MaRateStruct(new Rate(((MaDecompositionNbStruct)list_.first()).getDecomposition().getFactors().size())));
                return;
            }
            if (list_.first() instanceof MaPrimDivisorNbStruct) {
                setStruct(new MaRateStruct(new Rate(2)));
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
            if (areAllIntegersNb(rates_,1)) {
                Rate nb_= rates_.first().getRate();
                setStruct(MaBoolStruct.of(nb_.intPart().isPrime()));
                return;
            }
        }
        _error.setOffset(getIndexExp());
    }

    private void procDivs(MaError _error) {
        if (getChildren().size() == 1) {
            CustList<MaRateStruct> rates_ = tryGetRates();
            if (areAllIntegersNb(rates_,1)) {
                Rate nb_= rates_.first().getRate();
                setStruct(new MaDividersNbStruct(nb_.intPart().getDividers()));
                return;
            }
        }
        _error.setOffset(getIndexExp());
    }

    private void procDecomp(MaError _error) {
        if (getChildren().size() == 1) {
            CustList<MaRateStruct> rates_ = tryGetRates();
            if (areAllIntegersNb(rates_,1)) {
                Rate nb_= rates_.first().getRate();
                setStruct(new MaDecompositionNbStruct(nb_.intPart().decompoPrim()));
                return;
            }
        }
        _error.setOffset(getIndexExp());
    }

    private static boolean areAllIntegersNb(CustList<MaRateStruct> _rates, int _count) {
        return _rates.size() == _count && areAllIntegers(_rates);
    }

    private static boolean areAllIntegers(CustList<MaRateStruct> _list) {
        for (MaRateStruct r: _list) {
            if (!r.getRate().isInteger()) {
                return false;
            }
        }
        return true;
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
    CustList<MaDecompositionNbStruct> tryGetDecompNb() {
        CustList<MaDecompositionNbStruct> rates_ = new CustList<MaDecompositionNbStruct>();
        int len_ = getChildren().size();
        for (int i = 0; i < len_; i++) {
            MaStruct str_ = MaNumParsers.tryGet(this, i);
            if (str_ instanceof MaDecompositionNbStruct) {
                rates_.add((MaDecompositionNbStruct)str_);
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
