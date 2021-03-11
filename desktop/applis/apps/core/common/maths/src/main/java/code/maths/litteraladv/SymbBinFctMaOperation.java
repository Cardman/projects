package code.maths.litteraladv;

import code.maths.LgInt;
import code.maths.Rate;
import code.maths.litteralcom.StrTypes;
import code.maths.matrix.FractPol;
import code.maths.matrix.Polynom;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class SymbBinFctMaOperation extends MethodMaOperation {
    private String oper = "";
    public SymbBinFctMaOperation(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculate(StringMap<MaStruct> _conf, MaError _error, MaDelimiters _del) {
        if (StringUtil.quickEq(oper,"/")) {
            procQuot(_error);
        }
        if (StringUtil.quickEq(oper,"%")) {
            procMod(_error);
        }
        if (StringUtil.quickEq(oper,"^")) {
            procPower(_error);
        }
        if (StringUtil.quickEq(oper,"<=")) {
            procParmi(_error);
        }
        if (StringUtil.quickEq(oper,"/%")) {
            procBezout(_error);
        }
    }

    private void procQuot(MaError _error) {
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
        CustList<MaFractPolStruct> fractPols_ = tryGetFracPols();
        if (areTwoPols(fractPols_)) {
            Polynom quot_= fractPols_.first().getFractPol().intPart();
            Polynom div_= fractPols_.last().getFractPol().intPart();
            if (div_.isZero()) {
                _error.setOffset(getIndexExp());
            } else {
                setStruct(new MaPolynomStruct(quot_.dividePolynom(div_)));
            }
            return;
        }
        _error.setOffset(getIndexExp());
    }

    private void procMod(MaError _error) {
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
        CustList<MaFractPolStruct> fractPols_ = tryGetFracPols();
        if (areTwoPols(fractPols_)) {
            Polynom quot_= fractPols_.first().getFractPol().intPart();
            Polynom div_= fractPols_.last().getFractPol().intPart();
            if (div_.isZero()) {
                _error.setOffset(getIndexExp());
            } else {
                setStruct(new MaPolynomStruct(quot_.remainPolynom(div_)));
            }
            return;
        }
        if (fractPols_.size() == 2) {
            FractPol quot_= fractPols_.first().getFractPol();
            FractPol div_= fractPols_.last().getFractPol();
            if (div_.isZero()) {
                _error.setOffset(getIndexExp());
            } else {
                setStruct(new MaFractPolStruct(FractPol.minus(quot_,FractPol.multiply(new FractPol(FractPol.divide(quot_,div_).intPart()),div_))));
            }
            return;
        }
        _error.setOffset(getIndexExp());
    }

    private void procPower(MaError _error) {
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
        MaStruct val_ = MaNumParsers.tryGet(this, 0);
        MaStruct power_ = MaNumParsers.tryGet(this, 1);
        MaFractPolStruct fract_ = MaFractPolStruct.wrapOrNull(val_);
        if (fract_ != null && power_ instanceof MaRateStruct && ((MaRateStruct)power_).getRate().isInteger()) {
            FractPol base_= fract_.getFractPol();
            Rate exposant_= rates_.last().getRate();
            if (base_.isZero() && !exposant_.isZeroOrGt()) {
                _error.setOffset(getIndexExp());
            } else {
                setStruct(new MaFractPolStruct(base_.powNb(exposant_.intPart())));
            }
            return;
        }
        _error.setOffset(getIndexExp());
    }

    private void procBezout(MaError _error) {
        CustList<MaRateStruct> rates_ = tryGetRates();
        if (areTwoIntegers(rates_)) {
            LgInt quot_= rates_.first().getRate().intPart();
            LgInt div_= rates_.last().getRate().intPart();
            setStruct(new MaBezoutNbStruct(LgInt.identiteBezoutPgcdPpcm(quot_,div_)));
            return;
        }
        CustList<MaFractPolStruct> fractPols_ = tryGetFracPols();
        if (areTwoPols(fractPols_)) {
            Polynom quot_= fractPols_.first().getFractPol().intPart();
            Polynom div_= fractPols_.last().getFractPol().intPart();
            setStruct(new MaBezoutPolStruct(Polynom.idBezoutPgcdPpcm(quot_,div_)));
            return;
        }
        _error.setOffset(getIndexExp());
    }

    private void procParmi(MaError _error) {
        CustList<MaRateStruct> rates_ = tryGetRates();
        if (areTwoIntegers(rates_)) {
            Rate nbOne_= rates_.first().getRate();
            Rate nbTwo_= rates_.last().getRate();
            if (nbOne_.isZeroOrGt()&&nbTwo_.isZeroOrGt()) {
                setStruct(new MaRateStruct(new Rate(LgInt.among(nbOne_.intPart(),nbTwo_.intPart()))));
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
    CustList<MaFractPolStruct> tryGetFracPols() {
        CustList<MaFractPolStruct> rates_ = new CustList<MaFractPolStruct>();
        int len_ = getChildren().size();
        for (int i = 0; i < len_; i++) {
            MaStruct str_ = MaNumParsers.tryGet(this, i);
            MaFractPolStruct wr_ = MaFractPolStruct.wrapOrNull(str_);
            if (wr_ != null) {
                rates_.add(wr_);
            }
        }
        return rates_;
    }

    @Override
    void calculate() {
        StrTypes vs_ = getOperats().getParts();
        oper = vs_.lastValue();
        vs_.remove(vs_.size()-1);
        vs_.remove(0);
        getChs().addAllEntries(vs_);
    }
}
