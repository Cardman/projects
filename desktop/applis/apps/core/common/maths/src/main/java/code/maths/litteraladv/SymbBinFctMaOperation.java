package code.maths.litteraladv;

import code.maths.LgInt;
import code.maths.Rate;
import code.maths.litteralcom.StrTypes;
import code.maths.matrix.FractPol;
import code.maths.matrix.Matrix;
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
            procModInt(_error, rates_);
            return;
        }
        if (rates_.size() == 2) {
            procModRate(_error, rates_);
            return;
        }
        CustList<MaFractPolStruct> fractPols_ = tryGetFracPols();
        if (areTwoPols(fractPols_)) {
            procModPol(_error, fractPols_);
            return;
        }
        if (fractPols_.size() == 2) {
            procModFract(_error, fractPols_);
            return;
        }
        _error.setOffset(getIndexExp());
    }

    private void procModInt(MaError _error, CustList<MaRateStruct> _rates) {
        LgInt quot_= _rates.first().getRate().intPart();
        LgInt div_= _rates.last().getRate().intPart();
        if (div_.isZero()) {
            _error.setOffset(getIndexExp());
        } else {
            setStruct(new MaRateStruct(new Rate(LgInt.remain(quot_,div_))));
        }
    }

    private void procModRate(MaError _error, CustList<MaRateStruct> _rates) {
        Rate quot_= _rates.first().getRate();
        Rate div_= _rates.last().getRate();
        if (div_.isZero()) {
            _error.setOffset(getIndexExp());
        } else {
            setStruct(new MaRateStruct(Rate.minus(quot_,Rate.multiply(new Rate(Rate.divide(quot_,div_).intPart()),div_))));
        }
    }

    private void procModPol(MaError _error, CustList<MaFractPolStruct> _fractPols) {
        Polynom quot_= _fractPols.first().getFractPol().intPart();
        Polynom div_= _fractPols.last().getFractPol().intPart();
        if (div_.isZero()) {
            _error.setOffset(getIndexExp());
        } else {
            setStruct(new MaPolynomStruct(quot_.remainPolynom(div_)));
        }
    }

    private void procModFract(MaError _error, CustList<MaFractPolStruct> _fractPols) {
        FractPol quot_= _fractPols.first().getFractPol();
        FractPol div_= _fractPols.last().getFractPol();
        if (div_.isZero()) {
            _error.setOffset(getIndexExp());
        } else {
            setStruct(new MaFractPolStruct(FractPol.minus(quot_,FractPol.multiply(new FractPol(FractPol.divide(quot_,div_).intPart()),div_))));
        }
    }

    private void procPower(MaError _error) {
        CustList<MaRateStruct> rates_ = tryGetRates();
        if (rates_.size() == 2) {
            procPowerRate(_error, rates_);
            return;
        }
        MaStruct val_ = MaNumParsers.tryGet(this, 0);
        MaStruct power_ = MaNumParsers.tryGet(this, 1);
        MaFractPolStruct fract_ = MaFractPolStruct.wrapOrNull(val_);
        if (fract_ != null && power_ instanceof MaRateStruct && ((MaRateStruct)power_).getRate().isInteger()) {
            procPowerFract(_error, rates_, fract_);
            return;
        }
        if (val_ instanceof MaMatrixStruct && power_ instanceof MaRateStruct && ((MaRateStruct)power_).getRate().isInteger()) {
            procPowerMatrix(_error, rates_, (MaMatrixStruct) val_);
            return;
        }
        _error.setOffset(getIndexExp());
    }

    private void procPowerMatrix(MaError _error, CustList<MaRateStruct> _rates, MaMatrixStruct _val) {
        Matrix matrix_ = _val.getMatrix();
        Rate exposant_= _rates.last().getRate();
        if (matrix_.isSquare()) {
            if (!exposant_.isZeroOrGt()) {
                setStruct(new MaMatrixStruct(matrix_.inv().power(exposant_.absNb().intPart())));
            } else {
                setStruct(new MaMatrixStruct(matrix_.power(exposant_.intPart())));
            }
        } else if (Rate.eq(Rate.one(),exposant_.absNb())){
            if (!exposant_.isZeroOrGt()) {
                setStruct(new MaMatrixStruct(matrix_.inv()));
            } else {
                setStruct(_val);
            }
        } else {
            _error.setOffset(getIndexExp());
        }
    }

    private void procPowerFract(MaError _error, CustList<MaRateStruct> _rates, MaFractPolStruct _fract) {
        FractPol base_= _fract.getFractPol();
        Rate exposant_= _rates.last().getRate();
        if (base_.isZero() && !exposant_.isZeroOrGt()) {
            _error.setOffset(getIndexExp());
        } else {
            setStruct(new MaFractPolStruct(base_.powNb(exposant_.intPart())));
        }
    }

    private void procPowerRate(MaError _error, CustList<MaRateStruct> _rates) {
        Rate base_= _rates.first().getRate();
        Rate exposant_= _rates.last().getRate();
        if (base_.isZero() && !exposant_.isZeroOrGt()) {
            _error.setOffset(getIndexExp());
        } else {
            setStruct(new MaRateStruct(Rate.powNb(base_, exposant_)));
        }
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
